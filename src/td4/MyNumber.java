package td4;

import utilities.Logger;

public class MyNumber {
    Logger Log = new Logger();
    private int number;

    public MyNumber(){
        this.number = 0;
    }

    public MyNumber(int number){
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public MyNumber add(MyNumber other){
        return new MyNumber(this.number + other.number);
    }

    public MyNumber sub(MyNumber other){
        return new MyNumber(this.number - other.number);
    }

    public MyNumber mul(MyNumber other){
        return new MyNumber(this.number * other.number);
    }

    public MyNumber div(MyNumber other){
        try{
            return new MyNumber(this.number / other.number);
        } catch(ArithmeticException e){
            Log.e("Division by zero");
            return null;
        }
    }
}
