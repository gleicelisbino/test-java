package br.com.blz.testjava.service

import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.dto.responses.ProductResponse
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.ProductMapper
import br.com.blz.testjava.repository.ProductAlreadyExistsException
import br.com.blz.testjava.repository.ProductNotFoundException
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository,
                     private val productMapper: ProductMapper) {

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

  fun calculateInventoryAndMarketable(product: Product): Product {
    val inventoryQuantity = product.inventory.warehouses.sumOf { it.quantity }
    val ismarketable = inventoryQuantity > 0

    return product.copy(
      inventory = product.inventory.copy(quantity = inventoryQuantity),
      ismarketable = ismarketable
    )
  }


}
