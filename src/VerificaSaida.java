/**
 * Teste de regressão sem dependências: confere se as refatorações mantiveram
 * exatamente o mesmo comportamento do código original.
 *
 * Execução: java -cp out VerificaSaida   (sai com código 1 se algo mudou)
 */
public class VerificaSaida {

    private static int falhas = 0;

    public static void main(String[] args) {
        Customer cliente = new Customer("Wellington Davies");
        cliente.addRental(new Rental(new Movie("Matrix", Movie.REGULAR), 3));
        cliente.addRental(new Rental(new Movie("Vingadores: Ultimato", Movie.NEW_RELEASE), 2));
        cliente.addRental(new Rental(new Movie("Frozen", Movie.CHILDRENS), 4));

        confere("relatório em texto", cliente.statement(),
                "Rental Record for Wellington Davies\n"
                + "\tMatrix\t3.5\n"
                + "\tVingadores: Ultimato\t6.0\n"
                + "\tFrozen\t3.0\n"
                + "Amount owed is 12.5\n"
                + "You earned 4 frequent renter points");

        confere("relatório em HTML", cliente.htmlStatement(),
                "<h1>Rentals for <em>Wellington Davies</em></h1><p>\n"
                + "Matrix: 3.5<br>\n"
                + "Vingadores: Ultimato: 6.0<br>\n"
                + "Frozen: 3.0<br>\n"
                + "<p>You owe <em>12.5</em><p>\n"
                + "On this rental you earned <em>4</em> frequent renter points<p>");

        // Regras de preço nos limites de cada faixa
        confere("regular, 2 dias", new RegularPrice().getCharge(2), 2.0);
        confere("regular, 4 dias", new RegularPrice().getCharge(4), 5.0);
        confere("infantil, 3 dias", new ChildrensPrice().getCharge(3), 1.5);
        confere("infantil, 5 dias", new ChildrensPrice().getCharge(5), 4.5);
        confere("lançamento, 1 dia", new NewReleasePrice().getCharge(1), 3.0);
        confere("pontos lançamento, 1 dia", new NewReleasePrice().getFrequentRenterPoints(1), 1);
        confere("pontos lançamento, 2 dias", new NewReleasePrice().getFrequentRenterPoints(2), 2);
        confere("pontos regular, 5 dias", new RegularPrice().getFrequentRenterPoints(5), 1);

        // Trocar o tipo do filme troca a estratégia de preço
        Movie filme = new Movie("Filme", Movie.REGULAR);
        filme.setPriceCode(Movie.NEW_RELEASE);
        confere("troca de código de preço", filme.getCharge(2), 6.0);

        if (falhas > 0) {
            System.out.println(falhas + " verificação(ões) falharam.");
            System.exit(1);
        }
        System.out.println("Todas as verificações passaram.");
    }

    private static void confere(String nome, Object obtido, Object esperado) {
        if (esperado.equals(obtido)) {
            System.out.println("OK    " + nome);
        } else {
            falhas++;
            System.out.println("FALHA " + nome + "\n  esperado: " + esperado + "\n  obtido:   " + obtido);
        }
    }
}
