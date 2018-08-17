package ru.job4j.tree;

import java.util.*;

/**
 * Класс, реализующий работу с импровизированным древом
 * @author Galanov Sergey
 * @since 17.08.2018
 * @version 1.0
 * @param <E>
 */
public class SimpleTreeRealise<E extends Comparable<E>> implements SimpleTree {

    /**
     * Содержит приватные поля класса
     */
    private Node<E> root; //Родительский обьект класса Node

    /**
     * Конструктор класса
     * @param value значение класса Е, которое требуеться записать в данный обьект
     */
    public SimpleTreeRealise(E value) {
        this.root = new Node<E>(value);
    }

    /**
     * Метод добавления нового ребенка родителю
     * @param parent Родитель, ребенка которому требуется вставить
     * @param children вставляемый ребенок
     * @return true если такого ребенка нет и он вставился
     */
    @Override
    public boolean add(Comparable parent, Comparable children) {
        Node newChild = new Node<>(children);
        boolean result = false;
        Optional<Node<E>> findParent = this.findBy(parent); // поиск родителя
        Node<E> finder = Optional.of(findParent).get().get();
        if (!findParent.equals(Optional.empty()) && !this.isExist(finder, children)) { // Если найденный родидель не пустой и не содержит ребенка
            finder.add(newChild);
        }
        return result;
    }

    /**
     * Переопределнный метод интерфейса SimpleTree
     * Призводит поиск по древу
     * @param value значение, которое требуется найти в древе
     * @return обьект класса Optional, содержащий найденный элемент
     */
    @Override
    public Optional<Node<E>> findBy(Comparable value) {
        Optional<Node<E>> result = Optional.empty(); // Результативная переменная
        Queue<Node<E>> data = new LinkedList<>(); // Создание LinkedList и Queue как впомогательную переменную, хранящую все дерево
        data.offer(this.root); // Во вспомогательную переменную записывается текущее значение списка
        while (!data.isEmpty()) { // Цикл, работающий пока вспомогательная переменная не пустая
            Node<E> el = data.poll(); // Присваивает переменной el первое значение из списка и удаляет его из списка
            if (el.eqValue((E) value)) { // Если это значение равно передаваемому
                result = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Вспомогательный метод, проверяющий есть ли у данного обьекта определнный сын
     * @param parent родиьель, в котором требуется проверить всех детей
     * @param children ребенок для поиска
     * @return true если такой ребенок уже есть у родителя
     */
    private boolean isExist(Node<E> parent, Comparable children) {
        boolean result = false;
        List<Node<E>> listOfChildren = parent.leaves();
        for (int i = 0; i < listOfChildren.size(); i++) {
            if (children.equals(listOfChildren.get(i))) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {

            private int curIndex = 0;
            private List<Node<E>> listOfTheseChilds = root.leaves();

            @Override
            public boolean hasNext() {
                return this.checkNext();
            }

            @Override
            public Object next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.listOfTheseChilds.get(this.curIndex++);
            }

            private boolean checkNext() {
                boolean result = false;
                for (int i = curIndex; i < this.listOfTheseChilds.size(); i++) {
                    if (listOfTheseChilds.get(i++) != null) {
                        result = true;
                        this.curIndex++;
                        break;
                    }
                }
                return result;
            }
        };
    }
}
