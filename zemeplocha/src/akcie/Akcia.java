package akcie;

import zemeplocha.Zemeplocha;

public interface Akcia {
    //nazov akcie
    String getNazov();
    //vykonanie akcie
    void vykonaj(Zemeplocha zemeplocha);
}
