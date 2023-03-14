package zobrazenie;

import fri.shapesge.FontStyle;
import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import fri.shapesge.Text;

class HernePole {
    private Obrazok obrazok;
    private final Obdlznik pozadie;
    private final Obdlznik oramovanie;
    private Text text;

    private final int x;
    private final int y;
    private final int velkost;

    private String farba;
    private boolean jeZvyraznene;

    HernePole(int x, int y, int velkost) {
        this.x = x;
        this.y = y;
        this.velkost = velkost;

        this.farba = "white";
        this.jeZvyraznene = false;

        this.oramovanie = new Obdlznik(this.x, this.y);
        this.oramovanie.zmenStrany(velkost, velkost);
        this.oramovanie.zmenFarbu(this.farba);
        this.oramovanie.zobraz();

        this.pozadie = new Obdlznik(this.x + 5, this.y + 5);
        this.pozadie.zmenStrany(velkost - 10, velkost - 10);
        this.pozadie.zmenFarbu(this.farba);
        this.pozadie.zobraz();
    }


    void zmenFarbu(String farba) {
        this.farba = farba;
        this.pozadie.zmenFarbu(this.farba);
        if (!this.jeZvyraznene) {
            this.oramovanie.zmenFarbu(this.farba);
        }
    }

    void nastavObrazok(String cestaKObrazku) {
        if (this.obrazok == null && cestaKObrazku != null) {
            this.obrazok = new Obrazok(cestaKObrazku, this.x + 10, this.y);
            this.obrazok.zobraz();
        } else if (this.obrazok != null && cestaKObrazku != null) {
            this.obrazok.zmenObrazok(cestaKObrazku);
        } else if (this.obrazok != null) {
            this.obrazok.skry();
            this.obrazok = null;
        }
    }

    void nastavText(String text) {
        if (text == null) {
            text = "";
        }

        if (text.length() > 10) {
            text = String.join("\n", text.split(" "));
        }

        if (this.text != null) {
            this.text.skry();
        }
        this.text = new Text(text, this.x + 5, this.y + this.velkost - (4 + 10 * (text.split("\n").length - 1)));
        this.text.zmenFont("Consolas", FontStyle.BOLD, 10);
        this.text.zobraz();
    }

    boolean obsahujeSuradnicu(int x, int y) {
        return x >= this.x && x <= this.x + this.velkost && y >= this.y && y <= this.y + this.velkost;
    }

    void zvyrazniPolicko(boolean zvyraznenie) {
        this.jeZvyraznene = zvyraznenie;
        if (this.jeZvyraznene) {
            this.oramovanie.zmenFarbu("#f1c40f");
        } else {
            this.oramovanie.zmenFarbu(this.farba);
        }
    }
}
