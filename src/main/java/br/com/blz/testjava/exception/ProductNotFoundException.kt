package br.com.blz.testjava.exception

class ProductNotFoundException(sku: Long) :
  IllegalArgumentException("The product with SKU: $sku not found.")
