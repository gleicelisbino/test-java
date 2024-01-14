package br.com.blz.testjava.model

data class Product(
  val sku: Long,
  val name: String,
  val inventory: Inventory,
  val isMarketable: Boolean? = null
)
//{
//  constructor() : this(0, "", Inventory(), false)
//}
