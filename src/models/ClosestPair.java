package models;

import utils.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private final Metrics metrics;

    public ClosestPair(Metrics metrics) {
        this.metrics = metrics;
    }

    public double findClosest(Point[] points) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingInt(p -> p.x));
        long start = System.nanoTime();
        double result = closestPairRec(sortedByX);
        long end = System.nanoTime();
        metrics.setRunTime(end - start);
        return result;
    }

    private double closestPairRec(Point[] points) {
        int n = points.length;
        if (n <= 3) return bruteForce(points);

        int mid = n / 2;
        Point midPoint = points[mid];

        double dl = closestPairRec(Arrays.copyOfRange(points, 0, mid));
        double dr = closestPairRec(Arrays.copyOfRange(points, mid, n));
        double d = Math.min(dl, dr);

        Point[] strip = Arrays.stream(points)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .sorted(Comparator.comparingInt(p -> p.y))
                .toArray(Point[]::new);

        return Math.min(d, stripClosest(strip, d));
    }

    private double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                min = Math.min(min, dist(strip[i], strip[j]));
                metrics.incrementComparison();
            }
        }
        return min;
    }

    private double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                min = Math.min(min, dist(points[i], points[j]));
                metrics.incrementComparison();
            }
        }
        return min;
    }

    private double dist(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (long)(p1.x - p2.x) +
                (p1.y - p2.y) * (long)(p1.y - p2.y));
    }

    public static class Point {
        public int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
    }
}
