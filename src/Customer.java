import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental rental : rentals) {
            result.append("\t").append(rental.getMovie().getTitle())
                  .append("\t").append(rental.getCharge()).append("\n");
        }

        result.append("Amount owed is ").append(getTotalCharge()).append("\n");
        result.append("You earned ").append(getTotalFrequentRenterPoints())
              .append(" frequent renter points");
        return result.toString();
    }

    public String htmlStatement() {
        StringBuilder result = new StringBuilder("<h1>Rentals for <em>" + getName() + "</em></h1><p>\n");

        for (Rental rental : rentals) {
            result.append(rental.getMovie().getTitle()).append(": ")
                  .append(rental.getCharge()).append("<br>\n");
        }

        result.append("<p>You owe <em>").append(getTotalCharge()).append("</em><p>\n");
        result.append("On this rental you earned <em>").append(getTotalFrequentRenterPoints())
              .append("</em> frequent renter points<p>");
        return result.toString();
    }

    private double getTotalCharge() {
        return rentals.stream().mapToDouble(Rental::getCharge).sum();
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
    }
}
