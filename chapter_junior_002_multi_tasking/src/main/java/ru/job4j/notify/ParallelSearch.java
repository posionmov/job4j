package ru.job4j.notify;

/**
 * Класс для показа работы Производителя и Потребителя
 * @author Petr Arsentev (доработал - Galanov Sergey)
 */
public class ParallelSearch {

    /**
     * Главный метод
     * В нем создается обьект класса SimplyBlockingQuene, который хранит в себе коллекцию очереди из целых чисел
     * Затем создается массив (я не совсем понял почему можно использовать только ссылочные типы данных)
     *              с единственным значением булевым значением. Данное значение является маркером для определния
     *              работы первого потока.
     * После этого создается и запускается поток consumer, который выводит на печать первый элемент из очереди.
     * Так как в самом начале в очереди ничего нет, то данный поток переходит в режим ожидания.
     * Затем создается новый поток, который добавляет в очередть элементы.
     * Так как он это делает при помощи метода offer, то при каждом добавлении поток consumer оповещается о том, что
     *              в очередь были добавлены элементы. В следствии этого каждый раз при добавлении элемента печатается
     *              добавленный элемент в консоль.
     * @param args
     */
    public static void main(String[] args) {
        SimplyBlockingQuene<Integer> queue = new SimplyBlockingQuene<Integer>();
        final boolean[] res = new boolean[] {false};
        final Thread consumer = new Thread(
                () -> {
                    while (!res[0]) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        res[0] = (index == 2);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();
    }
}
