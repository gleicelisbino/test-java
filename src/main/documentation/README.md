## Backend Test Documentation - Boticário

### Tecnologias
* Back-end Kotlin e Java 8 <img alt="Kelly-Java" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-plain.svg">
* Maven
* RESTful
* Spring Boot 2.7.16
* Testes unitários com 90% de cobertura

### Features
- [X] Criação de produto (exceto as propriedades isMarketable e inventory.quantity).
- [X] Edição de produto por sku.
- [X] Recuperação de produto por sku.
- [X] Deleção de produto por sku.
- [X] Utilização de ktlint-maven-plugin, para formatação.
- [X] Utilização do MapStructure, com definição da interface Product Mapper para conversão

### Estrutura
- Controller: Definição de endpoints da API para realizar operações; criar, buscar, atualizar e deletar recursos 
(Gerenciamento as requisições e respostas HTTP).
- DTO: Foi utilizados para encapsular dados; formatando os requests e responses
possibilitando o controle dos dados, como remover quantity e marketable do Request.
- Exception: Definição das exceções personalizadas; ProductAlreadyExistsException e ProductNotFoundException.
- Model: Contém classes que representam as entidades do domínio.
- Repository: Define os métodos que vão ser utilizados no Service.
- Service: Lógica de negócios central e regras específicas do domínio, como o método calculateInventoryAndMarketable.

### Exemplo: Endpoint para adicionar produto
#### http://localhost:8080/products

#### Request - quantity e marketable são calculados, dessa forma o productRequest encapsula essa informação
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
#### Response - quantity e marketable foram calculados; quantity é a soma dos quantities de warehouses 
#### e marketable é true devido ao quantity ser maior que zero
```bash
{
    "sku": 43266,
    "name": "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    "inventory": {
        "quantity": 15,
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
    },
    "marketable": true
}
```

### Exemplo: Endpoint para atualizar produto
#### http://localhost:8080/products/43266
#### Request - quantity e marketable são calculados, dessa forma o productRequest encapsula essa informação
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

#### Response - soma dos quantities de warehouses igual a zero, resultando em marketable false 
```bash
{
    "sku": 43266,
    "name": "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    "inventory": {
        "quantity": 0,
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
    },
    "marketable": false
}
```

### Exemplo: Endpoint para recuperar o produto
#### http://localhost:8080/products/43266
#### Response - soma dos quantities de warehouses igual a zero, também resultando em marketable false
```bash
{
    "sku": 43266,
    "name": "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    "inventory": {
        "quantity": 0,
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
    },
    "marketable": false
}
```

### Exemplo: Endpoint para deletar o produto
#### http://localhost:8080/products/43266
