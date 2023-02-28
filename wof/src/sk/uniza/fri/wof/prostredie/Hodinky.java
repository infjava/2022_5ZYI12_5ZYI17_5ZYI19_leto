package sk.uniza.fri.wof.prostredie;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hodinky implements Predmet {
    @Override
    public String getNazov() {
        return "hodinky";
    }

    @Override
    public void pouzi() {
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.printf("Aktualny cas je %s%n", timeStamp);
    }
}
