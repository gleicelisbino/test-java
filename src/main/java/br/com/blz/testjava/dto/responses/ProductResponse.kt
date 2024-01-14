package br.com.blz.testjava.dto.responses

data class ProductResponse(
  val sku: Long,
  val name: String,
  val inventory: Inventory,
  val isMarketable: Boolean
) {
  data class Inventory(
    val quantity: Int,
    val warehouses: List<Warehouse>
  ) {
    data class Warehouse(
      val locality: String,
      val quantity: Int,
      val type: String
    )
  }
}
