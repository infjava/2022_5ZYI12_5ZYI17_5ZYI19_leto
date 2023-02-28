package sk.uniza.fri.wof.prostredie;

public class ObycajnyPredmet implements Predmet {
    private final String nazov;

    public ObycajnyPredmet(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public void pouzi() {
        System.out.format("Predmet %s sa neda pouzit!%n", this.nazov);
    }
}
