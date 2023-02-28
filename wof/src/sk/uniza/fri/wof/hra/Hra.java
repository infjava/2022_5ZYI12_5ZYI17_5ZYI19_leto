package sk.uniza.fri.wof.hra;

import sk.uniza.fri.wof.prikazy.Parser;
import sk.uniza.fri.wof.prikazy.Prikaz;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Predmet;

/**
 * Trieda sk.uniza.fri.wof.hra.Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * sk.uniza.fri.wof.hra.Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty
 * a zbierat/pokladat predmety. To je v tejto verzii vsetko. Hru treba skutocne
 * znacne rozsirit, aby bola zaujimava.
 *
 * sk.uniza.fri.wof.hra.Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. sk.uniza.fri.wof.hra.Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private final Parser parser;
    private final Hrac hrac;

    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        Miestnost miestnost = this.vytvorMiestnosti();
        this.hrac = new Hrac(miestnost);
        this.parser = new Parser();
    }

    /**
     * Vytvori mapu hry - miestnosti.
     *
     * @return startovacia miestnost
     */
    private Miestnost vytvorMiestnosti() {
        // vytvorenie miestnosti
        Miestnost terasa = new Miestnost("terasa - hlavny vstup na fakultu");
        Miestnost aula = new Miestnost("aula");
        Miestnost bufet = new Miestnost("bufet");
        Miestnost labak = new Miestnost("pocitacove laboratorium");
        Miestnost kancelaria = new Miestnost("kancelaria spravcu pocitacoveho laboratoria");
        Miestnost strecha = new Miestnost("strecha s peknym vyhladom");
        
        // inicializacia miestnosti = nastavenie vychodov
        terasa.nastavVychod("vychod", aula);
        terasa.nastavVychod("juh", labak);
        terasa.nastavVychod("zapad", bufet);
        terasa.nastavVychod("rebrik", strecha);
        aula.nastavVychod("zapad", terasa);
        bufet.nastavVychod("vychod", terasa);
        labak.nastavVychod("sever", terasa);
        labak.nastavVychod("vychod", kancelaria);
        kancelaria.nastavVychod("zapad", labak);

        terasa.polozPredmet(new Predmet("kamen"));
        labak.polozPredmet(new Predmet("mys"));
        bufet.polozPredmet(new Predmet("bageta"));

        return terasa;  // startovacia miestnost hry
    }

    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        do {
            Prikaz prikaz = this.parser.nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(prikaz);
        } while (!jeKoniec);
        
        System.out.println("Maj sa fajn!");
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        this.hrac.getAktualnaMiestnost().vypisMiestnost();
    }

    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Prikaz prikaz) {
        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();
        
        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.chodDoMiestnosti(prikaz);
                return false;
            case "zober":
                this.zoberPredmet(prikaz);
                return false;
            case "inventar":
                this.hrac.vypisInventar();
                return false;
            case "poloz":
                this.polozPredmet(prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            default:
                return false;
        }
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.println("   chod zober poloz inventar ukonci pomoc");
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();

        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        this.hrac.posunHraca(smer);
    }

    /**
     * zoberie predmet z miestnosti
     */
    private void zoberPredmet(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno co zobrat
            System.out.println("Zober co?");
            return;
        }

        String predmet = prikaz.getParameter();

        this.hrac.zoberPredmet(predmet);
    }

    /**
     * polozi predmet do miestnosti
     */
    private void polozPredmet(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno co polozit
            System.out.println("Poloz co?");
            return;
        }

        String predmet = prikaz.getParameter();

        this.hrac.polozPredmet(predmet);
    }

    /**
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * sk.uniza.fri.wof.prikazy.Prikaz ukoncenia nema parameter.
     * 
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }
}
