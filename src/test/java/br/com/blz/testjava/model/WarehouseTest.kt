package br.com.blz.testjava.model

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class WarehouseTest {

  @Test
  fun createWarehouseTest() {
    val warehouse = Warehouse("SP", 12, "ECOMMERCE")

    assertEquals("SP", warehouse.locality)
    assertEquals(12, warehouse.quantity)
    assertEquals("ECOMMERCE", warehouse.type)
  }
}
