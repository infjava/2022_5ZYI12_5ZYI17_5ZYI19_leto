package sk.uniza.fri.wof.prostredie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Trieda sk.uniza.fri.wof.prostredie.Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi. 
 * Vychody z miestnosti su oznacovane svetovymi stranami sever, vychod, juh
 * a zapad. Pre kazdy vychod si miestnost pamata odkaz na susednu miestnost
 * alebo null, ak tym smerom vychod nema.
 *
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {
    private String popisMiestnosti;
    private HashMap<String, Miestnost> vychody;
    private ArrayList<Predmet> predmety;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.popisMiestnosti = popis;
        this.vychody = new HashMap<>();
        this.predmety = new ArrayList<>();
    }

    public void nastavVychod(String smer, Miestnost sever) {
        this.vychody.put(smer, sever);
    }

    /**
     * @return textovy popis miestnosti.
     */
    public String getPopis() {
        return this.popisMiestnosti;
    }

    /**
     * polozi predmet do miestnosti
     * @param predmet pokladany predmet
     */
    public void polozPredmet(Predmet predmet) {
        this.predmety.add(predmet);
    }

    /**
     * zoberie predmet z miestnosti
     * @param nazov nazov zdvihaneho predmetu
     * @return zdvihnuty predmet
     */
    public Optional<Predmet> zoberPredmet(String nazov) {
        for (Predmet kontrolovanyPredmet : this.predmety) {
            if (kontrolovanyPredmet.getNazov().equals(nazov)) {
                this.predmety.remove(kontrolovanyPredmet);
                return Optional.of(kontrolovanyPredmet);
            }
        }

        return Optional.empty();
    }

    public void vypisMiestnost() {
        System.out.println("Teraz si v miestnosti " + this.getPopis());
        System.out.print("Vychody: ");
        for (String vychod : this.vychody.keySet()) {
            System.out.printf("%s ", vychod);
        }
        System.out.println();

        if (!this.predmety.isEmpty()) {
            System.out.print("Predmety v miestnosti: ");
            for (Predmet predmet : this.predmety) {
                System.out.printf("%s ", predmet.getNazov());
            }
            System.out.println();
        }
    }

    public Optional<Miestnost> getMiestnostVSmere(String smer) {
        return Optional.ofNullable(this.vychody.get(smer));
    }
}
