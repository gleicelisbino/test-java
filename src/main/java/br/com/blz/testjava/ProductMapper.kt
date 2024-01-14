package br.com.blz.testjava

import br.com.blz.testjava.dto.requests.ProductRequest
import br.com.blz.testjava.dto.responses.ProductResponse
import br.com.blz.testjava.model.Product
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.beans.factory.annotation.Qualifier

@Qualifier("productMapperQualifier")
@Mapper
interface ProductMapper {
  @Mapping(source = "ismarketable", target = "isMarketable")
  fun toProductResponse(product: Product): ProductResponse

  fun toProduct(productRequest: ProductRequest): Product
}
