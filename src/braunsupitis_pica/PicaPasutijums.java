package braunsupitis_pica;

import javax.swing.*;

public class PicaPasutijums {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Sveicināti mūsu picērijā!", "Picērija", JOptionPane.INFORMATION_MESSAGE);
        
        String vards = ievadiTekstu("vārdu", "[a-zA-Z]+", "Tikai burti!");

        String[] picuVeidi = {"Siera pica", "Peperoni pica", "Studentu pica"};
        int veids = JOptionPane.showOptionDialog(null, "Izvēlieties picu:", "Pasūtījums",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, picuVeidi, picuVeidi[0]);

        String[] izmeri = {"Maza (25cm)", "Vidēja (30cm)", "Liela (35cm)"};
        String izmers = (String) JOptionPane.showInputDialog(null, "Izvēlieties izmēru:", "Izmērs",
                JOptionPane.QUESTION_MESSAGE, null, izmeri, izmeri[0]);

        int daudzums = ievadiSkaitli("Cik šādu picu vēlaties?");

        double cena = 0;
        switch (veids) {
            case 0: // Siera pica
                cena = 8;
                break;
            case 1: // Peperoni pica
                cena = 11;
                break;
            case 2: // Studentu pica
                cena = 7;
                break;
        }

        if (izmers.contains("Vidēja")) {
            cena += 2;
        } else if (izmers.contains("Liela")) {
            cena += 4;
        }

        double daudzumaCena = cena * daudzums;
        JOptionPane.showMessageDialog(null, "Cena par " + daudzums + " picām: " + daudzumaCena + "€", "Pasūtījums", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String ievadiTekstu(String lauks, String regex, String kluda) {
        while (true) {
            String ievade = JOptionPane.showInputDialog("Ievadiet savu " + lauks + ":");
            if (ievade != null && ievade.matches(regex)) return ievade;
            JOptionPane.showMessageDialog(null, kluda, "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
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
}
