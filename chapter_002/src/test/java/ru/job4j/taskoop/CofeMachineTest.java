package ru.job4j.taskoop;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CofeMachineTest {

    private PrintStream printstream;
    private ByteArrayOutputStream out;

    @Before
    public void beforeTestBegin() {
        this.printstream = System.out;
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void afterTestBeginn() {
        System.setOut(this.printstream);
    }

    @Test
    public void whenLotteThanResult() {
        ValidateInput input = new ValidateInput(new InputForTests(new String[] {"1", "22"}));
        new CofeMachine(input).init();
        assertThat(new String(out.toByteArray()), is("qwe"));

    }


}
