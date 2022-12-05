package ch28_concurrency_utilities;

// Продемонстрировать применение класса CyclicBarrier.

import java.util.concurrent.*;

class BarDemo {
    public static void main(String args[]) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction() );

        System.out.println("Starting");

        new Thread(new MyThreadSecond(cb, "A")).start();
        new Thread(new MyThreadSecond(cb, "B")).start();
        new Thread(new MyThreadSecond(cb, "C")).start();
//        new Thread(new MyThreadSecond(cb, "X")).start();
//        new Thread(new MyThreadSecond(cb, "Y")).start();
//        new Thread(new MyThreadSecond(cb, "Z")).start();

    }
}

// Поток исполнения, использующий барьер типа CyclicBarrier.
class MyThreadSecond implements Runnable {
    CyclicBarrier cbar;
    String name;

    MyThreadSecond(CyclicBarrier c, String n) {
        cbar = c;
        name = n;
    }

    public void run() {

        System.out.println(name);

        try {
            cbar.await();
        } catch (BrokenBarrierException exc) {
            System.out.println(exc);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
    }
}

// Объект этого класса вызывается по достижении
// барьера типа CyclicBarrier.
class BarAction implements Runnable {
    public void run() {
        System.out.println("Barrier Reached!");
    }
}
