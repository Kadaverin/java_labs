package coords;

public class Main {
    public static void main(String[] args) {
        Plane plane = new Plane();
        plane.generatePoints();

        System.out.println("\nAll plane with points (marked as 'X'):");
        plane.print();

        Point leastRemote = plane.getPointLeastRemoteFromRest();

        System.out.print("Point that least remote from rest points has coordinates ");
        System.out.println(leastRemote);
    }
}
