package gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logika.Podaci;
import logika.UpravljanjePodacima;

/**
 * Glavna klasa koja predstavlja GUI za igru 2048.
 */
public class Igra2048JavaGui {
	// Glavni prozor igre
    JFrame igra; 
    // Objekt za upravljanje ekranom
    UpravljanjeEkranom upravljanjeEkranom; 
    // Objekt za crtanje ekrana
    CrtanjeEkrana crtanjeEkrana; 
    // Objekt za upravljanje podacima igre
    UpravljanjePodacima upravljanjePodacima; 

    /**
     * Konstruktor koji inicijalizuje igru i postavlja osnovne komponente.
     */
    public Igra2048JavaGui() {
        inicijalizacija();
        postavi();
    }

    /**
     * Metoda za inicijalizaciju komponenti igre.
     */
    private void inicijalizacija() {
        igra = new JFrame();
        // Kreiranje objekta za upravljanje ekranom
        upravljanjeEkranom = new UpravljanjeEkranom(crtanjeEkrana, upravljanjePodacima, igra);
    }

    /**
     * Metoda za postavljanje osnovnih svojstava igre.
     */
    private void postavi() {
    	// Postavljanje ekranom za trenutni JFrame prozor
        upravljanjeEkranom.postavi(igra); 

        // Dodajte slušač za zatvaranje prozora
        igra.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Pozovite pohranu stanja igre pri zatvaranju prozora
                Podaci.getPodaci().sacuvajStanjeIgre();
                // ili druga logika zatvaranja prozora
                System.exit(0); 
            }
        });
    }

    /**
     * Metoda za pokretanje igre.
     */
    public void pokreniIgru() {
        igra.setVisible(true);
    }

    /**
     * Metoda koja predstavlja glavnu funkciju za pokretanje igre.
     *
     * @param args Argumenti naredbenog reda (ne koriste se).
     */
    public static void main(String[] args) {
        System.out.println("Igrica 2048 - Ovo je main funkcija za GUI!");

        // Pokušaj učitati prethodno stanje igre
        Podaci prethodnoStanje = Podaci.ucitajStanjeIgre();

        // Ako postoji prethodno stanje igre, pitaj korisnika želi li nastaviti
        if (prethodnoStanje != null && !prethodnoStanje.isIgraZavrsena()) {
            int odgovor = JOptionPane.showConfirmDialog(null, "Želite li nastaviti prethodnu igru?", "Nastavi igru", JOptionPane.YES_NO_OPTION);
            if (odgovor == JOptionPane.YES_OPTION) {
                Podaci.setPodaci(prethodnoStanje);
            }
        }

        Igra2048JavaGui i = new Igra2048JavaGui();
        i.pokreniIgru();
    }

    /**
     * Metoda koja konvertuje heksadecimalni zapis boje u objekt klase Color.
     *
     * @param bojaStr Heksadecimalni zapis boje (#RRGGBB).
     * @return Objekat klase Color koji predstavlja boju.
     */
    public static Color hexUrgb(String bojaStr) {
    	// Integer.valueOf konvertuje heksadecimalne podstringove u decimalne vrijednosti.
        // Svaki podstring označava jednu od komponenti boje (crvenu, zelenu ili plavu).
        // Na kraju se kreira i vraća objekat klase Color sa specificiranim bojama.
        return new Color(
                Integer.valueOf(bojaStr.substring(1, 3), 16),  // Crvena komponent
                Integer.valueOf(bojaStr.substring(3, 5), 16),  // Zelena komponent
                Integer.valueOf(bojaStr.substring(5, 7), 16));  // Plava komponent
    }
}
