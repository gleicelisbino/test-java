package br.com.blz.testjava.dto

import br.com.blz.testjava.function.createProductRequest
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class ProductRequestTest {
  private val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
  private val sku: Long = 43264

  @Test
  fun productRequestTest() {
    val product = createProductRequest()

    assertEquals(sku, product.sku)
    assertEquals(
      productName,
      product.name
    )
  }
}
