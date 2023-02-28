import sk.uniza.fri.student.Skupina;
import sk.uniza.fri.student.Student;

public class Main {
    public static void main(String[] args) {
        Student jozo = new Student("Jozo", "Mrkvicka", "123");

        Skupina skupina = new Skupina("A1");
        skupina.pridaj(jozo);
        skupina.vypis();
    }
}