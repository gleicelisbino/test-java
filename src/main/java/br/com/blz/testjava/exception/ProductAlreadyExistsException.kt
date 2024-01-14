package br.com.blz.testjava.exception

class ProductAlreadyExistsException(sku: Long) : RuntimeException("The product with SKU: $sku already exists.")
