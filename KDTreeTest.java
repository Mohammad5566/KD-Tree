package Trees.KDTree;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    Random rand = new Random(500);
    PointSet nps;
    KDTree kd;

    /*Insert N random points into NaivePointSet and KDTree. */
    @Before
    public void setup() {
        List<Point> input = generatePoints(100000);
        nps = new NaivePointSet(input);
        kd = new KDTree(input);
    }

    @Test
    public void testNearest() {
        List<Point> queries = generatePoints(10000);
        for(Point p : queries) {
            Point naiveNearest = nps.nearest(p.getX(), p.getY());
            Point kdNearest = kd.nearest(p.getX(), p.getY());
            assertEquals(kdNearest, naiveNearest);
        }
    }

    @Test
    public void speedTest() {
        List<Point> input = generatePoints(100000);
        KDTree kd = new KDTree(input);
        NaivePointSet nps = new NaivePointSet(input);
        List<Point> queries = generatePoints(10000);

        Stopwatch kdWatch = new Stopwatch();
        for(Point p : queries) {
            Point x = kd.nearest(p.getX(), p.getY());
        }
        System.out.println("KDTree took: " + kdWatch.elapsedTime());

        Stopwatch npsWatch = new Stopwatch();
        for(Point p : queries) {
            Point x = nps.nearest(p.getX(), p.getY());
        }
        System.out.println("NaivePointSet took: " + npsWatch.elapsedTime());
    }







    /*Generate a list of N random points. */
    private List<Point> generatePoints(int N) {
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < N; i += 1) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            points.add(new Point(x, y));
        }
        return points;
    }

    /*Generate a list of N non-random points. */
    private List<Point> generatePredictablePoints(int N) {
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < N; i += 1) {
            points.add(new Point(i, i));
        }
        return points;
    }




}
