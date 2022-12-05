package ch28_concurrency_utilities;

// Простой пример применения исполнителя.

import java.util.concurrent.*;

class SimpExec {
    public static void main(String args[]) {
        CountDownLatch cdl = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdl3 = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);
        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("Starting");

        // Запустить потоки исполнения.
        es.execute(new MyThread5(cdl, "A"));
        es.execute(new MyThread5(cdl2, "B"));
        es.execute(new MyThread5(cdl3, "C"));
        es.execute(new MyThread5(cdl4, "D"));

        try {
            cdl.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        es.shutdown();
        System.out.println("Done");
    }
}

class MyThread5 implements Runnable {
    String name;
    CountDownLatch latch;

    MyThread5(CountDownLatch c, String n) {
        latch = c;
        name = n;
    }

    public void run() {

        for(int i = 0; i < 5; i++) {
            System.out.println(name + ": " + i);
            latch.countDown();
        }
    }
}
