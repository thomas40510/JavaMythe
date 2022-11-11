package td3;

public class Expressions {
    public interface Expression{
        double value(double x);
    }

    public static class Constant implements Expression {
        final double value;
        public Constant(double x){
            this.value = x;
        }
        public double value(double x){
            return this.value;
        }
    }

    public static class Variable implements Expression{
        public Variable(){

        }

        public double value(double x) {
            return x;
        }
    }

    public abstract static class BinaryOperation implements Expression{
        final Expression x;
        final Expression y;

        public BinaryOperation(Expression x, Expression y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Addition extends BinaryOperation{
        public Addition(Expression x, Expression y){
            super(x, y);
        }


        public double value(double x){
            return this.x.value(x) + this.y.value(x);
        }
    }

    public static class Subtraction extends BinaryOperation{
        public Subtraction(Expression x, Expression y){
            super(x, y);
        }

        public double value(double x){
            return this.x.value(x) - this.y.value(x);
        }
    }

    public static class Multiplication extends BinaryOperation{
        public Multiplication(Expression x, Expression y){
            super(x, y);
        }

        public double value(double x){
            return this.x.value(x) * this.y.value(x);
        }
    }

    public static class Division extends BinaryOperation{
        public Division(Expression x, Expression y){
            super(x, y);
        }

        public double value(double x){
            return this.x.value(x) / this.y.value(x);
        }
    }

    public static class OperationUnary implements Expression{
        final Expression x;
        public OperationUnary(Expression x){
            this.x = x;
        }

        public double value(double x){
            return this.x.value(x);
        }
    }

    public static class Sin extends OperationUnary{
        public Sin(Expression x){
            super(x);
        }

        public double value(double x){
            return Math.sin(this.x.value(x));
        }
    }

    public static class Cos extends OperationUnary{
        public Cos(Expression x){
            super(x);
        }

        public double value(double x){
            return Math.cos(this.x.value(x));
        }
    }

    public static class Log extends OperationUnary{
        public Log(Expression x){
            super(x);
        }

        public double value(double x){
            return Math.log(this.x.value(x));
        }
    }

    public static class Exp extends OperationUnary{
        public Exp(Expression x){
            super(x);
        }

        public double value(double x){
            return Math.exp(this.x.value(x));
        }
    }

    public static void main(String[] args){
        Expression f = new Addition(
                new Multiplication(
                        new Constant(2), new Sin(new Variable())),
                new Multiplication(
                        new Constant(3), new Cos(new Variable()))
        );

        double[] tab = {0, .5, 1, 1.5, 2, 2.5};
        for (double x : tab){
            System.out.println("f("+x+") = "+f.value(x));
        }

    }

}
