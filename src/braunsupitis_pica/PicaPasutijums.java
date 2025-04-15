package braunsupitis_pica;

import javax.swing.*;

public class PicaPasutijums {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Sveicināti mūsu picērijā!", "Picērija", JOptionPane.INFORMATION_MESSAGE);
        
        String vards = ievadiTekstu("vārdu", "[a-zA-Z]+", "Tikai burti!");
    }
    private static String ievadiTekstu(String lauks, String regex, String kluda) {
        while (true) {
            String ievade = JOptionPane.showInputDialog("Ievadiet savu " + lauks + ":");
            if (ievade != null && ievade.matches(regex)) return ievade;
            JOptionPane.showMessageDialog(null, kluda, "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
