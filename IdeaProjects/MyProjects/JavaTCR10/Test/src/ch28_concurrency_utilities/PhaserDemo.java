package ch28_concurrency_utilities;

// Пример применения класса Phaser.

import java.util.concurrent.*;

class PhaserDemo {
    public static void main(String args[]) {
        Phaser phsr = new Phaser(1);
        int curPhase;

        System.out.println("Starting");

        new Thread(new MyThread3(phsr, "A")).start();
        new Thread(new MyThread3(phsr, "B")).start();
        new Thread(new MyThread3(phsr, "C")).start();

        // Ожидать завершения всеми потоками исполнения первой фазы.
        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        // Ожидать завершения всеми потоками исполнения второй фазы.
        curPhase =  phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        curPhase =  phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        // Снять основной поток с регистрации.
        phsr.arriveAndDeregister();

        if(phsr.isTerminated())
            System.out.println("The Phaser is terminated");
    }
}

// Поток исполнения, применяющий синхронизатор фаз типа Phaser.
class MyThread3 implements Runnable {
    Phaser phsr;
    String name;

    MyThread3(Phaser p, String n) {
        phsr = p;
        name = n;
        phsr.register();
    }

    public void run() {

        System.out.println("Thread " + name + " Beginning Phase One");
        phsr.arriveAndAwaitAdvance(); // Signal arrival.

        // Небольшая пауза, чтобы не нарушить порядок вывода.
        // Это сделано только для целей демонстрации, но
        // совсем не обязательно для правильного
        // функционирования синхронизатора фаз
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread " + name + " Beginning Phase Two");
        phsr.arriveAndAwaitAdvance(); // Signal arrival.

        // Небольшая пауза, чтобы не нарушить порядок вывода.
        // Это сделано только для целей демонстрации, но
        // совсем не обязательно для правильного
        // функционирования синхронизатора фаз
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread " + name + " Beginning Phase Three");
        phsr.arriveAndDeregister(); // Signal arrival and deregister.
    }
}