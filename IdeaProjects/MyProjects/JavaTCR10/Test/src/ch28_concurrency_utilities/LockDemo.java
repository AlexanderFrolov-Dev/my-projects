package ch28_concurrency_utilities;

// Простой пример блокировки.

import java.util.concurrent.locks.*;

class LockDemo {

    public static void main(String args[]) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(new LockThread(lock, "A")).start();
        new Thread(new LockThread(lock, "B")).start();

    }
}

// Общий ресурс.
class Shared2 {
    static int count = 0;
}

// Поток исполнения, инкрементирующий значение счетчика.
class LockThread implements Runnable {
    String name;
    ReentrantLock lock;

    LockThread(ReentrantLock lk, String n) {
        lock = lk;
        name = n;
    }

    public void run() {

        System.out.println("Starting " + name);

        try {
            // Сначала заблокировать счетчик.
            System.out.println(name + " is waiting to lock count.");
            lock.lock();
            System.out.println(name + " is locking count.");

            Shared2.count++;
            System.out.println(name + ": " + Shared2.count);

            // А теперь переключить контекст, если это возможно.
            System.out.println(name + " is sleeping.");
            Thread.sleep(1000);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        } finally {
            // Снять блокировку.
            System.out.println(name + " is unlocking count.");
            lock.unlock();
        }
    }
}
