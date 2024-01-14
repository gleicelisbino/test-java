package br.com.blz.testjava.service

import br.com.blz.testjava.ProductMapper
import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.dto.responses.ProductResponse
import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Qualifier("productServiceQualifier")
@Service
class ProductService(
  @Qualifier("productRepositoryQualifier") private val productRepository: ProductRepository,
  @Qualifier("productMapperQualifier") private val productMapper: ProductMapper
) {

  fun createProduct(request: ProductRequest): ProductResponse {
    val product = productMapper.toProduct(request)
    productRepository.findBySku(product.sku)?.let {
      throw ProductAlreadyExistsException(product.sku)
    }
    val calculated = productRepository.save(calculateInventoryAndMarketable(product))
    return productMapper.toProductResponse(calculated)
  }

  fun getProductBySku(sku: Long): ProductResponse {
    val product = productRepository.findBySku(sku)
      ?: throw ProductNotFoundException(sku)
    return productMapper.toProductResponse(product)
  }

  fun updateProduct(sku: Long, request: ProductRequest): ProductResponse {
    val existingProduct = productRepository.findBySku(sku)
      ?: throw ProductNotFoundException(sku)

    val updatedProduct = productMapper.toProduct(request).copy(sku = existingProduct.sku)
    return productMapper.toProductResponse(productRepository.save(calculateInventoryAndMarketable(updatedProduct)))
  }

  fun deleteProductBySku(sku: Long) {
    productRepository.deleteBySku(sku)
  }

  private fun calculateInventoryAndMarketable(product: Product): Product {
    val inventoryQuantity = product.inventory.warehouses.sumOf { it.quantity }
    val ismarketable = inventoryQuantity > 0

    return product.copy(
      inventory = product.inventory.copy(quantity = inventoryQuantity),
      ismarketable = ismarketable
    )
  }
}
