package br.com.blz.testjava.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WarehouseTest {

  @Test
  fun createWarehouseTest() {
    val warehouse = Warehouse("SP", 12, "ECOMMERCE")

    assertEquals("SP", warehouse.locality)
    assertEquals(12, warehouse.quantity)
    assertEquals("ECOMMERCE", warehouse.type)
  }
}
