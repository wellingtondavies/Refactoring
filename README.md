# 🎬 Refactoring - Exemplo de Locadora (Movie Rental)

Projeto baseado no exemplo clássico do livro **“Refactoring: Improving the Design of Existing Code”**, de **Martin Fowler**, reimplementado em **Java** com commits organizados e documentados passo a passo.

O objetivo é demonstrar **boas práticas de refatoração** em código orientado a objetos, mantendo o mesmo comportamento, mas tornando o código mais limpo, reutilizável e fácil de manter.

---

## 🚀 Estrutura do Projeto

| Classe | Função |
|--------|--------|
| **Movie** | Representa um filme e seu tipo de preço (Regular, Infantil ou Novo Lançamento). |
| **Rental** | Representa uma locação, associando um filme e a quantidade de dias alugados. |
| **Customer** | Representa o cliente e gera os relatórios de locações (texto e HTML). |
| **Main** | Classe principal que cria objetos, executa os testes e exibe os resultados. |

---

## 🧩 Histórico de Refatorações (Commits)

### **Commit 1 – Criar estrutura inicial**
Criação das classes base: `Movie`, `Rental`, `Customer` e `Main`, com o método `statement()` original contendo toda a lógica dentro de `Customer`.

### **Commit 2 – Extract Method (amountFor)**
Extraiu o cálculo de cada locação para um novo método `amountFor()`, isolando a lógica de cobrança e melhorando a legibilidade.

### **Commit 3 – Rename Variable (each → aRental)**
Substituiu variáveis com nomes genéricos (`each`) por nomes descritivos (`aRental`), tornando o código mais fácil de entender.

### **Commit 4 – Move Method (amountFor → getCharge em Rental)**
Moveu o cálculo de valor da locação para a classe `Rental` (`getCharge()`), aplicando o princípio de **cohesão** e **encapsulamento**.

### **Commit 5 – Replace Temp with Query (thisAmount → uso direto de getCharge)**
Removeu a variável temporária `thisAmount` e passou a chamar `getCharge()` diretamente, reduzindo complexidade.

### **Commit 6 – Extract Method (frequent renter points → getFrequentRenterPoints)**
Criou o método `getFrequentRenterPoints()` em `Rental`, encapsulando a regra de pontos de fidelidade dentro da classe correta.

### **Commit 7 – Replace Temp with Query (totalAmount → getTotalCharge)**
Criou o método `getTotalCharge()` em `Customer`, eliminando a variável temporária e deixando o método `statement()` mais limpo.

### **Commit 8 – Replace Temp with Query (frequentRenterPoints → getTotalFrequentRenterPoints)**
Criou o método `getTotalFrequentRenterPoints()` em `Customer`, aplicando o mesmo princípio do commit anterior.

### **Commit 9 – Add htmlStatement()**
Adicionou o método `htmlStatement()`, que gera o relatório de locações em formato HTML, reutilizando os métodos refatorados.

---

## 💡 Resultado Final

### 📄 Saída em texto:
```
Rental Record for Wellington Davies
	Matrix	3.5
	Vingadores: Ultimato	6.0
	Frozen	4.5
Amount owed is 14.0
You earned 4 frequent renter points
```

### 🌐 Saída em HTML:
```html
<h1>Rentals for <em>Wellington Davies</em></h1><p>
Matrix: 3.5<br>
Vingadores: Ultimato: 6.0<br>
Frozen: 4.5<br>
<p>You owe <em>14.0</em><p>
On this rental you earned <em>4</em> frequent renter points<p>
```

---

## ⚙️ Como Executar

### 1️⃣ Compilar
```bash
javac *.java
```

### 2️⃣ Executar
```bash
java Main
```

---

## 🧠 Conceitos de Refatoração Aplicados

- **Extract Method** → Quebra grandes blocos de código em métodos menores e reutilizáveis.  
- **Move Method** → Move comportamentos para a classe mais apropriada.  
- **Replace Temp with Query** → Remove variáveis temporárias substituindo-as por métodos.  
- **Encapsulamento e coesão** → Cada classe é responsável apenas por sua própria lógica.  
- **Clareza e manutenibilidade** → Código mais legível, testável e preparado para evoluções.

---

## 🧑‍💻 Autor

**Wellington Davies**  
Estudante de Engenharia de Software  
Apaixonado por tecnologia, refatoração e boas práticas de código limpo 💡  
