package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import logika.UpravljanjePodacima;

/**
 * Klasa koja upravlja prikazom ekrana igre 2048 i reaguje na korisničke događaje.
 */
public class UpravljanjeEkranom {
    // Postavljanje vrijednosti za margine, širinu i visinu ekrana.
    public int margina = 2;
    public int sirinaEkrana = 400 + 3 * margina;
    public int visinaEkrana = 400 + 3 * margina;
    CrtanjeEkrana crtanjeEkrana;
    UpravljanjePodacima upravljanjePodacima;

    /**
     * Konstruktor klase.
     *
     * @param crtanjeEkrana      Objekat klase CrtanjeEkrana za iscrtavanje ekrana.
     * @param upravljanjePodacima Objekat klase UpravljanjePodacima za manipulaciju podacima igre.
     * @param okvir              Glavni okvir igre.
     */
    public UpravljanjeEkranom(CrtanjeEkrana crtanjeEkrana, UpravljanjePodacima upravljanjePodacima, JFrame okvir) {
        // Postavljanje referenci na objekte i stvaranje novih instanci.
        this.crtanjeEkrana = crtanjeEkrana;
        this.crtanjeEkrana = new CrtanjeEkrana();
        this.upravljanjePodacima = upravljanjePodacima;
        this.upravljanjePodacima = new UpravljanjePodacima();
        // Pozivanje metode za iscrtavanje brojeva na ekranu.
        this.crtanjeEkrana.crtajBrojeve(okvir, sirinaEkrana, sirinaEkrana, margina);
    }

    /**
     * Metoda za postavljanje osnovnih svojstava okvira.
     *
     * @param okvir Glavni okvir igre.
     */
    public void postavi(JFrame okvir) {
        okvir.setTitle("2048 Game - Armin Vejzović - 303/IT-21");
        okvir.setSize(sirinaEkrana + 16 + 2 * margina, visinaEkrana + 38 + 2 * margina);
        okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Omogućavanje fokusa na okvir.
        okvir.setFocusable(true);
        // Postavljanje pozadinske boje na crnu.
        okvir.getContentPane().setBackground(Igra2048JavaGui.hexUrgb("#000000"));
        // Dodavanje KeyListener-a za reagovanje na pritiske tastature.
        okvir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // Ignorisanje keyTyped događaja.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Ignorisanje keyPressed događaja.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Reagovanje na otpuštanje tastera tastature.
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    upravljanjePodacima.pomjeriLijevo();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    upravljanjePodacima.pomjeriDesno();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    upravljanjePodacima.pomjeriGore();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    upravljanjePodacima.pomjeriDole();
                }

                // Ažuriranje ekrana nakon promjene.
                CrtanjeEkrana.azuriraj();
            }
        });
    }
}
