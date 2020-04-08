package concurrency;

public class AllWithJoining {
    public static void main(String[] args) {
        try {
            new ParallelTask("Strings statistics calculator", () -> {
                lab1.Main.main(args);
            }).join();

            new ParallelTask("String type recognizer", () -> {
                lab2.Main.main(args);
            }).join();

            new ParallelTask("Points", () -> {
                coords.Main.main(args);
            }).join();

        } catch (InterruptedException interruptedException) {
            System.err.println("An error has been occurred: " + interruptedException.getMessage());
        }
    }
}
