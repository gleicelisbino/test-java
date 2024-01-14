package br.com.blz.testjava.model

data class Product(
  val sku: Long,
  val name: String,
  val inventory: Inventory,
  val ismarketable: Boolean
)

