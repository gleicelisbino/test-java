package br.com.blz.testjava.repository

class ProductNotFoundException(sku: Long) :
  IllegalArgumentException("The product with SKU: $sku not found.")
