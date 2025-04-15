package braunsupitis_pica;

public class Pica {
    private String vards;
    private String talrunis;
    private String adrese;
    private String izmers;
    private boolean piegade;
    private double cena;

    public Pica(String vards, String talrunis, String adrese, String izmers, boolean piegade) {
        this.vards = vards;
        this.talrunis = talrunis;
        this.adrese = adrese;
        this.izmers = izmers;
        this.piegade = piegade;
    }

    private double aprekinatCenu() {
        double cena = 0;
        switch (izmers) {
            case "Maza":
                cena += 5.0; 
                break;
            case "Videja":
                cena += 7.0;
                break;
            case "Liela":
                cena += 10.0;
                break;
        }

        if (piegade) {
            cena += 2.0;
        }
        return cena;
    }

    public String toFileString() {
        return vards + "," + talrunis + "," + adrese + "," + izmers + "," + piegade + "," + cena;
    }
}
