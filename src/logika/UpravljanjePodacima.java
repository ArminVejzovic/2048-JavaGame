package logika;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gui.CrtanjeEkrana;

/**
 * Klasa za upravljanje podacima igre 2048.
 */
public class UpravljanjePodacima {
	
	private int bodovi;
	private boolean pitajNakon2048 = true;
	private static final String DATOTEKA_REZULTATA = "rezultati.txt";
    private static final int MAKS_BROJ_REZULTATA = 10;

    private static ArrayList<Integer> rezultati = new ArrayList<>();
    
 
    static {
        ucitajRezultate();
    }

	
    /**
     * Konstruktor klase UpravljanjePodacima.
     */
    public UpravljanjePodacima() {
    	this.bodovi = 0;
    	//Podaci.getPodaci().matrica[0][0] = 1024;
    	//Podaci.getPodaci().matrica[0][1] = 1024;
        generisiBrojeve();
    }
    
    /**
     * Metoda za dobivanje trenutnog broja bodova.
     *
     * @return Trenutni broj bodova.
     */
    public int getBodovi() {
    	return this.bodovi;
    }
    
    /**
     * Metoda za postavljanje broja bodova.
     *
     * @param b Broj bodova koji treba dodati.
     */
    public void setBodovi(int b) {
    	this.bodovi += b;
    }

    /**
     * Metoda za generiranje novih brojeva u matrici igre.
     */
    public void generisiBrojeve() {
    	Random r = new Random();
        int praznoPolje = 0;

        // Prvo prebroj prazna polja
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] == 0) {
                    praznoPolje++;
                }
            }
        }

        // Generiši novi broj
        if (praznoPolje > 0) {
            int indeks = r.nextInt(praznoPolje); // Nasumično izaberi jedno prazno polje
            praznoPolje = 0;

            // Postavi novi broj (2 ili 4) na izabrano prazno polje
            outerloop:
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (Podaci.getPodaci().matrica[i][j] == 0) {
                        if (praznoPolje == indeks) {
                            Podaci.getPodaci().matrica[i][j] = (r.nextInt(10) > 8) ? 4 : 2;
                            break outerloop; // Prekini oba for loop-a
                        }
                        praznoPolje++;
                    }
                }
            }
        }
        
    }

    /**
     * Pomera sve elemente u matrici igre prema levo.
     */
    public void pomjeriLijevo() {
        // Pomera sve elemente u matrici prema levo
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, 0, -1);
                }
            }
        }

        // Spaja susedne iste brojeve u istom redu
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (Podaci.getPodaci().matrica[i][j] != 0 &&
                        Podaci.getPodaci().matrica[i][j] == Podaci.getPodaci().matrica[i][j + 1]) {
                    spojiPolje(i, j, 0, 1);
                }
            }
        }

        // Ponovno pomera sve elemente prema levo kako bi popunili praznine nakon spajanja
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, 0, -1);
                }
            }
        }

        // Generiše nove brojeve nakon pomjeranja i spajanja
        generisiBrojeve();

        // Ažurira grafički prikaz ekrana
        CrtanjeEkrana.azuriraj();

        // Proverava završetak igre nakon poteza
        provjeriZavrsetakIgre();
    }

    /**
     * Pomera sve elemente u matrici igre prema desno.
     */
    public void pomjeriDesno() {
        // Pomera sve elemente u matrici prema desno
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, 0, 1);
                }
            }
        }

        // Spaja susedne iste brojeve u istom redu
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (Podaci.getPodaci().matrica[i][j] != 0 &&
                        Podaci.getPodaci().matrica[i][j] == Podaci.getPodaci().matrica[i][j - 1]) {
                    spojiPolje(i, j, 0, -1);
                }
            }
        }

        // Ponovno pomera sve elemente prema desno kako bi popunili praznine nakon spajanja
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, 0, 1);
                }
            }
        }

        // Generiše nove brojeve nakon pomjeranja i spajanja
        generisiBrojeve();

        // Ažurira grafički prikaz ekrana
        CrtanjeEkrana.azuriraj();

        // Proverava završetak igre nakon poteza
        provjeriZavrsetakIgre();
    }

    /**
     * Pomera sve elemente u matrici igre prema gore.
     */
    public void pomjeriGore() {
        // Pomera sve elemente u matrici prema gore
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, -1, 0);
                }
            }
        }

        // Spaja susedne iste brojeve u istoj koloni
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                if (Podaci.getPodaci().matrica[i][j] != 0 &&
                        Podaci.getPodaci().matrica[i][j] == Podaci.getPodaci().matrica[i + 1][j]) {
                    spojiPolje(i, j, 1, 0);
                }
            }
        }

        // Ponovno pomera sve elemente prema gore kako bi popunili praznine nakon spajanja
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, -1, 0);
                }
            }
        }

        // Generiše nove brojeve nakon pomjeranja i spajanja
        generisiBrojeve();

        // Ažurira grafički prikaz ekrana
        CrtanjeEkrana.azuriraj();

        // Proverava završetak igre nakon poteza
        provjeriZavrsetakIgre();
    }

    /**
     * Pomera sve elemente u matrici igre prema dole.
     */
    public void pomjeriDole() {
        // Pomera sve elemente u matrici prema dole
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, 1, 0);
                }
            }
        }

        // Spaja susedne iste brojeve u istoj koloni
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i > 0; i--) {
                if (Podaci.getPodaci().matrica[i][j] != 0 &&
                        Podaci.getPodaci().matrica[i][j] == Podaci.getPodaci().matrica[i - 1][j]) {
                    spojiPolje(i, j, -1, 0);
                }
            }
        }

        // Ponovno pomera sve elemente prema dole kako bi popunili praznine nakon spajanja
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] != 0) {
                    pomakniPolje(i, j, 1, 0);
                }
            }
        }

        // Generiše nove brojeve nakon pomjeranja i spajanja
        generisiBrojeve();

        // Ažurira grafički prikaz ekrana
        CrtanjeEkrana.azuriraj();

        // Proverava završetak igre nakon poteza
        provjeriZavrsetakIgre();
    }

    /**
     * Pomera polje na novu poziciju.
     *
     * @param i  Trenutni red polja.
     * @param j  Trenutna kolona polja.
     * @param it Pomak po vertikali (redovi).
     * @param jt Pomak po horizontali (kolone).
     */
    private void pomakniPolje(int i, int j, int it, int jt) {
        // Provjerava granice matrice kako bi spriječio prelazak izvan nje
        if (i + it < 0 || i + it >= 4 || j + jt < 0 || j + jt >= 4) {
            return;
        }

        // Računa novu poziciju polja
        int x = i + it;
        int y = j + jt;

        // Provjerava da li je novo polje prazno
        if (Podaci.getPodaci().matrica[x][y] != 0) {
            return;
        }

        // Prebacuje broj s trenutne pozicije na novu poziciju
        int broj = Podaci.getPodaci().matrica[i][j];
        Podaci.getPodaci().matrica[x][y] = broj;
        Podaci.getPodaci().matrica[i][j] = 0;

        // Ponovno poziva funkciju za pomjeranje kako bi rekuzivno pomjerio ostala polja
        pomakniPolje(x, y, it, jt);
    }

    /**
     * Spaja polje na novoj poziciji ako je moguće.
     *
     * @param i  Trenutni red polja.
     * @param j  Trenutna kolona polja.
     * @param it Pomak po vertikali (redovi).
     * @param jt Pomak po horizontali (kolone).
     */
    private void spojiPolje(int i, int j, int it, int jt) {
        // Provjerava granice matrice kako bi spriječio prelazak izvan nje
        if (i + it < 0 || i + it >= 4 || j + jt < 0 || j + jt >= 4) {
            return;
        }

        // Računa novu poziciju polja
        int x = i + it;
        int y = j + jt;

        // Provjerava da li je polje na novoj poziciji jednako polju na trenutnoj poziciji
        if (Podaci.getPodaci().matrica[x][y] != Podaci.getPodaci().matrica[i][j]) {
            return;
        }

        // Računa bodove na osnovu spojenih polja i ažurira ukupan broj bodova
        int bodovi = Podaci.getPodaci().matrica[i][j] * 2;
        setBodovi(bodovi);

        // Množi vrijednost trenutnog polja s 2 i postavlja rezultat na trenutnu poziciju
        int broj = Podaci.getPodaci().matrica[i][j] * 2;
        Podaci.getPodaci().matrica[x][y] = 0;
        Podaci.getPodaci().matrica[i][j] = broj;

        // Ponovno poziva funkciju za pomjeranje kako bi rekuzivno pomjerio ostala polja
        pomakniPolje(x, y, it, jt);
    }


    
    
    /**
     * Metoda za provjeru završetka igre.
     */
    public void provjeriZavrsetakIgre() {
        // Provjerava postizanje pločice s brojem 2048
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] == 2048) {
                    // Ako je postignut 2048, pita korisnika hoće li nastaviti igru
                    if (this.pitajNakon2048) {
                        boolean nastaviIgru = nastaviNakon2048();
                        if (!nastaviIgru) {
                            zavrsiIgru();
                            return;
                        } else {
                            this.pitajNakon2048 = false; // Postavlja da se više ne postavlja pitanje
                        }
                    }
                }
            }
        }

        // Provjerava mogućnost daljnjih poteza
        if (!imaMogucihPoteza()) {
            // Ako nema mogućih poteza, prikazuje dijalog s opcijom ponovnog igranja
            SwingUtilities.invokeLater(() -> {
                int odgovor = JOptionPane.showConfirmDialog(null, "Bodovi: " + getBodovi() + "\n - Nema više mogućih poteza. Želite li ponovo igrati?", "Kraj igre", JOptionPane.YES_NO_OPTION);
                if (odgovor == JOptionPane.YES_OPTION) {
                    UpravljanjePodacima.dodajRezultat(bodovi);
                    restartIgre();
                    CrtanjeEkrana.azuriraj();
                } else {
                    zavrsiIgru();
                }
            });
        }
    }

    /**
     * Metoda za provjeru postojanja mogućih poteza u igri.
     *
     * @return True ako postoji barem jedan mogući potez, inače False.
     */
    public boolean imaMogucihPoteza() {
        // Provjerava mogućnost pomaka ili praznih mjesta u bilo kojem smjeru
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int trenutniBroj = Podaci.getPodaci().matrica[i][j];

                // Provjerava poteze prema lijevo, desno, gore i dolje
                if ((j > 0 && trenutniBroj == Podaci.getPodaci().matrica[i][j - 1]) ||
                    (j < 3 && trenutniBroj == Podaci.getPodaci().matrica[i][j + 1]) ||
                    (i > 0 && trenutniBroj == Podaci.getPodaci().matrica[i - 1][j]) ||
                    (i < 3 && trenutniBroj == Podaci.getPodaci().matrica[i + 1][j])) {
                    return true; // Pronađen je barem jedan mogući potez
                }

                // Provjerava prazna mjesta (0) u matrici
                if (trenutniBroj == 0) {
                    return true; // Pronađeno je prazno mjesto
                }
            }
        }
        return false; // Nema mogućih poteza ili praznih mjesta
    }


    /**
     * Metoda za resetiranje igre na početne vrijednosti, uključujući matricu i bodove.
     */
    private void restartIgre() {
        // Resetuje matricu i bodove na početne vrijednosti
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Podaci.getPodaci().matrica[i][j] = 0;
            }
        }
        this.bodovi = 0;
        generisiBrojeve();
    }

    /**
     * Metoda za provjeru postizanja pločice s brojem 2048 i opciju nastavka igre.
     *
     * @return True ako igra treba nastaviti, inače False.
     */
    private boolean nastaviNakon2048() {
        // Prikazuje dijalog s opcijom nastavka igre
        int odgovor = JOptionPane.showConfirmDialog(null, "Želite li nastaviti igru i nakon postizanja 2048?", "Nastavi igru", JOptionPane.YES_NO_OPTION);
        return odgovor == JOptionPane.YES_OPTION;
    }

    /**
     * Metoda za završetak igre, prikaz rezultata i eventualno spremanje u datoteku.
     */
    private void zavrsiIgru() {
    	
        int bodovi = getBodovi();
        String poruka = "Igra je završena. Osvojili ste " + bodovi + " bodova. Hvala što ste igrali!";
        JOptionPane.showMessageDialog(null, poruka, "Kraj igre", JOptionPane.INFORMATION_MESSAGE);
        
        // Dodavanje rezultata u datoteku
        UpravljanjePodacima.dodajRezultat(bodovi);

        Podaci.getPodaci().zavrsiIgru();
        this.bodovi = 0;
        System.exit(0); // Zatvaranje aplikacije
    }

    
    /**
     * Metoda za učitavanje rezultata iz datoteke.
     */
    private static void ucitajRezultate() {
        try (BufferedReader br = new BufferedReader(new FileReader(DATOTEKA_REZULTATA))) {
            String linija;
            while ((linija = br.readLine()) != null) {
                // Parsiranje linije i dodavanje rezultata u listu
                int rezultat = Integer.parseInt(linija.trim());
                rezultati.add(rezultat);
            }
            // Sortiranje rezultata u opadajućem redoslijedu
            Collections.sort(rezultati, Collections.reverseOrder());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda za dodavanje rezultata u listu rezultata.
     *
     * @param noviRezultat Novi rezultat koji se dodaje.
     */
    public static void dodajRezultat(int noviRezultat) {
        // Provjerava je li rezultat već prisutan prije dodavanja
        if (!rezultati.contains(noviRezultat)) {
            rezultati.add(noviRezultat);
            // Sortiranje rezultata u opadajućem redoslijedu
            Collections.sort(rezultati, Collections.reverseOrder());
            // Ograničavanje na maksimalan broj rezultata
            if (rezultati.size() > MAKS_BROJ_REZULTATA) {
                rezultati.remove(MAKS_BROJ_REZULTATA);
            }
            // Spremanje rezultata u datoteku
            sacuvajRezultate();
        }
    }

    /**
     * Metoda za spremanje rezultata u datoteku.
     */
    public static void sacuvajRezultate() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATOTEKA_REZULTATA))) {
            // Pisanje rezultata u datoteku
            for (int rezultat : rezultati) {
                bw.write(Integer.toString(rezultat));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    // Konzola
    
    
    /**
     * Metoda za provjeru završetka igre u konzoli.
     *
     * @return True ako igra treba završiti, inače False.
     */
    public boolean provjeriKrajIgre() {
        if (!imaMogucihPoteza()) {
            return true;
        }
        return false;
    }
    
    /**
     * Metoda za provjeru postizanja pločice s brojem 2048.
     *
     * @return True ako je pločica s brojem 2048 postignuta, inače False.
     */
    public boolean isPostignut2048() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Podaci.getPodaci().matrica[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

  
}