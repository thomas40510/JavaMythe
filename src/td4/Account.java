package td4;

import utilities.Log;

public class Account {
    private final String name;
    private int balance;
    private int maxWithdrawal;
    private int maxDeficit;

    Log Log = new Log();

    public Account (String name, int balance){
        this.name = name;
        this.balance = balance;
        this.maxWithdrawal = 1000;
        this.maxDeficit = 0;
    }

    public Account(String name, int balance, int maxWithdrawal, int maxDeficit){
        this.name = name;
        this.balance = balance;
        this.maxWithdrawal = maxWithdrawal;
        this.maxDeficit = maxDeficit;
    }

    public String getName(){
        return this.name;
    }

    public int getBalance(){
        return this.balance;
    }

    public int getMaxWithdrawal(){
        return this.maxWithdrawal;
    }

    public int getMaxDeficit(){
        return this.maxDeficit;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void setMaxWithdrawal(int maxWithdrawal){
        this.maxWithdrawal = maxWithdrawal;
    }

    public void setMaxDeficit(int maxDeficit){
        this.maxDeficit = maxDeficit;
    }

    public boolean withdraw(int amount){
        try {
            if (amount > maxWithdrawal) {
                throw new IllegalArgumentException("amount exceeds maximum withdrawal.");
            } else if (amount > balance + maxDeficit) {
                throw new IllegalArgumentException("amount exceeds balance.");
            } else {
                balance -= amount;
                return true;
            }
        } catch (IllegalArgumentException e) {
            Log.i(e.getMessage());
            return false;
        }
    }

    public void deposit(int amount){
        balance += amount;
    }

    public boolean transfer(Account other, int amount){
        try{
            if(other == null) throw new NullPointerException();
            if(amount > maxWithdrawal){
                throw new IllegalArgumentException("amount exceeds maximum withdrawal.");
            } else if(amount > balance + maxDeficit){
                throw new IllegalArgumentException("amount exceeds balance.");
            } else {
                balance -= amount;
                other.balance += amount;
                return true;
            }
        } catch(IllegalArgumentException e){
            Log.i(e.getMessage());
        } catch (NullPointerException e){
            Log.e("account does not exist.");
        }
        return false;
    }

    public boolean hasDeficit(){
        return balance < 0;
    }

    public String toString() {
        String msg = "Account " + name + " has a balance of " + balance
                + " with a maximum withdrawal of " + maxWithdrawal + " and a maximum deficit of " + maxDeficit;
        if (hasDeficit()) {
            return msg + " and IS IN DEFICIT.";
        } else {
            return msg + " and is not in deficit.";
        }
    }
}
