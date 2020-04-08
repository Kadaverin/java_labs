package concurrency;

public class ParallelTask implements Runnable {
    private Thread tread;
    private Runnable task;
    
    public ParallelTask(String threadName, Runnable task) {
        this.task = task;
        tread = new Thread(this, threadName);
        System.out.println("Thread with name '" + getName() + "' was created");
        tread.start();
    }

    public void join() throws InterruptedException {
        getThread().join();
    }

    public Thread getThread() {
        return tread;
    }

    public String getName() {
        return getThread().getName();
    }

    @Override
    public void run() {
        System.out.println("Entering thread:  " + getName());
        task.run();
        System.out.println("Terminating thread: " + getName() + "\n\n");
    }

}
