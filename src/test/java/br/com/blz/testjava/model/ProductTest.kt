package br.com.blz.testjava.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ProductTest {
  private val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
  private val sku: Long = 43264

  @Test
  fun createModelProductTest() {
    val product = createProduct()

    assertEquals(sku, product.sku)
    assertEquals(
      productName,
      product.name
    )
    assertEquals(true, product.ismarketable)
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
