public class Main {
    public static void main(String[] args) {

        // Criação dos filmes
        Movie filme1 = new Movie("Matrix", Movie.REGULAR);
        Movie filme2 = new Movie("Vingadores: Ultimato", Movie.NEW_RELEASE);
        Movie filme3 = new Movie("Frozen", Movie.CHILDRENS);

        // Criação das locações
        Rental aluguel1 = new Rental(filme1, 3); // 3 dias
        Rental aluguel2 = new Rental(filme2, 2); // 2 dias
        Rental aluguel3 = new Rental(filme3, 4); // 4 dias

        // Criação do cliente
        Customer cliente = new Customer("Wellington Davies");

        // Adicionando as locações ao cliente
        cliente.addRental(aluguel1);
        cliente.addRental(aluguel2);
        cliente.addRental(aluguel3);

        // Exibindo o relatório em texto
        System.out.println(cliente.statement());

        // Exibindo o relatório em HTML
        System.out.println("\nHTML Statement:\n" + cliente.htmlStatement());
    }
}
