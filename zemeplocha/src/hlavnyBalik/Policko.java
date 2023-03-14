package hlavnyBalik;

import akcie.Akcia;
import obyvatelia.Tvory;

import java.util.ArrayList;
import java.util.Optional;

public class Policko  {
    private Tvory obyvatelia;

    public Policko() {
        //na zaciatku nema obyvatelov
        this.obyvatelia = null;
    }

    public Optional<Tvory> getObyvatelia() {
        return Optional.ofNullable(this.obyvatelia);
    }

    public void setObyvatelia(Tvory obyvatelia) {
        this.obyvatelia = obyvatelia;
    }

    public void zruseniObyvatelia() {

        //ked vsetci obyvatelia zahynu , je tam potom pustatina
        this.obyvatelia = null;
    }

    public String getFarba() {
        //policko - pustatina je biele, ostatne maju svoju farbu
        return this.obyvatelia == null ? "white" : this.obyvatelia.getFarba();
    }

    public String getObrazok() {
        //policko bez obyvatela nema obrazok
        return this.obyvatelia == null ? null : this.obyvatelia.getObrazok();
    }

    public String getText() {
        //policko bez obyvatela je pustatina
        return this.obyvatelia == null ? "pustatina" : this.obyvatelia.getMeno();
    }

    public String getPopis() {
        //popis co sa zobrazuje
        return this.obyvatelia == null ? "pustatina, tu nič nieje" : this.obyvatelia.getVypis();
    }

    //zoznam akcii, ktore su na tomto policku mozne
    public ArrayList<Akcia> dajAkcieNa(Policko druhePolicko) {
        if (this.obyvatelia == null) {
            return new ArrayList<>();
        }
        return this.obyvatelia.dajAkcieNa(this, druhePolicko);
    }
}
