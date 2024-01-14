package br.com.blz.testjava.dto.requests

data class InventoryRequest(
  val warehouses: List<WarehouseRequest> = emptyList()
)
