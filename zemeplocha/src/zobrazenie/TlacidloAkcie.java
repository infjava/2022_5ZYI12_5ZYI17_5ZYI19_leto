package zobrazenie;

import akcie.Akcia;
import fri.shapesge.FontStyle;
import fri.shapesge.Obdlznik;
import fri.shapesge.Text;
import zemeplocha.Zemeplocha;

public class TlacidloAkcie {
    public static final int SIRKA_TLACIDLA = 150;
    public static final int VYSKA_TLACIDLA = 19;
    private final int x;
    private final int y;
    private final Akcia akcia;
    private final Obdlznik pozadie;
    private final Obdlznik ramik;
    private final Text text;

    TlacidloAkcie(int x, int y, Akcia akcia) {
        if (x + SIRKA_TLACIDLA > 630) {
            x = 630 - SIRKA_TLACIDLA;
        }

        this.x = x;
        this.y = y;
        this.akcia = akcia;

        this.ramik = new Obdlznik(this.x, this.y);
        this.ramik.zmenStrany(SIRKA_TLACIDLA, VYSKA_TLACIDLA);
        this.ramik.zmenFarbu("#34495e");
        this.ramik.zobraz();

        this.pozadie = new Obdlznik(this.x + 1, this.y + 1);
        this.pozadie.zmenStrany(SIRKA_TLACIDLA - 2, VYSKA_TLACIDLA - 2);
        this.pozadie.zmenFarbu("#ecf0f1");
        this.pozadie.zobraz();

        this.text = new Text(this.akcia.getNazov(), this.x + 2, this.y + 13);
        this.text.zmenFont("Consolas", FontStyle.BOLD, 15);
        this.text.zobraz();
    }


    public void skry() {
        this.ramik.skry();
        this.pozadie.skry();
        this.text.skry();
    }

    public void zrusZvyraznenie() {
        this.text.zmenFarbu("black");
    }

    public void zvyrazni() {
        this.text.zmenFarbu("#e74c3c");
    }

    boolean obsahujeSuradnicu(int x, int y) {
        return x >= this.x && x <= this.x + SIRKA_TLACIDLA && y >= this.y && y <= this.y + VYSKA_TLACIDLA;
    }

    public void vykonaj(Zemeplocha zemeplocha) {
        this.akcia.vykonaj(zemeplocha);
    }
}
