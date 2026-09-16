# Roteiro da Apresentação — 10 minutos

## 1. Problema
A instituição precisava controlar reservas de equipamentos, evitando conflitos de equipamentos, salas e horários.

## 2. Solução
Criamos uma API REST com Java e Spring Boot. A arquitetura separa Controller, Service, Repository, Entity e DTO.

## 3. Entidades
- Equipamento: id, nome, tipo e ativo.
- Reserva: professor, curso, sala, data, retirada, entrega e equipamentos.

## 4. Como funciona
O professor envia uma reserva. O Service valida todas as regras antes de salvar.

## 5. Regras
- 7 dias de antecedência.
- Retirada anterior à entrega.
- Pelo menos um equipamento.
- Equipamento precisa estar ativo.
- Não pode haver conflito de equipamento.
- Não pode haver conflito de sala.

## 6. Testes
JUnit + Mockito verificam principalmente antecedência, horário e equipamento inativo.

## 7. Três melhorias
1. Sistema de reservas no lugar da API simples de produtos.
2. Regras de negócio centralizadas no Service.
3. Validação, tratamento de exceções e respostas padronizadas.

## 8. Principal desafio
Garantir que uma reserva só seja criada quando todas as regras forem atendidas simultaneamente.

## Tecnologias
Java 17, Spring Boot, Spring Web, Spring Data JPA, Lombok, H2, Oracle, JUnit e Mockito.
