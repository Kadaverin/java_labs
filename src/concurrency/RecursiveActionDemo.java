package concurrency;

import common.utils.IOManager;
import lab2.StringType;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionDemo {

    public static void main(String[] args) {
        IOManager ioManager = IOManager.getInstance();
        ioManager.printLine("Enter number of lines");
        int amount = ioManager.getPositiveInteger();

        ioManager.printLine("Enter your lines");
        List<String> lines = ioManager.getLines(amount);

        ioManager.printLine("Enter level of parallelism");
        int pLevel = ioManager.getPositiveInteger();

        ioManager.printLine("Enter threshold");
        int threshold = ioManager.getPositiveInteger();

        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        TransformToStringTypesAction task = new TransformToStringTypesAction(threshold, lines, 0, lines.size());

        fjp.invoke(task);

        ioManager.printLine("\nString types:");
        lines.forEach(ioManager::printLine);
    }

    static class TransformToStringTypesAction extends RecursiveAction {
        int threshold;
        List<String> strings;

        int start, end;

        TransformToStringTypesAction(int threshold, List<String> strings, int start, int end) {
            System.out.println("Scheduling TransformToStringTypesAction for slice ["+ start + " - " + end + ']');
            this.threshold = threshold;
            this.strings = strings;
            this.start = start;
            this.end = end;
        }

        protected void compute() {
            if((end - start) < threshold) {
                System.out.println("Executing as a sequence for slice ["+ start + " - " + end + ']');
                for(int i = start; i < end; i++) {
                    strings.set(i, StringType.getInputType(strings.get(i)).toString());
                }
            }
            else {
                int middle = (start + end) / 2;

                invokeAll(new TransformToStringTypesAction(threshold, strings, start, middle),
                        new TransformToStringTypesAction(threshold, strings, middle, end));
            }
        }
    }
}
