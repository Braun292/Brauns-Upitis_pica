package braunsupitis_pica;

import javax.swing.*;
import java.io.*;

public class PasutijumaApskate {
    private static final String FAILA_NOSAUKUMS = "pasutijumi.txt";

    public static void main(String[] args) {
        apskatitPasutijumus();
    }

    public static void apskatitPasutijumus() {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(FAILA_NOSAUKUMS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                sb.append("Vārds: ").append(d[0])
                  .append("\nTelefons: ").append(d[1])
                  .append("\nAdrese: ").append(d[2])
                  .append("\nIzmērs: ").append(d[3])
                  .append("\nPiegāde: ").append(Boolean.parseBoolean(d[4]) ? "Jā" : "Nē")
                  .append("\nCena: ").append(d[5]).append("€")
                  .append("\n----------------\n");
            }
        } catch (IOException e) {
            sb.append("Nav pasūtījumu vai kļūda lasot failu!");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Visi pasūtījumi", JOptionPane.INFORMATION_MESSAGE);
    }
}
