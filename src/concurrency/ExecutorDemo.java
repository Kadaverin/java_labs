package concurrency;

import common.utils.IOManager;
import lab2.StringType;

import java.util.*;
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) throws Exception {
        IOManager ioManager = IOManager.getInstance();

        ioManager.printLine("Enter number of lines");
        int amount = ioManager.getPositiveInteger();

        ioManager.printLine("Enter your lines");
        List<String> lines = ioManager.getLines(amount);

        ioManager.printLine("Enter number of threads for executor");
        int num = ioManager.getPositiveInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(num);

        // Latch to wait all lines to be processed in executorService
        CountDownLatch latch = new CountDownLatch(lines.size());

        List<StringType> types = new ArrayList<>(Arrays.asList(new StringType[lines.size()]));

        for (int i = 0; i < num; i++) {
            executorService.execute(new StringTypeRecognizer(i, types, lines, latch));
        }

        if (num < lines.size()) {
            for (int i = lines.size() - num + 1; i < lines.size(); i++) {
                executorService.execute(new StringTypeRecognizer(i, types, lines, latch));
            }
        }

        latch.await();

        ioManager.printLine("Your string types:");
        ioManager.printLine(types.toString());
    }

    static class StringTypeRecognizer implements Runnable {
        List<String> lines;
        List<StringType> targetContainer;
        int targetIndexToTransform;
        CountDownLatch latch;

        StringTypeRecognizer(int index, List<StringType> container, List<String> lines, CountDownLatch latch) {
            System.out.println("Creating StringTypeRecognizer for index=" + index);
            this.targetIndexToTransform = index;
            this.lines = lines;
            this.targetContainer = container;
            this.latch = latch;
        }


        @Override
        public void run() {
            Optional.of(lines.get(targetIndexToTransform)).ifPresent((string) -> {
                System.out.println("Executing StringTypeRecognizer for index=" + targetIndexToTransform);

                StringType type = StringType.getInputType(string);
                targetContainer.set(targetIndexToTransform, type);

                // count down that line for specific index is processed
                latch.countDown();
            });
        }
    }


    static void processStringType(int index, List<StringType> types, List<String> lines) {

    }

}
