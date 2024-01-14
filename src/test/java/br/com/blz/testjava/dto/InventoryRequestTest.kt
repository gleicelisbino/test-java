package br.com.blz.testjava.dto

import br.com.blz.testjava.dto.requests.InventoryRequest
import br.com.blz.testjava.dto.requests.WarehouseRequest
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class InventoryRequestTest {

  @Test
  fun createInventoryRequestTest() {
    val warehouses = listOf(
      WarehouseRequest("SP", 12, "ECOMMERCE"),
      WarehouseRequest("MOEMA", 3, "PHYSICAL_STORE")
    )

    val inventory = InventoryRequest(warehouses)

    assertEquals(warehouses, inventory.warehouses)
  }
}
