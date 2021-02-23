package Trees.KDTree;
import java.util.List;

/**
 * CS61B SP2019 Project 2B
 * K-Dimensional Tree
 * @author Mohammad Mahmud
 */

public class KDTree implements PointSet {

    private Node root;

    private class Node {
        int depth;
        Node left;
        Node right;
        Point point;
        Node(Point p, int d) {
            point = p;
            depth = d;
        }
    }

    /**Construct the KD Tree with the given list of points.*/
    public KDTree(List<Point> points) {
        for(Point p : points) {
            root = addPoint(root, p, 0);
        }
    }

    /**
     * Recursively add points to the kd tree by comparing
     * the point by either the X or Y coordinate.
     */
    private Node addPoint(Node n, Point p, int depth) {
        if(n == null) {
            return new Node(p, depth);
        }
        if(n.point.equals(p)) {
            return n;
        }

        double cmp = compare(p, n);
        if(cmp < 0) {
            n.left = addPoint(n.left, p, n.depth + 1);
        } else {
            n.right = addPoint(n.right, p, n.depth + 1);
        }
        return n;
    }

    /**
     * Compares 2 points based on their depth
     * If the depth is even, compare using X coordinate.
     * If the depth is odd, compare using Y coordinate.
     */
    private double compare(Point p, Node n) {
        if(n.depth % 2 == 0) {
            return p.getX() - n.point.getX();
        }
        else {
            return p.getY() - n.point.getY();
        }
    }

    /**Find the nearest point in the KD Tree to the given point.*/
    @Override
    public Point nearest(double x, double y) {
        Point toFind = new Point(x, y);
        Node nearest = getNearest(root, toFind, root);
        return nearest.point;
    }

    /**
     * Recursively searches for the nearest point by comparing the
     * current point with given point based on depth(X or Y coordinate).
     * Always search the "good" side, only search the "bad" side if
     * there is can be a possibly better point that the current best point.
     */
    private Node getNearest(Node n, Point goal, Node best) {
        if(n == null) return best;

        if(distTo(n.point, goal) < distTo(best.point, goal)) {
            best = n;
        }

        Node goodSide, badSide;
        double cmp = compare(goal, n);
        if(cmp < 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = getNearest(goodSide, goal, best);

        if(badSide != null) {
            /*Only check the bad side if it can contain a possibly better point.*/
            if(Math.abs(compare(goal, n)) < distTo(best.point, goal)) {
                best = getNearest(badSide, goal, best);
            }
        }

        return best;
    }

    /*Returns the euclidean distance between 2 points.*/
    private double distTo(Point p, Point goal) {
        return Math.sqrt(Point.distance(p, goal));
    }

    public static void main(String[] args) {

        Point A = new Point(2, 3);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        KDTree kd = new KDTree(List.of(A, B, C, D, E, F));

        System.out.println(kd.nearest(0, 7));
        System.out.println(kd.nearest(4, 4.2));


    }
}
