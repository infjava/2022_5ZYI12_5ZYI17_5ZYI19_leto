package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.zaklad.Hrac;

public class Radio implements Predmet {
    private boolean maBaterky;

    @Override
    public String getNazov() {
        return "radio";
    }

    @Override
    public void pouzi(Hrac hrac) {
        if (this.maBaterky) {
            System.out.println("Radio vyhrava: ♩♪♫♬♭♮♩♪♫");
        } else {
            System.out.println("Radio sa neda pouzit");
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

    public void vlozBaterky() {
        this.maBaterky = true;
    }
}
