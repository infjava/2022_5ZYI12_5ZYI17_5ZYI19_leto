package obyvatelia;

public class Magovia extends Tvory {
    private final int koeficientMagie;

    public Magovia(int populacia, int koeficientMagie) {
        super(populacia, TypObyvatela.MAGOVIA);

        this.koeficientMagie = koeficientMagie;
    }

    @Override
    public String getVypis() {
        return String.format("Sme %d magov.%nNas koeficient magie je %d", this.getPopulacia(), this.koeficientMagie);
    }

    @Override
    public Tvory vytvorTvory(int pocetTvorov) {
        return new Magovia(pocetTvorov, this.koeficientMagie);
    }
}
