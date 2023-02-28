package sk.uniza.fri.wof.hra;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Predmet;

import java.util.ArrayList;
import java.util.Optional;

public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final ArrayList<Predmet> inventar;

    public Hrac(Miestnost startovaciaMiestnost) {
        this.aktualnaMiestnost = startovaciaMiestnost;
        this.inventar = new ArrayList<>();
    }

    void posunHraca(String smer) {
        var novaMiestnost = this.aktualnaMiestnost.getMiestnostVSmere(smer);

        if (novaMiestnost.isEmpty()) {
            System.out.println("Tam nie je vychod!");
        } else {
            this.aktualnaMiestnost = novaMiestnost.get();
            this.aktualnaMiestnost.vypisMiestnost();
        }
    }

    void zoberPredmet(String predmet) {
        Optional<Predmet> zdvihnutyPredmet = this.aktualnaMiestnost.zoberPredmet(predmet);
        if (zdvihnutyPredmet.isPresent()) {
            this.inventar.add(zdvihnutyPredmet.get());
        }
    }

    void polozPredmet(String predmet) {
        Predmet pokladanyPredmet = null;
        for (Predmet kontrolovanyPredmet : this.inventar) {
            if (kontrolovanyPredmet.getNazov().equals(predmet)) {
                pokladanyPredmet = kontrolovanyPredmet;
                break;
            }
        }

        this.inventar.remove(pokladanyPredmet);
        this.aktualnaMiestnost.polozPredmet(pokladanyPredmet);
    }

    /**
     * vypise inventar do terminalu
     *
     */
    void vypisInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Zbytocne pozeras, ved tu nic nie je");
        } else {
            System.out.println("Tvoj inventar obsahuje:");
            for (Predmet predmet : this.inventar) {
                System.out.printf("- %s%n", predmet.getNazov());
            }
        }
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }
}
