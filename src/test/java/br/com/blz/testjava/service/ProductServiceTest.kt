package br.com.blz.testjava.service

import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.repository.ProductAlreadyExistsException
import br.com.blz.testjava.repository.ProductNotFoundException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse
import br.com.blz.testjava.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProductServiceTest {

  private val productName: String = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"
  private val sku: Long = 43264

  @MockBean
  lateinit var productRepository: ProductRepository

  @MockBean
  private lateinit var productService: ProductService

  @BeforeEach
  fun setUp() {
    productRepository = ProductRepository()
    productService = ProductService(productRepository)
  }

  @Test
  fun createProductTest() {
    val product = createProductRequest()

    val createdProduct = productService.createProduct(product)

    assertEquals(product.sku, createdProduct.sku)
  }

  @Test
  fun createProductWithSKUTest() {
    val product = createProductRequest()
    productRepository.save(createProduct())

    val exception = assertFailsWith<ProductAlreadyExistsException> {
      productService.createProduct(product)
    }

    assertEquals("The product with SKU: ${product.sku} already exists.", exception.message)
  }

  @Test
  fun getProductBySkuTest() {
    val product1 = createProductRequest()
    productService.createProduct(product1)

    val product2 = productService.getProductBySku(product1.sku)

    assertEquals(product1.sku, product2.sku)
  }

  @Test
  fun getProductBySkuWithoutSKUTest() {
    val sku = 12345L

    val exception = assertFailsWith<ProductNotFoundException> {
      productService.getProductBySku(sku)
    }

    assertEquals("The product with SKU: $sku not found.", exception.message)
  }

  @Test
  fun updateProductTest() {
    val product = createProductRequest()
    productService.createProduct(product)
    val updatedProduct = productService.updateProduct(product.sku, product)

    assertEquals(product.sku, updatedProduct.sku)
  }

  @Test
  fun deleteProductBySkuTest() {
    val product = createProductRequest()
    productRepository.save(createProduct())

    productService.deleteProductBySku(product.sku)

    val retrievedProduct = productRepository.findBySku(product.sku)

    assertNull(retrievedProduct)
  }

  private fun createProduct(): Product {
    return Product(
      sku = sku,
      name = productName,
      inventory = Inventory(
        quantity = 15,
        warehouses = listOf(
          Warehouse("SP", 3, "ECOMMERCE"),
          Warehouse("MOEMA", 12, "PHYSICAL_STORE")
        )
      ),
      isMarketable = true
    )
  }
  private fun createProductRequest(): ProductRequest {
    return ProductRequest(
      sku = sku,
      name = productName,
      inventory = Inventory(
        warehouses = listOf(
          Warehouse("SP", 3, "ECOMMERCE"),
          Warehouse("MOEMA", 12, "PHYSICAL_STORE")
        )
      )
    )
  }
}

