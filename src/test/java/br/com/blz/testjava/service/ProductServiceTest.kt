package br.com.blz.testjava.service

import br.com.blz.testjava.ProductMapper
import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductAlreadyExistsException
import br.com.blz.testjava.repository.ProductNotFoundException
import br.com.blz.testjava.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@SpringBootTest
class ProductServiceTest {

  private val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
  private val sku: Long = 43264

  @MockBean
  private lateinit var productRepository: ProductRepository

  @MockBean
  private lateinit var productService: ProductService

  @MockBean
  private lateinit var productMapper: ProductMapper

  private lateinit var mockProduct: Product
  private lateinit var productRequest: ProductRequest

  @BeforeEach
  fun setUp() {
    mockProduct = createProduct()
    productRequest = createProductRequest()

    productRepository = Mockito.mock(ProductRepository::class.java)
    productMapper = Mockito.mock(ProductMapper::class.java)

    productService = ProductService(productRepository,productMapper)

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

    assertEquals("The product with SKU: ${productRequest.sku} already exists.", exception.message)
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
    `when`(productRepository.findBySku(sku)).thenReturn(mockProduct)
    `when`(productService.updateProduct(productRequest.sku, productRequest)).thenReturn(createProductResponse())

    val updatedProduct = productService.updateProduct(productRequest.sku, productRequest)

    assertEquals(mockProduct.sku, updatedProduct.sku)
  }

  @Test
  fun deleteProductBySkuTest() {
    productRepository.save(mockProduct)

    productService.deleteProductBySku(sku)

    `when`(productRepository.findBySku(sku)).thenReturn(null)
    val retrievedProduct = productRepository.findBySku(sku)

    assertNull(retrievedProduct)
  }
}

