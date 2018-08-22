package ru.job4j.threads;

public class JMM {

    /**
     * Первая проблема - использование одного и того же контейнера данных в разных потоках
     * т.е. если в потоке будет приниматься какая либо переменная, то шанс одновременного обращения
     *  к переменной у разных потоков почти 100%
     * В результате этого вероятность ошибочного вычисления почти 100%
     */

    public static void main(String[] args) throws InterruptedException {
        Example ex = new Example();
        for (int i = 0; i < 1000; i++) {
            ThreadExample te = new ThreadExample(ex);
            te.start();
        }
        Thread.sleep(1000);
        System.out.println(ex.getCount());
    }

    /**
     * Для решения этой проблемы можно использовать синхронизацию
     * Синхронизация позволяет избежать проблемы одновременного использования
     * Однако при использовании синхронизации есть вероятность, что все процессы встанут, так как если все процессы
     *  используют синхронизированный метод, то они стоят в очереди за ним. Если один из потоков встанет с данным процессом,
     *  то остальные потоки не пойдут дальше.
     */

}

class Example {
    private double count = 0;

    public void growCount() {
//        synchronized (this) {
//            count++;
//        }
        this.count++;
    }

    public double getCount() {
        return this.count;
    }
}

class ThreadExample extends Thread {
    private Example example;

    public ThreadExample(Example ex) {
        this.example = ex;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            example.growCount();
        }
    }
}
