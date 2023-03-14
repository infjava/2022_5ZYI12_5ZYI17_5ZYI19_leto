package akcie;

import hlavnyBalik.Policko;
import obyvatelia.Magovia;
import zemeplocha.Zemeplocha;

public class AkciaMnozenie implements Akcia {
    private final Magovia magovia;
    private final Policko druhePolicko;

    public AkciaMnozenie(Magovia magovia, Policko druhePolicko) {
        this.magovia = magovia;
        this.druhePolicko = druhePolicko;
    }

    @Override
    public String getNazov() {
        return "Kúzlo množenia";
    }

    @Override
    public void vykonaj(Zemeplocha zemeplocha) {
        this.magovia.kuzloMnozenia(this.druhePolicko);
    }
}
