package ru.job4j.notify;

import org.junit.Assert;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования частично блокирующей работу хэш мапы
 * @author Galanov Sergey
 * @since 24.08.2018
 * @version 1.0
 */
public class CashBaseTest {

    /**
     * Тестировнаие на выкидывание исключений, когда каждый поток пытается изменить обьект, у которого он не совпадает
     */
    @Test
    public void whenBlaThenBla() {
        AtomicReference<Exception> ex = new AtomicReference<>();
        CashBase base = new CashBase();
        Base base1 = new Base(1, 1, "Michail");
        base.add(base1);
        Thread one = new Thread(() -> {
            try {
                base.update(new Base(1, 1, "michail1"));
            } catch (Exception e) {
                ex.set(e);
            }
        });
        Thread two = new Thread(() -> {
            try {
                base.update(new Base(1, 5, "michail2"));
            } catch (Exception e) {
                ex.set(e);
            }
        });
        Thread three = new Thread(() -> {
            try {
                base.update(new Base(1, 2, "michail3"));
            } catch (Exception e) {
                ex.set(e);
            }
        });
        one.start();
        two.start();
        three.start();
        Assert.assertThat(ex.get().getMessage(), is("Обьект не актуальный"));
    }

    /**
     * Тестирование хранилища при условии, что у каждого потока совпадает каждый элемент и они выстроены в правильной очереди
     * В результате не выскакивают сключительные ситуации
     */
    @Test
    public void whenbalblaThenBla() {
        AtomicReference<Exception> ex = new AtomicReference<>();
        CashBase base = new CashBase();
        Base base1 = new Base(1, 1, "Michail");
        base.add(base1);
        Thread one = new Thread(() -> base.update(new Base(1, 1, "michail1")));
        Thread two = new Thread(() -> base.update(new Base(1, 2, "michail2")));
        Thread three = new Thread(() -> base.update(new Base(1, 3, "michail3")));
        one.start();
        two.start();
        three.start();
        assertThat(base.findById(1).version, is(3));
    }
}