package ch28_concurrency_utilities;

// Расширить класс MyPhaser, и переопределить
// метод onAdvance() таким образом, чтобы было
// выполнено только определенное количество фаз.

import java.util.concurrent.*;

// Расширить класс MyPhaser, чтобы выполнить
// только определенное количество фаз.
class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int phaseCount) {
        super(parties);
        numPhases = phaseCount - 1;
    }

    // Переопределить метод onAdvance(), чтобы
    // выполнить только определенное количество фаз.
    protected boolean onAdvance(int p, int regParties) {
        // Следующий вызов метода println() требуется только
        // для целей иллюстрации. Как правило, метод
        // onAdvance() не отображает выводимые данные.
        System.out.println("Phase " + p + " completed.\n");

        // Возвратить логическое значение true,
        // Если все фазы завершены.
        if(p == numPhases || regParties == 0) return true;

        // В противном случае возвратить логическое
        // значение false.
        return false;
    }
}

class PhaserDemo2 {
    public static void main(String args[]) {

        MyPhaser phsr = new MyPhaser(1, 4);

        System.out.println("Starting\n");

        new Thread(new MyThread4(phsr, "A")).start();
        new Thread(new MyThread4(phsr, "B")).start();
        new Thread(new MyThread4(phsr, "C")).start();

        // Ожидать завершения определенного количества фаз.
        while(!phsr.isTerminated()) {
            phsr.arriveAndAwaitAdvance();
        }

        System.out.println("The Phaser is terminated");
    }
}

// Поток исполнения, применяющий синхронизатор фаз типа Phaser.
class MyThread4 implements Runnable {
    Phaser phsr;
    String name;

    MyThread4(Phaser p, String n) {
        phsr = p;
        name = n;
        phsr.register();
    }

    public void run() {

        while(!phsr.isTerminated()) {
            System.out.println("Thread " + name + " Beginning Phase " +
                    phsr.getPhase());

            phsr.arriveAndAwaitAdvance();

            // Небольшая пауза, чтобы не нарушить
            // порядок вывода. Это сделано только для
            // демонстрации, но совсем не обязательно для
            // правильного функционирования синхронизатора фаз.
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        }

    }
}