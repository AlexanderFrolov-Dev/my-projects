package ch28_concurrency_utilities;

// Простой пример программы, позволяющий
// экспериментировать с эффектом от изменения
// порогового значения и уровня параллелизма
// выполнения задач в классе ForkJoinTask.
import java.util.concurrent.*;

// Класс ForkJoinTask преобразует
// (через класс RecursiveAction)
// элементы массива типа double.
class Transform extends RecursiveAction {

    // Порог последовательного выполнения,
    // устанавливаемый конструктором.
    int seqThreshold;

    // Обрабатываемый массив.
    double[] data;

    // Определить часть обрабатываемых данных.
    int start, end;

    Transform(double[] vals, int s, int e, int t ) {
        data = vals;
        start = s;
        end = e;
        seqThreshold = t;
    }

    // Этот метод выполняет параллельное вычисление.
    protected void compute() {

        // Выполнить далее обработку последовательно,
        // если количество элементов ниже порогового значения
        if((end - start) < seqThreshold) {
            // В следующем фрагменте кода элементу по четному
            // индексу присваивается квадратный корень его
            // первоначального значения, а элементу по нечетному
            // индексу - кубический корень его первоначального
            // значения. Этот код предназначен только для
            // потребления времени ЦП, чтобы сделать нагляднее
            // эффект от параллельного выполнения.
            for(int i = start; i < end; i++) {
                if((data[i] % 2) == 0)
                    data[i] = Math.sqrt(data[i]);
                else
                    data[i] = Math.cbrt(data[i]);
            }
        }
        else {
            // В противном случае продолжить разделение данных
            // на меньшие части.

            // Найти середину.
            int middle = (start + end) / 2;

            // Запустить новые подзадачи на выполнение,
            // используя разделенные на части данные.
            invokeAll(new Transform(data, start, middle, seqThreshold),
                    new Transform(data, middle, end, seqThreshold));
        }
    }
}

// Продемонстрировать параллельное выполнение.
class FJExperiment {

    public static void main(String args[]) {
        int pLevel;
        int threshold;

        if(args.length !=  2) {
            System.out.println("Использование: "
                    + "FJExperiment параллелизм порог");
            return;
        }

        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        // Эти переменные используются для измерения
        // времени выполнения задачи.
        long beginT, endT;

        // Создать пул задач. Обратите внимание
        // на установку уровня параллелизма.
        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        double[] nums = new double[1000000];

        for(int i = 0; i < nums.length; i++)
            nums[i] = (double) i;

        Transform task = new Transform(nums, 0, nums.length, threshold);

        // Начать измерение времени выполнения задачи.
        beginT = System.nanoTime();

        // Запустить главную задачу типа ForkJoinTask.
        fjp.invoke(task);

        // Завершить измерение времени выполнения задачи.
        endT = System.nanoTime();

        System.out.println("Уровень параллелизма: " + pLevel);
        System.out.println("Порог последовательной обработки: " + threshold);
        System.out.println("Истекшее время: " + (endT - beginT) + " нс");
        System.out.println();
    }
}

