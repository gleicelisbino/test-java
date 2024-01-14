package br.com.blz.testjava.service

import br.com.blz.testjava.ProductMapper
import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.function.createProduct
import br.com.blz.testjava.function.createProductRequest
import br.com.blz.testjava.function.createProductResponse
import br.com.blz.testjava.function.updatedProduct
import br.com.blz.testjava.function.updatedProductRequest
import br.com.blz.testjava.function.updatedProductResponse
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.mock.mockito.MockBean

class ProductServiceTest {

  private val sku: Long = 43264

  @MockBean
  private lateinit var productRepository: ProductRepository

  @MockBean
  private lateinit var productService: ProductService

  @MockBean
  private lateinit var productMapper: ProductMapper

  private lateinit var mockProduct: Product
  private lateinit var updatedProduct: Product
  private lateinit var productRequest: ProductRequest
  private lateinit var updatedProductRequest: ProductRequest

  @BeforeEach
  fun setUp() {
    mockProduct = createProduct()
    updatedProduct = updatedProduct()
    productRequest = createProductRequest()
    updatedProductRequest = updatedProductRequest()

    productRepository = Mockito.mock(ProductRepository::class.java)
    productMapper = Mockito.mock(ProductMapper::class.java)

    productService = ProductService(productRepository, productMapper)

    `when`(productMapper.toProduct(productRequest)).thenReturn(mockProduct)
    `when`(productMapper.toProductResponse(mockProduct)).thenReturn(createProductResponse())
    `when`(productRepository.save(mockProduct)).thenReturn(mockProduct)
  }

  @Test
  fun createProductTest() {
    `when`(productService.createProduct(productRequest)).thenReturn(createProductResponse())

    val response = productService.createProduct(productRequest)

    assertEquals(createProductResponse(), response)
  }

  @Test
  fun productAlreadyExistsExceptionTest() {
    val mockProduct = createProduct()
    val productRequest = createProductRequest()

    `when`(productRepository.findBySku(sku)).thenReturn(mockProduct)

    val exception = assertFailsWith<ProductAlreadyExistsException> {
      productService.createProduct(productRequest)
    }

    assertEquals("The product with SKU: ${mockProduct.sku} already exists.", exception.message)
  }

  @Test
  fun productNotFoundExceptionTest() {
    val sku = 12345L

    val exception = assertFailsWith<ProductNotFoundException> {
      productService.getProductBySku(sku)
    }

    assertEquals("The product with SKU: $sku not found.", exception.message)
  }

  @Test
  fun updateProductTest() {
    val updatedProductRequest = updatedProductRequest()
    `when`(productRepository.findBySku(sku)).thenReturn(mockProduct)
    `when`(productMapper.toProduct(updatedProductRequest)).thenReturn(updatedProduct)
    `when`(productService.updateProduct(mockProduct.sku, updatedProductRequest())).thenReturn(updatedProductResponse())

    val updatedProduct = productService.updateProduct(updatedProductRequest.sku, updatedProductRequest)

    assertEquals(updatedProduct.sku, mockProduct.sku)
    assertEquals(updatedProduct.name, mockProduct.name)
    assertEquals(updatedProduct.isMarketable, false)
    assertEquals(updatedProduct.inventory.quantity, 0)
    assertEquals(updatedProduct.inventory.warehouses[0].quantity, 0)
  }

  @Test
  fun deleteProductBySkuTest() {
    productRepository.save(mockProduct)

    productService.deleteProductBySku(sku)

    `when`(productRepository.findBySku(sku)).thenReturn(null)
    val deletedProduct = productRepository.findBySku(sku)

    assertNull(deletedProduct)
  }
}
