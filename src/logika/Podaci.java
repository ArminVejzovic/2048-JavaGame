package logika;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Klasa koja predstavlja podatke o stanju igre.
 */
public class Podaci implements Serializable {

    private static Podaci podaci;

    // Inicijalizacija statičke instance podataka o igri.
    static {
        podaci = new Podaci();
    }

    // Varijabla koja označava da li je igra završena.
    private boolean igraZavrsena;
    // Matrica koja predstavlja raspored brojeva na igraćoj tabli.
    public int matrica[][] = new int[4][4];

    /**
     * Metoda za dobijanje instance podataka o igri.
     *
     * @return Instanca podataka o igri.
     */
    public static Podaci getPodaci() {
        return podaci;
    }

    /**
     * Metoda za čuvanje trenutnog stanja igre u datoteci.
     */
    public void sacuvajStanjeIgre() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stanje_igre.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda za učitavanje prethodnog stanja igre iz datoteke.
     *
     * @return Podaci o stanju igre.
     */
    public static Podaci ucitajStanjeIgre() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stanje_igre.ser"))) {
            return (Podaci) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metoda za postavljanje podataka o igri na prethodno stanje.
     *
     * @param prethodnoStanje Prethodno stanje igre.
     */
    public static void setPodaci(Podaci prethodnoStanje) {
        podaci = prethodnoStanje;
    }

    /**
     * Metoda koja provjerava je li igra završena.
     *
     * @return true ako je igra završena, inače false.
     */
    public boolean isIgraZavrsena() {
        return igraZavrsena;
    }

    /**
     * Metoda za postavljanje statusa završetka igre.
     *
     * @param igraZavrsena true ako je igra završena, inače false.
     */
    public void setIgraZavrsena(boolean igraZavrsena) {
        this.igraZavrsena = igraZavrsena;
    }

    /**
     * Metoda koja označava završetak igre i sprema stanje igre.
     */
    public void zavrsiIgru() {
        this.igraZavrsena = true;
        sacuvajStanjeIgre();
    }

    /**
     * Metoda koja označava nastavak igre.
     */
    public void nastaviIgru() {
        this.igraZavrsena = false;
    }

    /**
     * Metoda koja vraća matricu igre.
     *
     * @return Matrica igre.
     */
    public int[][] getMatrica() {
        return matrica;
    }
}
