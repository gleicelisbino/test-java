package br.com.blz.testjava.repository

class ProductAlreadyExistsException(sku: Long) :
  IllegalArgumentException("The product with SKU: $sku already exists.")
