package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.zaklad.Hrac;

public class Baterka implements Predmet {
    @Override
    public String getNazov() {
        return "baterka";
    }

    @Override
    public void pouzi(Hrac hrac) {
        var radio = hrac.najdiPredmet("radio");
        if (radio.isPresent()) {
            ((Radio)radio.get()).vlozBaterky();
            hrac.vyberPredmetZInventara(this.getNazov());
            System.out.println("Baterku si vlozil do radia");
        } else {
            System.out.println("Baterka sa neda pouzit");
        }
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }

    @Override
    public boolean jeNasadeny() {
        return false;
    }
}
