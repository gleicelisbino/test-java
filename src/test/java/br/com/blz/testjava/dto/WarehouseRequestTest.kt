package br.com.blz.testjava.dto

import br.com.blz.testjava.dto.requests.WarehouseRequest
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class WarehouseRequestTest {
  @Test
  fun createWarehouseRequestTest() {
    val warehouse = WarehouseRequest("SP", 12, "ECOMMERCE")

    assertEquals("SP", warehouse.locality)
    assertEquals(12, warehouse.quantity)
    assertEquals("ECOMMERCE", warehouse.type)
  }
}
