package coords;

import java.util.ArrayList;
import java.util.List;


public class Plane {
    private List<Point> points;

    public  Plane() {
        this.points = new ArrayList<>();
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public void generatePoints() {
        this.generatePoints(10, -5, 5, -5, 5);
    }

    public void generatePoints(int amount, int minX, int maxX, int minY, int maxY) {
        for (int i = 0; i < amount; i++) {
            points.add(Point.createRandomPoint(minX, maxX, minY, maxY));
        }
    }

    public double getPointsDistance(Point point1, Point point2) {
        double a = (point1.getX() - point2.getX());
        double b = (point1.getY() - point2.getY());
        return Math.sqrt(a * a + b * b);
    }

    public double getTotalDistanceToRestPoints(Point target) {
        return this.points
                 .stream()
                 .mapToDouble( point -> getPointsDistance(target, point))
                 .sum();
    }

    public Point getPointLeastRemoteFromRest() {
        return this.points
                .stream()
                .reduce((leastRemote, current) ->
                    getTotalDistanceToRestPoints(leastRemote) > getTotalDistanceToRestPoints(current)
                        ? current
                        : leastRemote
                )
                .orElse(null);
    }


    public boolean containsXY(int x, int y) {
        return this.points
                .stream()
                .anyMatch(c -> y  == c.getY() && x == c.getX());
    }


    public void print() {
        MinMaxXY minMaxXY = PlaneStatistic.getMinMaxXY(this);

        if( minMaxXY == null ) {
            System.out.println("This plane has no points! Fill it first");
            return;
        }

        System.out.print('\n');

        for(int y = minMaxXY.getMaxY(); y >= minMaxXY.getMinY(); y--){
            for(int x = minMaxXY.getMinX(); x <= minMaxXY.getMaxX(); x++) {
                if(this.containsXY(x,y)){

                    if((x == 1) && this.containsXY(0, y)){
                        System.out.print(" x ");
                    } else if((x == 0) && (y == 0)) {
                        System.out.print("  x");
                    } else {
                        System.out.print("  x ");
                    }

                } else if ((x == 0) && (y == 0)) {
                    System.out.print("  0");
                } else if(x == 0) {

                    if(y > 0) {
                        System.out.print("  " + y );
                    } else {
                        System.out.print(" " + y );
                    }

                } else if (y == 0) {

                    if( x > 0) {
                        System.out.print("  " + x + " ");
                    } else {
                        System.out.print(" " + x + " ");
                    }

                } else {

                    if((x == 1) && this.containsXY(0, y)){
                        System.out.print(" . ");
                    } else {
                        System.out.print("  . ");
                    }
                }

                if(x == minMaxXY.getMaxX()){
                    System.out.print('\n');
                }
            }
        }

        System.out.print('\n');
    }
}
