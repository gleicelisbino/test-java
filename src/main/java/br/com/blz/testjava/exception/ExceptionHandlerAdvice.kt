package br.com.blz.testjava.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlerAdvice {

  @ExceptionHandler(ProductAlreadyExistsException::class)
  fun handleException(e: ProductAlreadyExistsException): ResponseEntity<String> {
    return ResponseEntity(e.message, HttpStatus.CONFLICT)
  }

  @ExceptionHandler(ProductNotFoundException::class)
  fun handleException(e: ProductNotFoundException): ResponseEntity<String> {
    return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
  }
}
