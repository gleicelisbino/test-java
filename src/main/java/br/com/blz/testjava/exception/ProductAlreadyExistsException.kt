package br.com.blz.testjava.exception

class ProductAlreadyExistsException(sku: Long) : RuntimeException("O produto com SKU: $sku já existe.")
