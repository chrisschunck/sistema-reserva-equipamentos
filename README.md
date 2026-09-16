# Sistema de Reserva de Equipamentos

API REST para reservas de equipamentos acadêmicos.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Lombok
- Bean Validation
- H2 para testes/desenvolvimento
- Oracle para produção
- JUnit 5 + Mockito

## Regras implementadas

1. A reserva deve ter no mínimo 7 dias de antecedência.
2. Horário de retirada deve ser anterior ao horário de entrega.
3. Retirada igual à entrega é rejeitada.
4. Apenas equipamentos ativos podem ser reservados.
5. Um equipamento não pode ter duas reservas com horários conflitantes.
6. Uma sala não pode ter duas reservas com horários conflitantes.
7. Uma reserva deve possuir pelo menos um equipamento.
8. Campos obrigatórios possuem validações como `@NotBlank`, `@NotNull` e `@Size`.
9. Respostas dos controllers são padronizadas com `ApiResponse`.
10. Endpoints possuem documentação com `@Operation`.

## Executar

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Endpoints

### Equipamentos

- `POST /equipamentos`
- `GET /equipamentos`
- `GET /equipamentos/{id}`
- `PUT /equipamentos/{id}`
- `DELETE /equipamentos/{id}`

### Reservas

- `POST /reservas`
- `GET /reservas`
- `GET /reservas/{id}`
- `DELETE /reservas/{id}`

## Exemplo de equipamento

```json
{
  "nome": "Datashow 01",
  "tipo": "Datashow",
  "ativo": true
}
```

## Exemplo de reserva

```json
{
  "professor": "João da Silva",
  "curso": "Engenharia de Software",
  "sala": "204",
  "data": "2026-09-10",
  "horarioRetirada": "18:30",
  "horarioEntrega": "22:30",
  "equipamentosIds": [1, 2]
}
```

> Para testar a regra dos 7 dias, informe uma data pelo menos 7 dias à frente da data atual.

## Testes

```bash
mvn test
```

## Oracle

O arquivo `application-prod.properties` está preparado para variáveis de ambiente:

```text
ORACLE_URL
ORACLE_USERNAME
ORACLE_PASSWORD
```

Ative o perfil de produção com:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
