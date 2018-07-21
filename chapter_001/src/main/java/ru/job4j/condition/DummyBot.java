package ru.job4j.condition;

/**
 * Class for dummy Bot
 * @author Sergey Galanov
 * @since 21.07.2018
 * @version 1.0
 */
public class DummyBot {

    /**
     * Func for answer the question from user
     * @param question - question (String) from user to Bot
     * @return - answer from bot
     */
    public String questions(String question) {
        if (question.equals("Привет, Бот.")) {
            return "Привет, умник";
        } else if (question.equals("Пока.")) {
            return "До скорой встречи.";
        } else {
            return "Это ставит меня в тупик. Спросите другой вопрос.";
        }
    }

}
