package ru.job4j.professions;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;


public class ProfessionsTest {

    @Test
    public void whenDoctorHealThenDiagnose() {
        Doctor doctor = new Doctor();
        Patient patient = new Patient("Robert");
        Diagnose diagnose = new Diagnose("name", "desc");
        assertThat((String) (doctor.heal(patient).name), is("name"));
    }

    @Test
    public void whenAskingNameThenAnswer() {
        String[] names = new String[3];
        Doctor doctor = new Doctor();
        doctor.name = "Doc";
        names[0] = doctor.name;
        Teacher teacher = new Teacher();
        teacher.name = "Teach";
        names[1] = teacher.name;
        Ingener ingener = new Ingener();
        ingener.name = "Inge";
        names[2] = ingener.name;
        boolean result = names[0] == doctor.getName()
                && names[1] == teacher.getName()
                && names[2] == ingener.getName();
        boolean expect = true;
        assertThat(result, is(expect));
    }

}
