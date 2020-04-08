package coords;

import common.utils.IOManager;

public class Main {
    public static void main(String[] args) {
        IOManager ioManager = IOManager.getInstance();
        Plane plane = new Plane();
        plane.generatePoints();

        ioManager.printLine("\nAll plane with points (marked as 'X'):");
        plane.print();

        Point leastRemote = plane.getPointLeastRemoteFromRest();

        ioManager.printLine("Point that least remote from rest points has coordinates ");
        ioManager.printLine(leastRemote.toString());
    }
}
