package br.com.blz.testjava.controller

import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.dto.responses.ProductResponse
import br.com.blz.testjava.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

  @GetMapping("/{sku}")
  fun getProductBySku(@PathVariable sku: Long): ProductResponse {
    return productService.getProductBySku(sku)
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createProduct(@RequestBody product: ProductRequest): ProductResponse {
    return productService.createProduct(product)
  }

  @PutMapping("/{sku}")
  fun updateProduct(@PathVariable sku: Long, @RequestBody product: ProductRequest): ProductResponse {
    return productService.updateProduct(sku, product)
  }

  @DeleteMapping("/{sku}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteProductBySku(@PathVariable sku: Long) {
    productService.deleteProductBySku(sku)
  }
}
