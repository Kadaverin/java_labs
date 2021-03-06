package coords;

import common.exceptions.PlaneHasNoPointsException;

import java.util.List;

public class PlaneStatistic {
    public static MinMaxXY getMinMaxXY(Plane plane) {
        List<Point> points = plane.getPoints();

        if (plane.getPoints() == null || plane.getPoints().isEmpty()) {
            throw new PlaneHasNoPointsException("Plane has no points for calculating statistics");
        }

        Point temp = points.get(0);

        int maxX = temp.getX();
        int maxY = temp.getY();
        int minX = temp.getX();
        int minY = temp.getY();

        for (int i = 1; i < points.size(); i++) {
            temp = points.get(i);

            if (temp.getX() > maxX) {
                maxX = temp.getX();
            }

            if (temp.getX() < minX) {
                minX = temp.getX();
            }

            if (temp.getY() > maxY) {
                maxY = temp.getY();
            }

            if (temp.getY() < minY) {
                minY = temp.getY();
            }
        }

        return new MinMaxXY(minX, maxX, minY, maxY);
    }
}
