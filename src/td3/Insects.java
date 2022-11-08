package td3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Insects {
    public static class Insect{
        private int hunger;
        private int lastMeal;
        protected int abscissa;
        protected int ordinate;
        public Insect(int hunger, int abscissa, int ordinate){
            this.hunger = hunger;
            this.lastMeal = 0;
            this.abscissa = abscissa;
            this.ordinate = ordinate;
        }

        public int getLastMeal() {
            return lastMeal;
        }

        public void setLastMeal(int lastMeal) {
            this.lastMeal = lastMeal;
        }

        public int getHunger() {
            return hunger;
        }

        public void setHunger(int hunger) {
            this.hunger = hunger;
        }

        public void eat(){
            this.hunger = (this.hunger == this.lastMeal) ? 0 : this.hunger;
        }

        public void move(){
            this.abscissa += 1;
            this.ordinate += 1;
            this.lastMeal += 1;
            this.hunger += 1;
        }

        public String toString(){
            return "[hunger=" + hunger
                    + ", lastMeal=" + lastMeal + ", abscissa="
                    + abscissa + ", ordinate=" + ordinate + "]";
        }
    }

    public static class Cigale extends Insect{
        public Cigale(int abscissa, int ordinate){
            super(3, abscissa, ordinate);
        }

        public String toString(){
            return "Cigale" + super.toString();
        }
    }

    public static class Ant extends Insect{
        public Ant(int abscissa, int ordinate){
            super(5, abscissa, ordinate);
        }

        public String toString(){
            return "Ant" + super.toString();
        }
    }

    private final int nbRounds;
    public Random rand;
    private final ArrayList<Insect> insects;

    public Insects(int nbRounds, int nbInsects){
        this.nbRounds = nbRounds;
        this.rand = new Random();
        this.insects = new ArrayList<>();
        for (int i = 0; i < nbInsects; i++){
            int k = rand.nextInt(2);
            if (k == 0){
                this.insects.add(new Cigale(rand.nextInt(10), rand.nextInt(10)));
            } else {
                this.insects.add(new Ant(rand.nextInt(10), rand.nextInt(10)));
            }
        }
    }

    public void simulate(){
        for (int i = 0; i < this.nbRounds; i++) {
            System.out.println("*** Round " + i + " ***");
            for (Insect insect : this.insects) {
                insect.move();
                insect.eat();
                System.out.println(insect);
            }
        }
    }

    public static void main(String[] args) {
        Insects i = new Insects(10, 5);
        i.simulate();
    }
}
