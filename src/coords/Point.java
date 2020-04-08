package coords;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Point createRandomPoint(int minX, int maxX, int minY, int maxY) {
        return new Point(
                Utils.randomIntFromRange(minX, maxX),
                Utils.randomIntFromRange(minY, maxY)
        );
    }

    public String toString() {
        return String.format("[%d ; %d]", this.getX(), this.getY());
    }
}
