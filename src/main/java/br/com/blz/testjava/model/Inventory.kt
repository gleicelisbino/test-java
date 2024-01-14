package br.com.blz.testjava.model

data class Inventory(
  var quantity: Int? = 0,
  val warehouses: List<Warehouse>
)
//{
//  constructor() : this(0, emptyList())
//}
