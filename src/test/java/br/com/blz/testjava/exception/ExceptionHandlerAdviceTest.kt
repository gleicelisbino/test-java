package br.com.blz.testjava.exception

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ExceptionHandlerAdviceTest {
  private val sku: Long = 43264

  private val exceptionHandlerAdvice = ExceptionHandlerAdvice()

  @Test
  fun handleProductAlreadyExistsExceptionTest() {
    val exceptionMessage = "The product with SKU: $sku already exists."
    val exception = ProductAlreadyExistsException(sku)

    val response = exceptionHandlerAdvice.handleException(exception)

    assertEquals(HttpStatus.CONFLICT, response.statusCode)
    assertEquals(exceptionMessage, response.body)
  }

  @Test
  fun handleProductNotFoundExceptionTest() {
    val exceptionMessage = "The product with SKU: $sku not found."
    val exception = ProductNotFoundException(sku)

    val response = exceptionHandlerAdvice.handleException(exception)

    assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    assertEquals(exceptionMessage, response.body)
  }
}
