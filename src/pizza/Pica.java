package pizza;

import java.util.List;

public class Pica {
    private String vards;
    private String talrunis;
    private String adrese;
    private String izmers;
    private List<String> piedevas;
    private String merce;
    private boolean piegade;
    private double cena;

    public Pica(String vards, String talrunis, String adrese, String izmers, List<String> piedevas, String merce, boolean piegade) {
        this.vards = vards;
        this.talrunis = talrunis;
        this.adrese = adrese;
        this.izmers = izmers;
        this.piedevas = piedevas;
        this.merce = merce;
        this.piegade = piegade;
        this.cena = aprekinatCenu();
    }

    private double aprekinatCenu() {
        double cena = switch (izmers) {
            case "Maza" -> 5.0;
            case "Vidēja" -> 7.0;
            case "Liela" -> 10.0;
            default -> 0.0;
        };

        cena += piedevas.size() * 0.5;
        if (!merce.equals("Bez mērces")) cena += 0.5;
        if (piegade) cena += 5.0;

        return cena;
    }

    public String toFileString() {
        return vards + "," + talrunis + "," + adrese + "," + izmers + "," +
                String.join("+", piedevas) + "," + merce + "," +
                (piegade ? "Jā" : "Nē") + "," + cena;
    }

    public String toReadableString() {
        return izmers + " pica ar " + String.join(", ", piedevas) +
                (merce.equals("Bez mērces") ? "" : (" un " + merce + " mērci")) +
                (piegade ? " (piegāde)" : "");
    }

    public double getCena() {
        return cena;
    }
}
