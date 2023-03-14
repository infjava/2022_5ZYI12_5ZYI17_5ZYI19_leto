package obyvatelia;

import akcie.Akcia;
import akcie.AkciaMnozenie;
import hlavnyBalik.Policko;

import java.util.ArrayList;

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

    public void kuzloMnozenia(Policko cielMnozenia) {
        var mnozenyObyvatelia = cielMnozenia.getObyvatelia();

        if (mnozenyObyvatelia.isPresent() && !(mnozenyObyvatelia.get() instanceof Magovia)) {
            mnozenyObyvatelia.get().upravPopulaciu(
                    mnozenyObyvatelia.get().getPopulacia() * this.koeficientMagie
            );
        }
    }

    @Override
    public ArrayList<Akcia> dajAkcieNa(Policko mojePolicko, Policko druhePolicko) {
        var akcie = super.dajAkcieNa(mojePolicko, druhePolicko);

        var mnozenyObyvatelia = druhePolicko.getObyvatelia();

        if (mnozenyObyvatelia.isPresent() && !(mnozenyObyvatelia.get() instanceof Magovia)) {
            akcie.add(new AkciaMnozenie(this, druhePolicko));
        }
        return akcie;
    }

    @Override
    public Tvory vytvorTvory(int pocetTvorov) {
        return new Magovia(pocetTvorov, this.koeficientMagie);
    }
}
