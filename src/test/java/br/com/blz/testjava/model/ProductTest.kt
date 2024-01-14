package br.com.blz.testjava.model

import br.com.blz.testjava.function.createProduct
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

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
    assertEquals(
      15,
      product.inventory.quantity
    )
    assertEquals(true, product.ismarketable)
  }
}
