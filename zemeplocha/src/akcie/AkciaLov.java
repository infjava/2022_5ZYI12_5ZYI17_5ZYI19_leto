package akcie;

import hlavnyBalik.Policko;
import obyvatelia.Lovci;
import zemeplocha.Zemeplocha;

import javax.swing.*;

public class AkciaLov implements Akcia {
    private final Lovci lovci;
    private final Policko druhePolicko;

    public AkciaLov(Lovci lovci, Policko druhePolicko) {
        this.lovci = lovci;
        this.druhePolicko = druhePolicko;
    }

    @Override
    public String getNazov() {
        return "Lov";
    }

    @Override
    public void vykonaj(Zemeplocha zemeplocha) {
        var pocetLovcov = Integer.parseInt( JOptionPane.showInputDialog( null,
                "Zadaj pocet lovcov, ktori idu lovit.") );
        this.lovci.lov(this.druhePolicko, pocetLovcov);
    }
}
