# Refactoring — exemplo da locadora de filmes

Reimplementação em Java do exemplo clássico do livro *Refactoring: Improving the Design of Existing
Code*, de Martin Fowler. O código parte de um método `statement()` que faz tudo sozinho e passa por
uma sequência de refatorações, uma por commit, até chegar a classes coesas e a uma hierarquia de
preços polimórfica. O comportamento é o mesmo do começo ao fim, e isso é conferido por um teste de
regressão.

Projeto acadêmico de Engenharia de Software — Centro Universitário FAG.

**Autor:** Wellington Davies dos Santos.

## Arquitetura

```
 Main ──► Customer ──► Rental ──► Movie ──► Price (abstrata)
          relatórios   dias       título     ├── RegularPrice
          e totais     alugados   e preço    ├── ChildrensPrice
                                             └── NewReleasePrice
```

Cada classe responde só pelo que conhece: o cliente monta os relatórios e soma os totais, a locação
guarda os dias, o filme delega o cálculo para a sua estratégia de preço.

| Classe            | Responsabilidade                                                        |
| ----------------- | ----------------------------------------------------------------------- |
| `Customer`        | Guarda as locações e gera os relatórios em texto e em HTML              |
| `Rental`          | Liga um filme à quantidade de dias alugados                             |
| `Movie`           | Título do filme e qual estratégia de preço está em uso                  |
| `Price`           | Contrato da estratégia de preço e regra padrão de pontos (1 por locação) |
| `RegularPrice`    | R$ 2,00 até 2 dias, mais R$ 1,50 por dia extra                          |
| `ChildrensPrice`  | R$ 1,50 até 3 dias, mais R$ 1,50 por dia extra                          |
| `NewReleasePrice` | R$ 3,00 por dia; 2 pontos quando alugado por mais de 1 dia              |
| `Main`            | Monta um exemplo e imprime os dois relatórios                           |
| `VerificaSaida`   | Teste de regressão sem dependências                                     |

## Estrutura do repositório

```
src/
├── Main.java              exemplo de uso
├── Customer.java
├── Rental.java
├── Movie.java
├── Price.java             estratégia de preço (abstrata)
├── RegularPrice.java
├── ChildrensPrice.java
├── NewReleasePrice.java
└── VerificaSaida.java     teste de regressão
```

## Como executar

Requisito: JDK 8 ou mais recente.

```bash
javac -d out src/*.java
java -cp out Main            # imprime os relatórios
java -cp out VerificaSaida   # confere o comportamento (sai com código 1 se algo mudou)
```

Saída em texto:

```
Rental Record for Wellington Davies
	Matrix	3.5
	Vingadores: Ultimato	6.0
	Frozen	3.0
Amount owed is 12.5
You earned 4 frequent renter points
```

Saída em HTML:

```html
<h1>Rentals for <em>Wellington Davies</em></h1><p>
Matrix: 3.5<br>
Vingadores: Ultimato: 6.0<br>
Frozen: 3.0<br>
<p>You owe <em>12.5</em><p>
On this rental you earned <em>4</em> frequent renter points<p>
```

## Histórico de refatorações

| #  | Refatoração                       | O que mudou                                                                      |
| -- | --------------------------------- | -------------------------------------------------------------------------------- |
| 1  | Estrutura inicial                 | `Movie`, `Rental`, `Customer` e `Main`, com toda a lógica em `statement()`       |
| 2  | Extract Method                    | Cálculo de cada locação vai para `amountFor()`                                   |
| 3  | Rename Variable                   | `each` vira `aRental`                                                            |
| 4  | Move Method                       | `amountFor()` vira `getCharge()` em `Rental`                                     |
| 5  | Replace Temp with Query           | Some a variável `thisAmount`                                                     |
| 6  | Extract Method                    | Pontos de fidelidade vão para `Rental.getFrequentRenterPoints()`                 |
| 7  | Replace Temp with Query           | `totalAmount` vira `getTotalCharge()`                                            |
| 8  | Replace Temp with Query           | `frequentRenterPoints` vira `getTotalFrequentRenterPoints()`                     |
| 9  | Novo recurso                      | `htmlStatement()` reaproveita os métodos extraídos                               |
| 10 | Move Method                       | `getCharge()` e `getFrequentRenterPoints()` passam para `Movie`                  |
| 11 | Replace Type Code with State/Strategy | O `switch` por código de preço vira a hierarquia `Price`                     |
| 12 | Coleções genéricas                | `Vector`/`Enumeration` viram `List<Rental>`, for-each e streams                  |

Os passos 10 e 11 são os que fecham o capítulo 1 do livro: depois deles, criar um novo tipo de
filme é só criar uma nova subclasse de `Price`, sem mexer em `Customer` nem em `Rental`.

## Limitações

- Os valores usam `double`, como no livro; um sistema real usaria `BigDecimal` para dinheiro.
- `VerificaSaida` é um teste em `main` para não depender de Maven nem de JUnit.
- As constantes `Movie.REGULAR`, `CHILDRENS` e `NEW_RELEASE` foram mantidas para não mudar a forma
  de criar filmes usada no `Main`.
