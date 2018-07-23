package ru.job4j.array;

/**
 * Class for cheking prefix of word
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 1.1
 */
public class ArrayChar {
    /**
     * Private variable contains some word for checking
     */
    private char[] data;

    /**
     * Constructor for this class
     * @param word - single private variable
     */
    public ArrayChar(String word) {
        this.data = word.toCharArray();
    }

    /**
     * Func for checking that word (private variable) start from this prefix
     * @param prefix
     * @return word really start from this prefix (true) or not (false)
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int index = 0; index != value.length; index++) {
            if (this.data[index] != value[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
