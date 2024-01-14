package br.com.blz.testjava.dto.requests

data class WarehouseRequest(
  val locality: String,
  val quantity: Int,
  val type: String
)
