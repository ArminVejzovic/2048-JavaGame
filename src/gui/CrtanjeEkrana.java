package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logika.Podaci;

/**
 * Klasa za iscrtavanje ekrana igre 2048.
 */
public class CrtanjeEkrana {
    static JLabel tabla[][] = new JLabel[4][4];

    /**
     * Metoda za crtanje brojeva na ekranu.
     *
     * @param okvir Glavni okvir igre.
     * @param W     Širina okvira.
     * @param H     Visina okvira.
     * @param M     Margina.
     */
    public void crtajBrojeve(JFrame okvir, int W, int H, int M) {
        // Postavljanje veličine polja na osnovu dimenzija okvira.
        int w = (W - 3 * M) / 4;
        int h = (H - 3 * M) / 4;
        int x = 0, y = 0;
        // Petlja za postavljanje polja na ekran.
        for (int i = 0; i < 4; i++) {
            y = i * w + (1 + i) * M;
            for (int j = 0; j < 4; j++) {
                x = j * h + (j + 1) * M;
                tabla[i][j] = kreirajPolje(x, y, w, h, i, j);
                okvir.add(tabla[i][j]);
            }
        }
        // Dodavanje prazne JLabel komponente (razmaka).
        okvir.add(new JLabel());
    }

    /**
     * Metoda za kreiranje pojedinačnog polja na ekranu.
     *
     * @param x     X-koordinata polja.
     * @param y     Y-koordinata polja.
     * @param w     Širina polja.
     * @param h     Visina polja.
     * @param i     Redni broj polja.
     * @param j     Kolona polja.
     * @return      JLabel predstavljajući pojedinačno polje.
     */
    private JLabel kreirajPolje(int x, int y, int w, int h, int i, int j) {
    	// Početni tekst (može biti bilo šta jer se odmah zamjenjuje).
        JLabel l = new JLabel("S"); 
        l.setBounds(x, y, w, h);
        // Postavljanje pozadine na osnovu podataka iz matrice.
        postaviPozadinu(l, i, j); 
        l.setOpaque(true);
        return l;
    }

    /**
     * Metoda za postavljanje pozadinske boje i teksta na polju.
     *
     * @param l JLabel koji predstavlja polje.
     * @param i Redni broj polja.
     * @param j Kolona polja.
     */
    private static void postaviPozadinu(JLabel l, int i, int j) {
        if (l == null) {
            // Inicijalizujte JLabel ako je null
            l = new JLabel();
        }
        int d = Podaci.getPodaci().matrica[i][j];
        String boja = "";
        // Odabir boje i postavljanje teksta na osnovu podataka iz matrice.
        switch (d) {
            case 2:
                boja = "9600FF";
                break;
            case 4:
                boja = "F0145A";
                break;
            case 8:
                boja = "FFC91B";
                break;
            case 16:
                boja = "093711";
                break;
            case 32:
                boja = "0095D6";
                break;
            case 64:
                boja = "CE0078";
                break;
            case 128:
                boja = "FF5510";
                break;
            case 256:
                boja = "29D7A5";
                break;
            case 512:
                boja = "73c702";
                break;
            case 1024:
                boja = "FF0024";
                break;
            case 2048:
                boja = "5F069B";
                break;
            case 4096:
                boja = "9C9C9C";
                break;
            case 8192:
                boja = "FF9200";
                break;
            case 16384:
                boja = "0A94AB";
                break;
            case 32768:
                boja = "CC6600";
                break;
            case 65536:
                boja = "660066";
                break;
            default:
                boja = "373737";
                break;
        }
        // Postavljanje teksta, fonta, boje i pozicije polja.
        if (d == 0) {
            l.setText("");
        } else {
            l.setText("" + d);
        }
        l.setFont(new Font("Sefif", Font.BOLD, 26));
        l.setForeground(Color.WHITE);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setVerticalAlignment(SwingConstants.CENTER);
        l.setBackground(Igra2048JavaGui.hexUrgb("#" + boja));
    }

    /**
     * Metoda za ažuriranje prikaza ekrana.
     */
    public static void azuriraj() {
        // Iteracija kroz matricu i postavljanje pozadine na osnovu novih podataka.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                postaviPozadinu(tabla[i][j], i, j);
            }
        }
    }
}
