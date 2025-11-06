import java.util.Enumeration;
import java.util.Vector;

public class Customer {
   private String _name;
   private Vector _rentals = new Vector();

   public Customer (String name){
      _name = name;
   }

   public void addRental(Rental arg) {
      _rentals.addElement(arg);
   }

   public String getName (){
      return _name;
   }

   public String statement() {
      int frequentRenterPoints = 0;
      Enumeration rentals = _rentals.elements();
      String result = "Rental Record for " + getName() + "\n";

      while (rentals.hasMoreElements()) {
         Rental aRental = (Rental) rentals.nextElement();

         // add frequent renter points
         frequentRenterPoints += aRental.getFrequentRenterPoints();

         // show figures for this rental
         result += "\t" + aRental.getMovie().getTitle() + "\t" +
             String.valueOf(aRental.getCharge()) + "\n";
      }

      // add footer lines
      result +=  "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
      result += "You earned " + String.valueOf(frequentRenterPoints) +
              " frequent renter points";
      return result;
   }

   // novo método: soma o total de todos os aluguéis
   private double getTotalCharge() {
      double result = 0;
      Enumeration rentals = _rentals.elements();
      while (rentals.hasMoreElements()) {
         Rental aRental = (Rental) rentals.nextElement();
         result += aRental.getCharge();
      }
      return result;
   }
}
