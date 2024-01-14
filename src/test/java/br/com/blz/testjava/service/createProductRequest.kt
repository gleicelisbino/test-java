package br.com.blz.testjava.service

import br.com.blz.testjava.dto.requests.InventoryRequest
import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.dto.requests.WarehouseRequest
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse

private val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
private val sku: Long = 43264

fun createProduct(): Product {
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
    isMarketable = true
  )
}

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
