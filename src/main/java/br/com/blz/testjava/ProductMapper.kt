package br.com.blz.testjava

import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.dto.responses.ProductResponse
import br.com.blz.testjava.model.Product
import org.mapstruct.Mapper

@Mapper
interface ProductMapper {

  fun toProductResponse(product: Product): ProductResponse

  fun toProduct(productRequest: ProductRequest): Product
}
