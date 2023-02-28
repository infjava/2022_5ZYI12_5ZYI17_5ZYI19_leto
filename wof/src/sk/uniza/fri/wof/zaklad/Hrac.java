package sk.uniza.fri.wof.zaklad;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Predmet;

import java.util.HashMap;

public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final HashMap<String, Predmet> inventar;

    public Hrac(Miestnost aktualnaMiestnost) {
        this.aktualnaMiestnost = aktualnaMiestnost;
        this.inventar = new HashMap<>();
    }

    public void posunVSmere(String smer) {
        Miestnost novaMiestnost = this.aktualnaMiestnost.getMiestnostVSmere(smer);
        if (novaMiestnost == null) {
            System.out.println("Tam nie je vychod!");
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            this.aktualnaMiestnost.vypisInfoOMiestnosti();
        }
    }

    public void zoberPredmet(String predmet) {
        Predmet zdvihnutyPredmet = this.aktualnaMiestnost.zoberPredmet(predmet);
        this.inventar.put(zdvihnutyPredmet.getNazov(), zdvihnutyPredmet);
    }

    public void polozPredmet(String predmet) {
        Predmet pokladanyPredmet = this.inventar.get(predmet);
        if (pokladanyPredmet.daSaPolozit()) {
            this.aktualnaMiestnost.polozPredmet(pokladanyPredmet);
            this.inventar.remove(predmet);
        } else {
            System.out.format("Nemozes polozit %s!%n", pokladanyPredmet.getNazov());
        }
    }

    /**
     * vypise inventar do terminalu
     *
     */
    public void vypisInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Zbytocne pozeras, ved tu nic nie je");
        } else {
            System.out.println("Tvoj inventar obsahuje:");
            for (var predmet : this.inventar.keySet()) {
                System.out.printf("- %s%n", predmet);
            }
        }
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public void pouziPredmet(String nazovPredmetu) {
        var predmet = this.inventar.get(nazovPredmetu);
        if (predmet != null) {
            predmet.pouzi();
        } else {
            System.out.println("Predmet nemas.");
        }
    }

}
