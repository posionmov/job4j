package ru.job4j.condition;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for func from DummyBot.java
 */
public class DummyBotTest {

    /**
     * Test for func "question" if say to bot "Hi"
     */
    @Test
    public void whenHiBotThenHiFromBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.questions("Привет, Бот."),is("Привет, умник"));
    }

    /**
     * Test for func "question" if say to bot "Bye"
     */
    @Test
    public void whenByeBotThanByeFromBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.questions("Пока."),is("До скорой встречи."));
    }

    /**
     * Test for func "question" if say to bot "qwerty"
     */
    @Test
    public void whenQwertyThanBotDoNotUnderstand() {
        DummyBot bot = new DummyBot();
        assertThat(bot.questions("qwerty"),is("Это ставит меня в тупик. Спросите другой вопрос."));
    }
}
