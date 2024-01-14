package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Qualifier("productRepositoryQualifier")
@Repository
class ProductRepository {
  private val products = mutableMapOf<Long, Product>()

  fun save(product: Product): Product {
    products[product.sku] = product
    return product
  }

  fun findBySku(sku: Long): Product? = products[sku]

  fun deleteBySku(sku: Long) {
    products.remove(sku)
  }
}
