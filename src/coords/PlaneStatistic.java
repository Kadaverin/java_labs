package coords;


import java.util.List;

public class PlaneStatistic {
    public static MinMaxXY getMinMaxXY(Plane plane) {
        List<Point> points = plane.getPoints();

        if( points.size() > 0) {
            Point temp = points.get(0);

            int maxX = temp.getX();
            int maxY = temp.getY();
            int minX = temp.getX();
            int minY = temp.getY();

            for(int i = 0; i < points.size(); i++) {
                temp = points.get(i);

                if(temp.getX() > maxX){
                    maxX = temp.getX();
                }

                if( temp.getX() < minX) {
                    minX = temp.getX();
                }

                if(temp.getY() > maxY) {
                    maxY = temp.getY();
                }

                if(temp.getY() < minY) {
                    minY = temp.getY();
                }
            }

            return new MinMaxXY(minX, maxX, minY, maxY);
        } else return null;
    }
}
