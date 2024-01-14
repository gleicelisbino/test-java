package br.com.blz.testjava.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InventoryTest {

  @Test
  fun createInventoryTest() {
    val warehouses = listOf(
        Warehouse("SP", 12, "ECOMMERCE"),
        Warehouse("MOEMA", 3, "PHYSICAL_STORE")
    )

    val inventory = Inventory(15, warehouses)

    assertEquals(15, inventory.quantity)
    assertEquals(warehouses, inventory.warehouses)
  }
}
