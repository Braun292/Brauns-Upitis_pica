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
    }

    private static String ievadiTekstu(String lauks, String regex, String kluda) {
        while (true) {
            String ievade = JOptionPane.showInputDialog("Ievadiet savu " + lauks + ":");
            if (ievade != null && ievade.matches(regex)) return ievade;
            JOptionPane.showMessageDialog(null, kluda, "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
