package br.com.blz.testjava

import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MapStructConfig {
  @Bean
  open fun productMapper(): ProductMapper {
    return Mappers.getMapper(ProductMapper::class.java)
  }
}
