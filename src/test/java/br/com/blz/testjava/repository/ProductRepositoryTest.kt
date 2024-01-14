package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ProductRepositoryTest {

  private val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
  private val sku: Long = 43264
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

  private fun createProduct(): Product {
    return Product(
      sku = sku,
      name = productName,
      inventory = Inventory(
        quantity = 15,
        warehouses = listOf(
          Warehouse("SP", 3, "ECOMMERCE"),
          Warehouse("MOEMA", 12, "PHYSICAL_STORE")
        )
      ),
      ismarketable = true
    )
  }
}
