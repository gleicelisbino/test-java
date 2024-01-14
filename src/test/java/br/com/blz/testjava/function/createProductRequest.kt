package br.com.blz.testjava.function

import br.com.blz.testjava.dto.requests.InventoryRequest
import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.dto.requests.WarehouseRequest

private const val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
private const val sku: Long = 43264

fun createProductRequest(): ProductRequest {
  return ProductRequest(
    sku = sku,
    name = productName,
    inventory = InventoryRequest(
      warehouses = listOf(
        WarehouseRequest("SP", 3, "ECOMMERCE"),
        WarehouseRequest("MOEMA", 12, "PHYSICAL_STORE")
      )
    )
  )
}

fun updatedProductRequest(): ProductRequest {
  return ProductRequest(
    sku = sku,
    name = productName,
    inventory = InventoryRequest(
      warehouses = listOf(
        WarehouseRequest("RJ", 0, "ECOMMERCE"),
        WarehouseRequest("PR", 0, "PHYSICAL_STORE")
      )
    )
  )
}
