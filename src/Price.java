/**
 * Estratégia de cobrança de um filme (Replace Type Code with State/Strategy).
 * Cada subclasse conhece a própria regra de preço, e a regra padrão de pontos
 * fica aqui para ser sobrescrita só quando necessário.
 */
public abstract class Price {

    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
