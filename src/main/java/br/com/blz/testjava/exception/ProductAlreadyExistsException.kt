package br.com.blz.testjava.exception

class ProductAlreadyExistsException(sku: Long) :
  IllegalArgumentException("The product with SKU: $sku already exists.")
