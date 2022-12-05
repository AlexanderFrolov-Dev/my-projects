package ch28_concurrency_utilities;

// Простой пример применения класса RecursiveTask<V>.
import java.util.concurrent.*;

// Класс RecursiveTask, используемый для вычисления
// суммы значений элементов в массиве типа double.
class Sum2 extends RecursiveTask<Double> {

    // Пороговое значение последовательного выполнения.
    final int seqThresHold = 500;

    // Обрабатываемый массив.
    double[] data;

    // Определить часть обрабатываемых данных.
    int start, end;

    Sum2(double[] vals, int s, int e ) {
        data = vals;
        start = s;
        end = e;
    }

    // Определить сумму значений элементов в массиве типа double.
    protected Double compute() {
        double sum = 0;

        // Если количество элементов ниже порогового значения,
        // то выполнить далее обработку последовательно.
        if((end - start) < seqThresHold) {
            // Суммировать значения элементов в массиве типа double.
            for(int i = start; i < end; i++) sum += data[i];
        }
        else {
            // В противном случае продолжить разделение данных
            // на меньшие части.

            // Найти середину.
            int middle = (start + end) / 2;

            // Запустить новые подзадачи на выполнение,
            // используя разделенные на части данные.
            Sum2 subTaskA = new Sum2(data, start, middle);
            Sum2 subTaskB = new Sum2(data, middle, end);

            // Запустить каждую подзадачу путем разветвления.
            subTaskA.fork();
//            subTaskB.fork();

            // Ожидать завершения подзадач и накопить результаты.
//            sum = subTaskA.join() + subTaskB.join();
//            sum = subTaskB.join() + subTaskB.invoke();
            sum = subTaskA.join() + subTaskB.compute();
        }
        // Возвратить конечную сумму.
        return sum;
    }
}

// Продемонстрировать параллельное выполнение.
class RecurTaskDemo {
    public static void main(String args[]) {
        // Создать пул задач.
        ForkJoinPool fjp = new ForkJoinPool();

        double[] nums = new double[5000];

        // Инициализировать массив nums чередующимися
        // положительными и отрицательными значениями.
        for(int i=0; i < nums.length; i++)
            nums[i] = (double) (((i%2) == 0) ? i : -i) ;

        Sum2 task = new Sum2(nums, 0, nums.length);

        // Запустить задачи типа ForkJoinTask. Обратите
        // внимание на то, что в данном случае метод invoke()
        // возвращает результат.
//        double summation = fjp.invoke(task);
//        double summation = task.invoke();
        // Запустить главную задачу типа ForkJoinTask асинхронно.
        fjp.execute(task);

        // отобразить состояние пула во время ожидания
        while (!task. isDone()) {
            System.out.println(fjp);
        }

        double summation = fjp.invoke(task);

        System.out.println("Суммирование " + summation);
    }
}
