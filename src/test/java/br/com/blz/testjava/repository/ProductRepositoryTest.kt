package br.com.blz.testjava.repository

import br.com.blz.testjava.function.createProduct
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ProductRepositoryTest {

  private lateinit var productRepository: ProductRepository

  @BeforeEach
  fun setUp() {
    productRepository = ProductRepository()
  }

  @Test
  fun saveAndFindSKU() {
    val product = createProduct()
    productRepository.save(product)

    val retrievedProduct = productRepository.findBySku(product.sku)

    assertEquals(product, retrievedProduct)
  }

  @Test
  fun deleteBySkuTest() {
    val product = createProduct()
    productRepository.save(product)

    productRepository.deleteBySku(product.sku)

    val retrievedProduct = productRepository.findBySku(product.sku)

    assertNull(retrievedProduct)
  }
}
