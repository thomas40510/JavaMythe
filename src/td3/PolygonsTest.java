package td3;

import org.junit.jupiter.api.Test;
import td3.Polygons.Point;
import td3.Polygons.Polygon;

public class PolygonsTest {

    @Test
    public void testCreate(){
        Point p1 = new Point(1, 2);
        assert p1.getX() == 1;
        assert p1.getY() == 2;
        Polygon p = new Polygon(new Point[]{});
        assert(p.points.size() == 0);
    }

    @Test
    public void testAddPoint(){
        Polygon p = new Polygon(new Point[]{});
        p.addPoint(new Point(1, 2));
        assert(p.points.size() == 1);
    }

    @Test
    public void testAddPoints(){
        Polygon p = new Polygon(new Point[]{});
        p.addPoints(new Point[]{new Point(1, 2), new Point(3, 4)});
        assert(p.points.size() == 2);
        assert(p.points.get(0).getX() == 1);
        assert(p.points.get(0).getY() == 2);
        assert(p.points.get(1).getX() == 3);
        assert(p.points.get(1).getY() == 4);
    }

    @Test
    public void testGetEdges(){
        Polygon p = new Polygon(new Point[]{new Point(1, 2), new Point(3, 4)});
        assert(p.getEdges().size() == 2);
        assert(p.getEdges().get(0).getX() == 1);
        assert(p.getEdges().get(0).getY() == 2);
        assert(p.getEdges().get(1).getX() == 3);
        assert(p.getEdges().get(1).getY() == 4);
    }

    @Test
    public void testPerimeter(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);
        Polygon p = new Polygon(new Point[]{p1, p2, p3});
        double res = p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
        assert(p.perimeter() == res);
    }

    @Test
    public void testSurface(){
        Polygon p = new Polygon(new Point[]{new Point(1, 2), new Point(3, 4)});
        assert(p.Surface() == -1);
    }

    @Test
    public void testEquals(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        assert(p1.equals(p2));
    }

}
