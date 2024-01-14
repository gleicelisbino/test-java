package br.com.blz.testjava.function

import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse

private const val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
private const val sku: Long = 43264

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
    ismarketable = true
  )
}

fun updatedProduct(): Product {
  return Product(
    sku = sku,
    name = productName,
    inventory = Inventory(
      quantity = 0,
      warehouses = listOf(
        Warehouse("RJ", 0, "ECOMMERCE"),
        Warehouse("PR", 0, "PHYSICAL_STORE")
      )
    ),
    ismarketable = false
  )
}
