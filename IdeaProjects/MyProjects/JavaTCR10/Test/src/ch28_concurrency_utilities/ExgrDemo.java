package ch28_concurrency_utilities;

// Пример применения класса Exchanger.

import java.util.concurrent.Exchanger;

class ExgrDemo {
    public static void main(String args[]) {
        Exchanger<String> exgr = new Exchanger<String>();

        new Thread(new UseString(exgr)).start();
        new Thread(new MakeString(exgr)).start();
    }
}

// Поток типа Thread, формирующий символьную строку.
class MakeString implements Runnable {
    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> c) {
        ex = c;
        str = new String();
    }

    public void run() {
        char ch = 'A';

        for (int i = 0; i < 3; i++) {

            // Заполнить буфер.
            for (int j = 0; j < 5; j++)
                str += ch++;

            try {
                // Обменять заполненный буфер на пустой.
                str = ex.exchange(str);
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }
}

// Поток типа Thread, формирующий символьную строку.
class UseString implements Runnable {
    Exchanger<String> ex;
    String str;

    UseString(Exchanger<String> c) {
        ex = c;
    }

    public void run() {

        for (int i = 0; i < 3; i++) {
            try {
                // Обменять пустой буфер на заполненный.
                str = ex.exchange(new String());
                System.out.println("Got: " + str);
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }
}
