package td3;

public class Accounts {
    public interface Payable {
        double getPaymentAmount();
    }

    public static class Invoice implements Payable{
        private final String partNumber;
        private final String partDescription;
        private int quantity;
        private double pricePerItem;

        public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem){
            this.partNumber = partNumber;
            this.partDescription = partDescription;
            this.quantity = quantity;
            this.pricePerItem = pricePerItem;
        }

        public String getPartNumber(){
            return this.partNumber;
        }

        public String getPartDescription(){
            return this.partDescription;
        }

        public int getQuantity(){
            return this.quantity;
        }

        public void setQuantity(int quantity){
            this.quantity = quantity;
        }

        public double getPricePerItem(){
            return this.pricePerItem;
        }

        public void setPricePerItem(double pricePerItem){
            this.pricePerItem = pricePerItem;
        }

        public double getPaymentAmount(){
            return this.quantity * this.pricePerItem;
        }

        public String toString(){
            return String.format("Invoice: %s (%s) - %d * %.2f = %.2f", this.partNumber, this.partDescription, this.quantity, this.pricePerItem, this.getPaymentAmount());
        }
    }

    public static class Employee implements Payable{
        private final String firstName;
        private final String lastName;
        private final String socialSecurityNumber;
        private double grossSales;
        private double commissionRate;

        public Employee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate){
            this.firstName = firstName;
            this.lastName = lastName;
            this.socialSecurityNumber = socialSecurityNumber;
        }

        public String getFirstName(){
            return this.firstName;
        }

        public String getLastName(){
            return this.lastName;
        }

        public String getSocialSecurityNumber(){
            return this.socialSecurityNumber;
        }

        public double getPaymentAmount(){
            return 0;
        }

        public String toString(){
            return String.format("%s %s - %s", this.firstName, this.lastName, this.socialSecurityNumber);
        }
    }

    public static class SalariedEmployee extends Employee{
        private double weeklySalary;

        public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary){
            super(firstName, lastName, socialSecurityNumber, 0, 0);
            this.weeklySalary = weeklySalary;
        }

        public double getWeeklySalary(){
            return this.weeklySalary;
        }

        public void setWeeklySalary(double weeklySalary){
            this.weeklySalary = weeklySalary;
        }

        public double getPaymentAmount(){
            return this.weeklySalary;
        }

        public String toString(){
            return String.format("Salaried employee: %s - %.2f", super.toString(), this.weeklySalary);
        }
    }

}
