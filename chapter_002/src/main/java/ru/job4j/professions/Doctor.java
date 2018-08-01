package ru.job4j.professions;

public class Doctor extends Profession {

    public Diagnose heal(Patient patient) {
        Diagnose diagnose;
        String result =  patient.getSymptoms();
        if (result.equals("кашель")) {
            diagnose = new Diagnose("Болезнь легких", "diagnose");
        } else {
            diagnose = new Diagnose("Не болезнь легких", "diagnose");
        }
        return diagnose;
    }
}
