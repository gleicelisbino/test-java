package br.com.blz.testjava.controller

import br.com.blz.testjava.function.createProductRequest
import br.com.blz.testjava.function.createProductResponse
import br.com.blz.testjava.service.ProductService
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ProductControllerTest {

  private lateinit var productService: ProductService
  private lateinit var productController: ProductController
  private val sku: Long = 43264

  @BeforeEach
  fun setUp() {
    productService = Mockito.mock(ProductService::class.java)
    productController = ProductController(productService)
  }

  @Test
  fun getProductBySku() {
    val productResponse = createProductResponse()
    Mockito.`when`(productService.getProductBySku(sku)).thenReturn(productResponse)

    val result = productController.getProductBySku(sku)

    assertEquals(productResponse, result)
  }

  @Test
  fun createProductTest() {
    val productRequest = createProductRequest()
    val productResponse = createProductResponse()
    Mockito.`when`(productService.createProduct(productRequest)).thenReturn(productResponse)

    val result = productController.createProduct(productRequest)

    assertEquals(productResponse, result)
  }

  @Test
  fun updateProductTest() {
    val productRequest = createProductRequest()
    val productResponse = createProductResponse()
    Mockito.`when`(productService.updateProduct(sku, productRequest)).thenReturn(productResponse)

    val result = productController.updateProduct(sku, productRequest)

    assertEquals(productResponse, result)
  }

  @Test
  fun deleteProductBySkuTest() {
    productController.deleteProductBySku(sku)

    Mockito.verify(productService).deleteProductBySku(sku)
  }
}
