package Trees.KDTree;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {

    private List<Point> pointList;

    public NaivePointSet(List<Point> points) {
        pointList = new ArrayList<>();
        for(Point p : points) {
            pointList.add(p);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point toFind = new Point(x, y);
        Point best = pointList.get(0);

        for(Point p : pointList) {
            double currDist = Math.sqrt(Point.distance(p, toFind));
            double bestDist = Math.sqrt(Point.distance(best, toFind));
            if(currDist < bestDist) {
                best = p;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4


        Point A = new Point(2, 3);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        NaivePointSet nn2 = new NaivePointSet(List.of(A, B, C, D, E, F));
        System.out.println(nn2.nearest(4, 4.2));

    }
}
