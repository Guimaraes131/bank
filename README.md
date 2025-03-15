# URLs

## GET
- http://localhost:8080/conta 
- http://localhost:8080/conta/{id}
- http://localhost:8080/ -> usada para mostrar os membros do grupo

## POST
- http://localhost:8080/conta
- http://localhost:8080/conta/deposito
- http://localhost:8080/conta/saque
- http://localhost:8080/conta/pix

## DEL
- http://localhost:8080/conta/{id}


# JSONs
### Exemplos de Json para facilitar o uso:

```json
{
  "numero": 123456,
  "agencia": 123,
  "nomeTitular": "Tiago",
  "cpf": "75013753058",
  "dataAbertura": "2024-03-15",
  "saldoInicial": 0,
  "ativa": true,
  "tipoConta": "Corrente"
}
```

```json
{
  "numero": 789012,
  "agencia": 456,
  "nomeTitular": "Daniel",
  "cpf": "08803974032",
  "dataAbertura": "2021-06-23",
  "saldoInicial": 0,
  "ativa": true,
  "tipoConta": "Salario"
}
```

```json
{
  "idConta": 1,
  "valorDeposito": 200
}
```

```json
{
  "idConta": 1,
  "valorSaque": 100
}
```

```json
{
  "idContaOrigem": 1,
  "idContaDestino": 2,
  "valorPix": 10
}
```