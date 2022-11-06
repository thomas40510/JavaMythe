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
        final ArrayList<Point> points;

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

        public double perimeter(){
            // Calculates segment per segment
            int n = this.points.size() - 1;
            double res = this.points.get(n).distance(this.points.get(0));
            for(int k = 0; k < n; k++){
                res += this.points.get(k).distance(this.points.get(k+1));
            }
            return res;
        }

        public double Surface(){
            return -1;
        }

        public String edgesAsText(){
            StringBuilder res = new StringBuilder();
            for(Point p : this.points){
                res.append(p.toString()).append(" ");
            }
            res.replace(res.length()-1, res.length(), "");
            return res.toString();
        }

        public boolean equals(Polygon other){
            if(this.points.size() != other.points.size()){
                return false;
            }
            int n = this.points.size() - 1;
            Point p0 = this.points.get(0);
            int m = other.points.indexOf(p0);
            if(m == -1){
                return false;
            }
            for(int k = 0; k < n; k++){
                if(!this.points.get(k).equals(other.points.get((m+k)%n))){
                    return false;
                }
            }
            return true;
        }

        public String toString(){
            return "Polygon{" + this.edgesAsText() + "}";
        }
    }

    public static class Triangle extends Polygon{
        public Triangle(Point[] points){
            super(points);
            if (points.length != 3){
                throw new IllegalArgumentException("Triangle must have 3 points");
            }
        }

        @Override
        public double Surface(){
            double a = this.points.get(0).distance(this.points.get(1));
            double b = this.points.get(1).distance(this.points.get(2));
            double c = this.points.get(2).distance(this.points.get(0));
            double p = (a+b+c)/2;
            return Math.pow(p*(p-a)*(p-b)*(p-c), .5);
        }

        @Override
        public String toString(){
            enum TriangleType{
                EQUILATERAL, ISOSCELES, SCALENE, RIGHT
            }
            TriangleType type;
            double a = this.points.get(0).distance(this.points.get(1));
            double b = this.points.get(1).distance(this.points.get(2));
            double c = this.points.get(2).distance(this.points.get(0));
            if(sameReal(a, b) && sameReal(b, c)){
                type = TriangleType.EQUILATERAL;
            }else if(sameReal(a, b) || sameReal(b, c) || sameReal(c, a)){
                type = TriangleType.ISOSCELES;
            }else if(sameReal(Math.pow(a, 2), Math.pow(b, 2) + Math.pow(c, 2))
                    || sameReal(Math.pow(b, 2), Math.pow(a, 2) + Math.pow(c, 2))
                    || sameReal(Math.pow(c, 2), Math.pow(a, 2) + Math.pow(b, 2))) {
                type = TriangleType.RIGHT;
            } else{
                type = TriangleType.SCALENE;
            }
            return type + " Triangle{" + this.edgesAsText() + "}";
        }
    }

    public static class Rectangle extends Polygon{
        public Rectangle(Point sg, Point id){
            /* sg = supérieur gauche, id = inférieur droit */
            super(new Point[]{sg, new Point(id.getX(), sg.getY()),
                    id, new Point(sg.getX(), id.getY())});

            if (sg.getX() > id.getX() || sg.getY() < id.getY()
                || sg.getX() == id.getX() || sg.getY() == id.getY()){
                throw new IllegalArgumentException("Incorrect points");
            }
        }

        public double Surface(){
            double a = this.points.get(0).distance(this.points.get(1));
            double b = this.points.get(1).distance(this.points.get(2));
            return a*b;
        }

        public String toString(){
            return "Flat Rectangle{" + this.edgesAsText() + "}";
        }
    }

    public static class Square extends Rectangle{
        public Square(Point sg, Point id){
            super(sg, id);
            if(this.points.get(0).distance(this.points.get(1))
                    != this.points.get(1).distance(this.points.get(2))){
                throw new IllegalArgumentException("This is not a square!");
            }
        }

        public double Surface(){
            double a = this.points.get(0).distance(this.points.get(1));
            return a*a;
        }

        public String toString(){
            return "Flat Square{" + this.edgesAsText() + "}";
        }
    }

    public static class PolygonList{
        private final ArrayList<Polygon> polygons;

        public PolygonList(){
            this.polygons = new ArrayList<>();
        }

        public void addPolygon(Polygon p){
            this.polygons.add(p);
        }

        public void addPolygons(Polygon[] polygons){
            this.polygons.addAll(Arrays.asList(polygons));
        }

        public ArrayList<Polygon> getPolygons(){
            return this.polygons;
        }

        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("PolygonList[\n");
            for (Polygon p : this.polygons) {
                res.append("\t").append(p.toString()).append(",\n");
            }
            res.append("]");
            return res.toString();
        }
    }


    public static void main(String[] args){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(0, 1);
        Point p5 = new Point(2, 2);
        Point p6 = new Point(3, 2);
        Point p7 = new Point(3, 3);

        Polygon poly1 = new Polygon(new Point[]{p1, p2, p3, p4});
        Polygon poly2 = new Polygon(new Point[]{p1, p2, p3, p4});
        Polygon poly3 = new Polygon(new Point[]{p1, p2, p3, p4, p5});

        System.out.println(poly1.equals(poly2));
        System.out.println(poly1.equals(poly3));

        Triangle tri1 = new Triangle(new Point[]{p1, p2, p3});
        Triangle tri2 = new Triangle(new Point[]{p1, p2, p3});

        System.out.println(tri1.equals(tri2));

        Rectangle rect1 = new Rectangle(new Point(0, 2), new Point(2, 0));
        System.out.println(rect1.Surface());

        Square square1 = new Square(new Point(0, 2), new Point(2, 0));
        System.out.println(square1.Surface());
        System.out.println(square1.toString());

        PolygonList list = new PolygonList();
        list.addPolygon(tri1);
        list.addPolygon(rect1);
        list.addPolygons(new Polygon[]{poly1, poly2, poly3});
        System.out.println(list.toString());
    }
}
