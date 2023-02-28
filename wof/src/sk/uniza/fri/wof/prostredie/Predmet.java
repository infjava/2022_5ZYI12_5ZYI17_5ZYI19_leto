package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.zaklad.Hrac;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Predmet {
    private final String nazov;
    private boolean navlekySuObute;

    public Predmet(String nazov) {
        this.nazov = nazov;
    }

    public String getNazov() {
        return this.nazov;
    }

    public void pouzi() {
        switch (this.nazov) {
            case "hodinky" -> {
                String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
                System.out.printf("Aktualny cas je %s%n", timeStamp);
            }
            case "navleky" -> {
                if (this.navlekySuObute) {
                    System.out.println("Vyzul si si navleky");
                } else {
                    System.out.println("Obul si si navleky");
                }
                this.navlekySuObute = !this.navlekySuObute;
            }
            default -> {
                System.out.format("Predmet %s sa neda pouzit!%n", this.nazov);
            }
        }
    }
}
