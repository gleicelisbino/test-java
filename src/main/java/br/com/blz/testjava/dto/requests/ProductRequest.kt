package br.com.blz.testjava.dto.requests

data class ProductRequest(
  val sku: Long,
  val name: String,
  val inventory: InventoryRequest
)
