package akcie;

import hlavnyBalik.Policko;
import obyvatelia.Tvory;
import zemeplocha.Zemeplocha;

import javax.swing.JOptionPane;

public class AkciaPresidlenie implements Akcia {
    //kto sa chce presidlit

    private final Policko mojePolicko;
    //kam sa chce presidlit
    private final Policko druhePolicko;

    public AkciaPresidlenie(Policko mojePolicko, Policko druhePolicko) {

        this.mojePolicko = mojePolicko;
        this.druhePolicko = druhePolicko;
    }

    @Override
    public String getNazov() {
        return "Presidlenie";
    }


    @Override
    public void vykonaj(Zemeplocha zemeplocha) {
        //presidluje sa nejaka cast populacie
        var pocetTvorov = Integer.parseInt( JOptionPane.showInputDialog( null,
                "Zadaj pocet tvorov, ktore sa idu presidlit.") );
        Tvory ja = this.mojePolicko.getObyvatelia().orElseThrow();
        if (pocetTvorov > ja.getPopulacia()) {
            //tolko tvorov policko nema
            JOptionPane.showMessageDialog( null, "Zvolene tvory nemaju taky pocet obyvatelov.");
        } else {
            //uz naozaj presidlujeme, z povodneho policka odidu a druhemu policku sa nastavia ako obyvatel
            this.druhePolicko.setObyvatelia(ja.dajTvoryNaPresidlenie(pocetTvorov));
            //presidlili sa vsetci
            if (ja.getPopulacia() <= 0) {
                this.mojePolicko.zruseniObyvatelia();
            }
        }
    }
}
