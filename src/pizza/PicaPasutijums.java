package pizza;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class PicaPasutijums {
    public static void main(String[] args) {
        ArrayList<Pica> pasutijumi = new ArrayList<>();

        JOptionPane.showMessageDialog(null, "Sveicināti mūsu picērijā!", "Picērija", JOptionPane.INFORMATION_MESSAGE);
        String vards = ievadiTekstu("vārdu", "[a-zA-Z]+", "Tikai burti!");
        String talrunis = "", adrese = "";
        boolean piegade = false;

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
            talrunis = ievadiTekstu("telefona numuru", "\\d{8,15}", "Tikai cipari!");
            adrese = ievadiTekstu("adresi", ".+", "Adrese nevar būt tukša!");
        }

        while (true) {
            int izvele = JOptionPane.showOptionDialog(null,
                    "Izvēlieties pasūtījuma veidu:",
                    "Izvēle",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Gatava pica", "Izveidot savu"},
                    "Gatava pica");

            String izmers = "Vidēja";
            List<String> piedevas = new ArrayList<>();
            String merce = "Tomātu";

            if (izvele == 0) {
                String[] gatavas = {"Siera pica", "Peperoni pica", "Sēņu pica"};
                String gatava = (String) JOptionPane.showInputDialog(null, "Izvēlieties gatavu picu:", "Gatavās picas",
                        JOptionPane.QUESTION_MESSAGE, null, gatavas, gatavas[0]);

                switch (gatava) {
                    case "Siera pica" -> piedevas = List.of("Siers");
                    case "Peperoni pica" -> piedevas = List.of("Siers", "Peperoni desiņas");
                    case "Sēņu pica" -> piedevas = List.of("Siers", "Sēnes");
                }

            } else {
                String[] izmeri = {"Maza", "Vidēja", "Liela"};
                izmers = (String) JOptionPane.showInputDialog(null, "Izvēlieties izmēru:", "Izmērs",
                        JOptionPane.QUESTION_MESSAGE, null, izmeri, izmeri[0]);
                if (izmers == null) break;

                JCheckBox siers = new JCheckBox("Siers");
                JCheckBox sēnes = new JCheckBox("Sēnes");
                JCheckBox paprika = new JCheckBox("Paprika");
                JCheckBox olīvas = new JCheckBox("Olīvas");
                JCheckBox liellopu = new JCheckBox("Liellopu gaļa");
                JCheckBox cukgala = new JCheckBox("Cūkgaļa");
                JCheckBox peperoni = new JCheckBox("Peperoni desiņas");

                Object[] piedevuIevade = {siers, sēnes, paprika, olīvas, liellopu, cukgala, peperoni};
                JOptionPane.showConfirmDialog(null, piedevuIevade, "Izvēlieties piedevas", JOptionPane.OK_CANCEL_OPTION);

                if (siers.isSelected()) piedevas.add("Siers");
                if (sēnes.isSelected()) piedevas.add("Sēnes");
                if (paprika.isSelected()) piedevas.add("Paprika");
                if (olīvas.isSelected()) piedevas.add("Olīvas");
                if (liellopu.isSelected()) piedevas.add("Liellopu gaļa");
                if (cukgala.isSelected()) piedevas.add("Cūkgaļa");
                if (peperoni.isSelected()) piedevas.add("Peperoni desiņas");

                String[] merces = {"Tomātu", "BBQ", "Baltā", "Bez mērces"};
                merce = (String) JOptionPane.showInputDialog(null, "Izvēlieties mērci:", "Mērce",
                        JOptionPane.QUESTION_MESSAGE, null, merces, merces[0]);
                if (merce == null) merce = "Bez mērces";
            }

            int daudzums = ievadiSkaitli("Cik šādu picu vēlaties?");
            for (int i = 0; i < daudzums; i++) {
                pasutijumi.add(new Pica(vards, talrunis, adrese, izmers, piedevas, merce, piegade));
            }

            int vel = JOptionPane.showConfirmDialog(null, "Vai vēlaties pievienot vēl picu?", "Turpināt?", JOptionPane.YES_NO_OPTION);
            if (vel != JOptionPane.YES_OPTION) break;
        }

        saglabaFaila(pasutijumi);

        double kopa = pasutijumi.stream().mapToDouble(Pica::getCena).sum();

        Map<String, Integer> picasSkaits = new LinkedHashMap<>();
        Map<String, Double> picasCenas = new HashMap<>();

        for (Pica p : pasutijumi) {
            String apraksts = p.toReadableString();
            picasSkaits.put(apraksts, picasSkaits.getOrDefault(apraksts, 0) + 1);
            picasCenas.put(apraksts, p.getCena());
        }

        StringBuilder sb = new StringBuilder("Jūsu pasūtījums:\n");
        for (Map.Entry<String, Integer> entry : picasSkaits.entrySet()) {
            String pica = entry.getKey();
            int skaits = entry.getValue();
            double cena = picasCenas.get(pica) * skaits;

            sb.append("• ").append(pica).append(" x").append(skaits).append(" — ").append(cena).append("€\n");
        }

        sb.append("----------------\nKOPĀ: ").append(kopa).append("€");

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

    private static void saglabaFaila(List<Pica> pasutijumi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pasutijumi.txt", true))) {
            for (Pica p : pasutijumi) {
                bw.write(p.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kļūda saglabājot failā!", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
