package br.com.blz.testjava.service

import br.com.blz.testjava.dto.responses.ProductResponse
import br.com.blz.testjava.dto.responses.ProductResponse.Inventory
import br.com.blz.testjava.dto.responses.ProductResponse.Inventory.Warehouse

private val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
private val sku: Long = 43264

  fun createProductResponse(): ProductResponse {
    return ProductResponse(
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
