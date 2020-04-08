package concurrency;

public class AllInParallel {
    public static void main(String[] args) {
        new ParallelTask("Strings statistics calculator", () -> {
            lab1.Main.main(args);
        });

        new ParallelTask("String type recognizer", () -> {
            lab2.Main.main(args);
        });

        new ParallelTask("Points", () -> {
            coords.Main.main(args);
        });
    }
}
