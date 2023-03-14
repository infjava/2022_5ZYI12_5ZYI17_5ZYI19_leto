package zobrazenie;

import akcie.Akcia;
import fri.shapesge.FontStyle;
import fri.shapesge.Manazer;
import fri.shapesge.Text;
import hlavnyBalik.Policko;
import zemeplocha.Zemeplocha;

import java.util.ArrayList;

public class Obrazovka {
    public static final int ROZMER_POLICKA = 70;

    private final HernePole[][] hernePolicka;
    private final Manazer manazer;

    private HernePole zvoleneHernePolicko;
    private Policko zvolenePolicko;

    private final Text zvolenePolickoText;
    private final Zemeplocha zemeplocha;

    private ArrayList<TlacidloAkcie> akcie;

    public Obrazovka(Zemeplocha zemeplocha) {
        this.zemeplocha = zemeplocha;
        this.manazer = new Manazer();
        this.hernePolicka = new HernePole[this.zemeplocha.getPocetPolicok()][this.zemeplocha.getPocetPolicok()];

        for (int i = 0; i < this.hernePolicka.length; i++) {
            for (int j = 0; j < this.hernePolicka[i].length; j++) {
                this.hernePolicka[i][j] = new HernePole(j * ROZMER_POLICKA, i * ROZMER_POLICKA + 70, ROZMER_POLICKA);
            }
        }

        this.zvolenePolickoText = new Text("", 10, 20);
        this.zvolenePolickoText.zmenFont("Consolas", FontStyle.BOLD, 12);
        this.zvolenePolickoText.zobraz();

        this.akcie = new ArrayList<>();

        this.manazer.spravujObjekt(this);
    }
    
    public void zobraz() {
        for (int i = 0; i < this.hernePolicka.length; i++) {
            for (int j = 0; j < this.hernePolicka[i].length; j++) {
                Policko policko = this.zemeplocha.getPolicko(i, j);
                this.hernePolicka[i][j].zmenFarbu(policko.getFarba());
                this.hernePolicka[i][j].nastavObrazok(policko.getObrazok());
                this.hernePolicka[i][j].nastavText(policko.getText());
            }
        }
        if (this.zvolenePolicko != null) {
            this.zvolenePolickoText.changeText(this.zvolenePolicko.getPopis());
        }

    }

    public void vyberSuradnice(int x, int y) {
        //Najskôr kontrola akcii
        for (TlacidloAkcie akcia : this.akcie) {
            if (akcia.obsahujeSuradnicu(x, y)) {
                akcia.vykonaj(this.zemeplocha);
            }
            this.skryAkcie();
            this.zobraz();
            return;
        }

        for (int i = 0; i < this.hernePolicka.length; i++) {
            for (int j = 0; j < this.hernePolicka[i].length; j++) {
                HernePole policko = this.hernePolicka[i][j];
                if (policko.obsahujeSuradnicu(x, y)) {
                    if (this.zvoleneHernePolicko != null) {
                        this.zvoleneHernePolicko.zvyrazniPolicko(false);
                    }
                    this.zvolenePolicko = this.zemeplocha.getPolicko(i, j);
                    this.zvoleneHernePolicko = policko;
                    this.zvoleneHernePolicko.zvyrazniPolicko(true);
                    this.zvolenePolickoText.changeText(this.zvolenePolicko.getPopis());
                }
            }
        }
        this.skryAkcie();
    }

    public void otvorAkcie(int x, int y) {
        this.skryAkcie();
        if (this.zvoleneHernePolicko == null) {
            return;
        }
        for (int i = 0; i < this.hernePolicka.length; i++) {
            for (int j = 0; j < this.hernePolicka[i].length; j++) {
                HernePole policko = this.hernePolicka[i][j];
                if (policko.obsahujeSuradnicu(x, y)) {
                    var aktualneAkcie = this.zvolenePolicko.dajAkcieNa(this.zemeplocha.getPolicko(i, j));

                    for (Akcia akcia : aktualneAkcie) {
                        var tlacidlo = new TlacidloAkcie(x, y + this.akcie.size() * 20, akcia);
                        this.akcie.add(tlacidlo);
                    }
                }
            }
        }
    }

    //Toto aj tak nefunguje lebo bug v SBGE
    public void mouseMove(int x, int y) {
        for (TlacidloAkcie tlacidloAkcie : this.akcie) {
            tlacidloAkcie.zrusZvyraznenie();
            if (tlacidloAkcie.obsahujeSuradnicu(x, y)) {
                tlacidloAkcie.zvyrazni();
            }
        }
    }

    public void skryAkcie() {
        for (TlacidloAkcie tlacidloAkcie : this.akcie) {
            tlacidloAkcie.skry();
        }
        this.akcie.clear();
    }
}
