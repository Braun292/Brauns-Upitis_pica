package pizza;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class PasutijumaApskate {
    private static final String FAILA_NOSAUKUMS = "pasutijumi.txt";

    public static void main(String[] args) {
        apskatitPasutijumus();
    }

    public static void apskatitPasutijumus() {
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> picasSkaits = new LinkedHashMap<>();
        Map<String, Double> picasCenas = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FAILA_NOSAUKUMS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                String apraksts = d[3] + " pica ar " + d[4].replace("+", ", ") +
                        (d[5].equals("Bez mērces") ? "" : (" un " + d[5] + " mērci")) +
                        (d[6].equals("Jā") ? " (piegāde)" : "");
                
                picasSkaits.put(apraksts, picasSkaits.getOrDefault(apraksts, 0) + 1);
                picasCenas.put(apraksts, Double.parseDouble(d[7]));
            }
        } catch (IOException e) {
            sb.append("Nav pasūtījumu vai kļūda lasot failu!");
        }

        
        for (Map.Entry<String, Integer> entry : picasSkaits.entrySet()) {
            String pica = entry.getKey();
            int skaits = entry.getValue();
            double cena = picasCenas.get(pica) * skaits;

            sb.append("• ").append(pica).append(" x").append(skaits).append(" — ").append(cena).append("€\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Visi pasūtījumi", JOptionPane.INFORMATION_MESSAGE);
    }
}
