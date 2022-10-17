package td2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Cars {
    public static class Car {
        private final String manufacturer;
        private final Date manDate;
        private final float acquisitionPrice;
        private float salePrice;
        private int condition;

        public Car(int seriesNumber, String manufacturer, Date manDate, Date acquisitionDate, float acquisitionPrice, float salePrice, int condition) {
            this.manufacturer = manufacturer;
            this.manDate = manDate;
            this.acquisitionPrice = acquisitionPrice;
            this.salePrice = salePrice;
            this.condition = condition;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public Date getManDate() {
            return manDate;
        }

        public float getAcquisitionPrice() {
            return acquisitionPrice;
        }

        public float getSalePrice() {
            return salePrice;
        }

        public String getCondition() {
            String[] conds = new String[] {"GOOD", "AVERAGE", "BAD"};
            return conds[condition - 1];
        }

        public void setSalePrice(float salePrice) {
            this.salePrice = salePrice;
        }

        public void setCondition(int condition) {
            this.condition = condition;
        }

        public String toString() {
            return "{Manufacturer: " + manufacturer + ", Manufacture date: " + manDate + ", Acquisition price: " + acquisitionPrice + ", Sale price: " + salePrice + ", Condition: " + getCondition()+"}";
        }

        public void applyDiscount(float discount, int condition) {
            if (this.condition == condition) {
                salePrice *= (1 - discount/100);
            }
        }
    }

    public static class Fleet{
        private List<Car> cars;
        private int nbCars;

        public Fleet(Car[] cars){
            this.cars = new ArrayList<>();
            addCars(cars);
            this.nbCars = cars.length;
        }
        void addCars(Car[] cars){
            this.cars.addAll(Arrays.asList(cars));
        }
        void addCar(Car car){
            this.cars.add(car);
        }

        void applyDiscount(float discount, int condition){
            for (Car car : cars) {
                car.applyDiscount(discount, condition);
            }
        }
        String show(){
            StringBuilder result = new StringBuilder();
            for (Car car : cars) {
                result.append("- ").append(car.toString()).append("\n");
            }
            return "You have a fleet of " + nbCars + " cars, containing : \n" + result;
        }
    }

    public static void main(String[] args) {
        Car c = new Car(1, "Renault", new Date(), new Date(), 1000, 2000, 1);
        Car c2 = new Car(2, "Peugeot", new Date(), new Date(), 1000, 2000, 1);
        Car c3 = new Car(3, "Citroen", new Date(), new Date(), 1000, 2000, 1);
        Car c4 = new Car(4, "Fiat", new Date(), new Date(), 1000, 2000, 3);

        Fleet f = new Fleet(new Car[]{c, c2, c3});
        f.addCar(c4);
        System.out.println(f.show());
        f.applyDiscount(10, 3);
        System.out.printf(f.show());
    }
}
