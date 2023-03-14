package obyvatelia;

public enum TypObyvatela {

    SLIEPKY ("#9b899b6", "chicken.png" ),
    SRNKY ("#16a085", "deer.png"),
    ZAJACE ("#2ecc71", "rabbit.png" );


    private final String farba;
    private final String obrazok;

    TypObyvatela(String farba, String obrazok) {
        this.farba = farba;
        this.obrazok = obrazok;
    }

    public String getFarba() {
        return this.farba;
    }

    public String getObrazok() {
        return this.obrazok;
    }
}


















//    MAGOVIA ("#d35400", "wizard.png"),
//    LOVCI("#f39c12", "caveman.png"),
//    RYTIERI("#9b59b6", "knight.png")
