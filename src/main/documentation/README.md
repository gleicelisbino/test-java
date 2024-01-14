## Backend Test Documentation - Boticário

### Tecnologias
* Back-end Kotlin e Java 8 <img alt="Kelly-Java" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-plain.svg">
* Maven
* RESTful
* Spring Boot 2+
* Testes unitários com 90% de cobertura

### Features
- [X] Criação de produto (exceto as propriedades isMarketable e inventory.quantity).
- [X] Edição de produto por sku.
- [X] Recuperação de produto por sku.
- [X] Deleção de produto por sku.
- [X] Utilização de ktlint-maven-plugin, para formatação.

### Estrutura
- Controller: Define endpoints para operações.
- DTO: Contém os requests e responses, possibilitando a montagem de estruturas específicas.
- Exception: Exceções ProductAlreadyExists e ProductNotFound
- Model: Contém as entidades
- Repository: Define os métodos que vão ser utilizados no Service
- Service: Lógica de negócios

### Exemplo: Endpoint para adicionar produto
#### http://localhost:8080/products
```bash
{
    "sku": 43266,
    "name": "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    "inventory": {
        "warehouses": [
            {
                "locality": "SP",
                "quantity": 12,
                "type": "ECOMMERCE"
            },
            {
                "locality": "MOEMA",
                "quantity": 3,
                "type": "PHYSICAL_STORE"
            }
        ]
    }
}
```
### Exemplo: Endpoint para atualizar produto
#### http://localhost:8080/products/43266
```bash
{
    "sku": 43266,
    "name": "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    "inventory": {
        "warehouses": [
            {
                "locality": "SP",
                "quantity": 0,
                "type": "ECOMMERCE"
            },
            {
                "locality": "MOEMA",
                "quantity": 0,
                "type": "PHYSICAL_STORE"
            }
        ]
    }
}
```
### Exemplo: Endpoint para recuperar o produto
#### http://localhost:8080/products/43266

### Exemplo: Endpoint para deletar o produto
#### http://localhost:8080/products/43266
