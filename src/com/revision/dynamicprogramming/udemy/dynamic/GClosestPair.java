package com.revision.dynamicprogramming.udemy.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GClosestPair {
    public static void main(String[] args) {
        GClosestPair pairFinder = new GClosestPair();
        List<Point> points = Arrays.asList(new Point(4, 3), new Point(3, 6), new Point(4, 6),
                new Point(1, 8), new Point(2, 3), new Point(3, 2), new Point(6, 5),
                new Point(5, 8), new Point(10, 13), new Point(11, 12), new Point(15, 10),
                new Point(14, 10));
        System.out.println("Closest Pair is of distance: " + pairFinder.solve(points));
    }

    private double solve(List<Point> points) {
        /* Create a copy of points to y */
        List<Point> pointsY = new ArrayList<>(points.size());
        pointsY.addAll(points);
        /* Sort the points according to the x coordinate */
        List<Point> pointsX = sort(points);
        double result = solve(pointsX, pointsY, points.size());
        return result;
    }

    private double solve(List<Point> pointsX, List<Point> pointsY, int size) {
        if (size <= 3) return conventionalComparison(pointsX);

        int midIndex = size / 2;
        Point midPoint = pointsX.get(midIndex);

        List<Point> leftX = new ArrayList<>(midIndex);
        List<Point> leftY = new ArrayList<>(midIndex);
        List<Point> rightX = new ArrayList<>(size - midIndex);
        List<Point> rightY = new ArrayList<>(size - midIndex);

        for (int i = 0; i < pointsX.size(); i++) {
            if (pointsY.get(i).getX() <= midPoint.getX()) {
                leftX.add(pointsX.get(i));
                leftY.add(pointsY.get(i));
            } else {
                rightX.add(pointsX.get(i));
                rightY.add(pointsY.get(i));
            }
        }
        double deltaLeft = solve(leftX, leftY, midIndex);
        double deltaRight = solve(rightX, rightY, size - midIndex);
        double delta = Math.min(deltaLeft, deltaRight);

        List<Point> midStrip = new ArrayList<>();
        for (int i = 0; i < pointsX.size(); i++) {
            if (Math.abs(pointsY.get(i).getX() - midPoint.getX()) < delta) {
                midStrip.add(pointsY.get(i));
            }
        }
        double minDistanceInMidStrip = closestInStrip(midStrip, delta);
        return Math.min(delta, minDistanceInMidStrip);
    }

    private double conventionalComparison(List<Point> pointsX) {
        double distance = Integer.MAX_VALUE;
        for (int i = 0; i < pointsX.size(); i++) {
            for (int j = i + 1; j < pointsX.size(); j++) {
                double temp = distance(pointsX.get(i), pointsX.get(j));
                if (temp < distance) distance = temp;
            }
        }
        return distance;
    }

    private double closestInStrip(List<Point> midStrip, double delta) {
        double distance = delta;
        midStrip.sort(Comparator.comparing(Point::getY));
        for (int i = 0; i < midStrip.size(); i++) {
            for (int j = i + 1; j < midStrip.size() && (midStrip.get(j).getY() - midStrip.get(i).getY()) < distance; j++) {
                double temp = distance(midStrip.get(i), midStrip.get(j));
                if (temp < distance) distance = temp;
            }
        }
        return distance;
    }

    private List<Point> sort(List<Point> points) {
        return points.stream().sorted(Comparator.comparing(Point::getX)).collect(Collectors.toList());
    }

    private double distance(Point one, Point two) {
        double xD = Math.pow(Math.abs(one.getX() - two.getX()), 2);
        double yD = Math.pow(Math.abs(one.getY() - two.getY()), 2);
        return Math.sqrt(xD + yD);
    }
}

class Point {
    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}