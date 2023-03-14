package obyvatelia;

import akcie.Akcia;
import akcie.AkciaLov;
import hlavnyBalik.Policko;

import java.util.ArrayList;

public class Lovci extends Tvory {
    public Lovci(int populacia) {
        super(populacia, TypObyvatela.LOVCI);
    }

    @Override
    public Tvory vytvorTvory(int pocetTvorov) {
        return new Lovci(pocetTvorov);
    }

    public void lov(Policko ciel, int pocetLovcov) {
        int pouzityLovci;
        if (pocetLovcov > this.getPopulacia()) {
            pouzityLovci = this.getPopulacia();
        } else {
            pouzityLovci = pocetLovcov;
        }

        var vrazdenyObyvatelia = ciel.getObyvatelia();

        if (vrazdenyObyvatelia.isPresent() && !(vrazdenyObyvatelia.get() instanceof Magovia) && !(vrazdenyObyvatelia.get() instanceof Lovci)) {
            vrazdenyObyvatelia.get().upravPopulaciu(-pocetLovcov);
            if (vrazdenyObyvatelia.get().getPopulacia() <= 0) {
                ciel.zruseniObyvatelia();
            }
        }
    }

    @Override
    public ArrayList<Akcia> dajAkcieNa(Policko mojePolicko, Policko druhePolicko) {
        var akcie = super.dajAkcieNa(mojePolicko, druhePolicko);

        var vrazdenyObyvatelia = druhePolicko.getObyvatelia();

        if (vrazdenyObyvatelia.isPresent() && !(vrazdenyObyvatelia.get() instanceof Magovia) && !(vrazdenyObyvatelia.get() instanceof Lovci)) {
            akcie.add(new AkciaLov(this, druhePolicko));
        }

        return akcie;
    }
}
