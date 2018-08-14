package ru.job4j.lists;

/**
 * Класс, реализующий алгоритм нахождения зацикленности
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.0
 */
public class LoopChecker {

    /**
     * Метод, который находит зависимости
     * Создаем две переменные, которые указывают на одну и ту же начальную позицию
     * Затем запускаем их в цикл, при этом одна переменная проходит в два раза больше позиций за раз, чем другая
     * При этом если быстрая позиция попадет в зацикленность (а она в любом случае попадет в нее если она есть
     * так как он не перескакивает элементы, а проходится по каждому из них) медленная переменная сровняется с быстрой.
     * При этом будет выход из цикла и присловаение true результату
     * @param first первый элемент связанного списка
     * @return true если в списке есть зацикленности
     */
    public boolean hasCycle(Node first) {
        boolean result = false;
        Node speedster = first;
        Node slower = first;
        while (speedster != null && speedster.nextElement != null) {
            slower = slower.nextElement;
            speedster = speedster.nextElement.nextElement;
            if (slower == speedster) {
                result = true;
                break;
            }
        }
        return result;
    }

}

/**
 * Класс, описывающий связанный список
 * @param <E>
 */
class Node<E> {

    /**
     * Содержит внутренние поля
     */
    public E value; // Значение данного элемента
    public Node<E> nextElement; // Следующий элемент

    /**
     * Конструктор класса
     * @param value значени, которое должно быть записанно во внутреннее поле данного класса
     */
    public Node(E value) {
        this.value = value;
    }
}
