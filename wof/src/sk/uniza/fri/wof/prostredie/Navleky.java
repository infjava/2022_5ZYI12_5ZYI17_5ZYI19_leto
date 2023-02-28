package sk.uniza.fri.wof.prostredie;

public class Navleky implements Predmet {
    private boolean navlekySuObute;

    public Navleky() {
        this.navlekySuObute = false;
    }

    @Override
    public String getNazov() {
        return "navleky";
    }

    @Override
    public void pouzi() {
        if (this.navlekySuObute) {
            System.out.println("Vyzul si si navleky");
        } else {
            System.out.println("Obul si si navleky");
        }
        this.navlekySuObute = !this.navlekySuObute;

    }

    @Override
    public boolean daSaPolozit() {
        return !this.navlekySuObute;
    }
}
