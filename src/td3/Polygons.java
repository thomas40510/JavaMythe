package td3;

import java.util.ArrayList;
import java.util.Arrays;

public class Polygons {

    static boolean sameReal(double a, double b){
        return (Math.abs(a-b) < 1e-10);
    }
    public static class Point{
        private double x, y;

        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }

        public double getX(){
            return this.x;
        }

        public double getY(){
            return this.y;
        }

        public double distance(Point other){
            /* Calculate distance btw this and another Point */
            double xa = this.getX();
            double ya = this.getY();
            double xb = other.getX();
            double yb = other.getY();

            double res = Math.pow((xb-xa), 2) + Math.pow((yb-ya), 2);
            return Math.pow(res, .5);
        }

        public boolean equals(Point other){
            return (sameReal(this.getX(), other.getX())
                    && sameReal(this.getY(), other.getY()));
        }

        public String toString(){
            return "<"+this.getX()+","+this.getY()+">";
        }
    }

    public static class Polygon{
        private final ArrayList<Point> points;

        public Polygon(Point[] points){
            this.points = new ArrayList<>();
            this.points.addAll(Arrays.asList(points));
        }

        public void addPoint(Point p){
            this.points.add(p);
        }

        public void addPoints(Point[] points){
            this.points.addAll(Arrays.asList(points));
        }

        public ArrayList<Point> getEdges(){
            return this.points;
        }

        public double perimeter(){//TODO
            double res = this.points.get(-1).distance(this.points.get(0));
            int n = this.points.size();
            for(int k = 0; k < n; k++){
                res += this.points.get(k).distance(this.points.get(k+1));
            }
            return res;
        }
    }


    public static void main(String[] args){
        System.out.println("hello world!");
    }
}
