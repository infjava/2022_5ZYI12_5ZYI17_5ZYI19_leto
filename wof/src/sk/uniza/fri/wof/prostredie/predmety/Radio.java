package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.zaklad.Hrac;

public class Radio implements Predmet, ReakciaNaPrechadzanie {
    private boolean maBaterky;

    @Override
    public String getNazov() {
        return "radio";
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }

    public void vlozBaterky() {
        this.maBaterky = true;
    }

    @Override
    public void hracZmenilMiestnost() {
        if (this.maBaterky) {
            System.out.println("Radio vyhrava: ♩♪♫♬♭♮♩♪♫");
        }
    }
}
