package hlavnyBalik;

import javax.swing.JOptionPane;

import zemeplocha.Zemeplocha;
import zobrazenie.Obrazovka;

public class Hra {
    //udaje o polickach a ich osidleni
    private final Zemeplocha zem;
    //zobrazovanie jednotlivych obrazkov
    private final Obrazovka obraz;
    public Hra(int pocetPolicok) {
        JOptionPane.showMessageDialog( null, "VITAJTE NA KUZELNEJ ZEMEPLOCHE!");
        this.zem = new Zemeplocha(pocetPolicok);
        this.zem.nastavPolicka();
        this.obraz = new Obrazovka(this.zem);
        this.obraz.zobraz();
        
    }
}
