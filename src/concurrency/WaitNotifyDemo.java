package concurrency;

import common.utils.IOManager;
import lab2.StringType;


public class WaitNotifyDemo {
    private static final String END_OF_INPUT = "__end";

    public static void main(String[] args) {
        Data data = new Data();
        Thread producer = new Thread(new StringProducer(data));
        Thread consumer = new Thread(new StringReceiverAndAnalyzer(data));

        producer.start();
        consumer.start();
    }

    public static class Data {
        private String packet;

        private boolean transfer = true;

        public synchronized void send(String packet) {
            while (!transfer) {
                try {
                    wait();
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted");
                }
            }
            transfer = false;

            this.packet = packet;
            notifyAll();
        }

        public synchronized String receive() {
            while (transfer) {
                try {
                    wait();
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted");
                }
            }
            transfer = true;

            notifyAll();
            return packet;
        }
    }

    public static class StringProducer implements Runnable {
        private Data data;

        public StringProducer(Data data) {
            this.data = data;
        }

        public void run() {
            IOManager ioManager = IOManager.getInstance();
            ioManager.printLine("Print your lines. Type " + END_OF_INPUT + " to break");

            while(true) {
                String line = ioManager.getLineSafely();
                data.send(line);

                if(END_OF_INPUT.equals(line)) {
                    break;
                }
            };
        }
    }

    public static class StringReceiverAndAnalyzer implements Runnable {
        private Data data;

        public StringReceiverAndAnalyzer(Data data) {
            this.data = data;
        }

        public void run() {
            while (true) {
                String receivedMessage = data.receive();

                if(END_OF_INPUT.equals(receivedMessage)) {
                    break;
                }

                StringType type = StringType.getInputType(receivedMessage);

                // тут может быть, например, запись в файл или другая более полезная и трудоемкая операция
                System.out.println("Your input type is: " + type);
            }
        }
    }
}
