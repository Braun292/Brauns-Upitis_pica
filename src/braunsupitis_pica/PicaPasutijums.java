package braunsupitis_pica;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class PicaPasutijums {
    public static void main(String[] args) {
        ArrayList<String> pasutijumi = new ArrayList<>();
        double kopCena = 0;

        JOptionPane.showMessageDialog(null, "Sveicināti mūsu picērijā!", "Picērija", JOptionPane.INFORMATION_MESSAGE);
        String vards = ievadiTekstu("vārdu", "[a-zA-Z]+", "Tikai burti!");
        boolean piegade = false;
        String talrunis = "";
        String adrese = "";

        while (true) {
            String[] picuVeidi = {"Siera pica", "Peperoni pica", "Studentu pica", "Apmaksāt"};
            int veids = JOptionPane.showOptionDialog(null, "Izvēlieties picu vai apmaksāt:", "Pasūtījums",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, picuVeidi, picuVeidi[0]);

            if (veids == 3 || veids == -1) break;

            String[] izmeri = {"Maza (25cm)", "Vidēja (30cm)", "Liela (35cm)"};
            String izmers = (String) JOptionPane.showInputDialog(null, "Izvēlieties izmēru:", "Izmērs",
                    JOptionPane.QUESTION_MESSAGE, null, izmeri, izmeri[0]);
            if (izmers == null) continue;

            int daudzums = ievadiSkaitli("Cik šādu picu vēlaties?");
            if (daudzums < 1) continue;

            double cena = switch (veids) {
                case 0 -> 8; case 1 -> 11; case 2 -> 7; default -> 0;
            };
            if (izmers.contains("Vidēja")) cena += 2;
            else if (izmers.contains("Liela")) cena += 4;

            double daudzumaCena = cena * daudzums;
            kopCena += daudzumaCena;
            pasutijumi.add(picuVeidi[veids] + " - " + izmers + " x" + daudzums + " = " + daudzumaCena + "€");
        }

        int piegadesIzvele = JOptionPane.showOptionDialog(null,
                "Vēlaties saņemt uz vietas vai pasūtīt uz mājām?",
                "Piegāde",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Pasūtīt (+5€)", "Saņemt uz vietas"},
                "Pasūtīt");

        if (piegadesIzvele == 0) {
            piegade = true;
            kopCena += 5;
            talrunis = ievadiTekstu("telefona numuru", "\\d{8,15}", "Tikai cipari!");
            adrese = ievadiTekstu("adresi", ".+", "Adrese nevar būt tukša!");
        }

        saglabaFaila(vards, talrunis, adrese, pasutijumi, piegade, kopCena);

        StringBuilder sb = new StringBuilder("Jūsu pasūtījums:\n");
        pasutijumi.forEach(p -> sb.append("• ").append(p).append("\n"));
        sb.append("----------------\nKOPĀ: ").append(kopCena).append("€");

        JOptionPane.showMessageDialog(null, sb.toString(), "Pasūtījums veiksmīgs!", JOptionPane.INFORMATION_MESSAGE);
    }

    private static int ievadiSkaitli(String jautajums) {
        while (true) {
            try {
                String ievade = JOptionPane.showInputDialog(jautajums);
                return Integer.parseInt(ievade);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ievadiet skaitli!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static String ievadiTekstu(String lauks, String regex, String kluda) {
        while (true) {
            String ievade = JOptionPane.showInputDialog("Ievadiet savu " + lauks + ":");
            if (ievade != null && ievade.matches(regex)) return ievade;
            JOptionPane.showMessageDialog(null, kluda, "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void saglabaFaila(String vards, String tel, String adr, List<String> pasutijumi, boolean piegade, double cena) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pasutijumi.txt", true))) {
            for (String p : pasutijumi) {
                bw.write(vards + "," + tel + "," + adr + "," + p + "," + piegade + "," + cena + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kļūda saglabājot failā!", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
