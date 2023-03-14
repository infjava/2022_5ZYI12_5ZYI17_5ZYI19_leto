package obyvatelia;

import akcie.Akcia;
import akcie.AkciaPresidlenie;
import hlavnyBalik.Policko;

import java.util.ArrayList;

public class Tvory {
    //pocet kusov danych tvorov tvoriacich obyvatelstvo
    private int populacia;
    private final TypObyvatela typObyvatela;

    public Tvory(int populacia, TypObyvatela typObyvatela) {
        this.populacia = populacia;
        this.typObyvatela = typObyvatela;
    }

    public void upravPopulaciu(int populacia) {
        //zmena populacie nejde do minusu..ziadni zaporni obyvatelia to nieee
        if ((this.populacia + populacia) > 0) {
            this.populacia += populacia;
        }  else {
            this.populacia = 0;  //pripadne miesto na vynimku
        }
    }

    public int getPopulacia() {
        return this.populacia;
    }

    public String getMeno() {
        return this.typObyvatela.name().toLowerCase();
    }

    public String getFarba() {
        return this.typObyvatela.getFarba();
    }

    public TypObyvatela getTypObyvatela() {
        return this.typObyvatela;
    }

    public String getVypis() {
        return "My sme " + this.getMeno() + "\na mame " + this.populacia + " obyvatelov";
    }

    public String getObrazok() {
        return this.typObyvatela.getObrazok();
    }

    public ArrayList<Akcia> dajAkcieNa(Policko mojePolicko, Policko druhePolicko) {
        ArrayList<Akcia> akcie = new ArrayList<>();
        //viem sa presidlit len na prazdne police = pustatinu, miesanie tvorov roznych druhov nepovolujeme
        if (druhePolicko.getObyvatelia().isEmpty()) {
            akcie.add(new AkciaPresidlenie(mojePolicko, druhePolicko));
        }
        return akcie;
    }

    public Tvory dajTvoryNaPresidlenie(int pocetTvorov) {
        this.upravPopulaciu(-pocetTvorov);
        //tito budu priradeni na cielove policko presidlenia
        return this.vytvorTvory(pocetTvorov);
    }

    public Tvory vytvorTvory(int pocetTvorov) {
        return new Tvory(pocetTvorov, this.typObyvatela);
    }
}
