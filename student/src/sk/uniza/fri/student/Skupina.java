package sk.uniza.fri.student;

import java.util.ArrayList;

public class Skupina {
    private final String cisloSkupiny;
    private final ArrayList<Student> studenti;

    public Skupina(String cisloSkupiny) {
        this.cisloSkupiny = cisloSkupiny;
        this.studenti = new ArrayList<>();
    }

    public String getCisloSkupiny() {
        return this.cisloSkupiny;
    }

    public void pridaj(Student jozo) {
        this.studenti.add(jozo);
    }

    public void vypis() {
        for (Student student : this.studenti) {
            System.out.println(student);
        }
    }
}
