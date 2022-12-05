package ch28_concurrency_utilities;

// Простой пример выполнения атомарных операций.

import java.util.concurrent.atomic.*;

class AtomicDemo {

    public static void main(String args[]) {
        new Thread(new AtomThread("A")).start();
        new Thread(new AtomThread("B")).start();
        new Thread(new AtomThread("C")).start();
    }
}

class Shared3 {
    static AtomicInteger ai = new AtomicInteger(0);
}

// Поток исполнения, в котором инкрементируется значение счетчика.
class AtomThread implements Runnable {
    String name;

    AtomThread(String n) {
        name = n;
    }

    public void run() {

        System.out.println("Starting " + name);

        for(int i=1; i <= 3; i++)
            System.out.println(name + " got: " +
                    Shared3.ai.getAndSet(i));
    }
}
