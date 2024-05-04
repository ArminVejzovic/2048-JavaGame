package konzola;

import java.util.Scanner;

import logika.Podaci;
import logika.UpravljanjePodacima;

public class Igra2048Konzola {
    private UpravljanjePodacima upravljanjePodacima;
    private boolean pitajNakon2048 = true;

    public Igra2048Konzola() {
        this.upravljanjePodacima = new UpravljanjePodacima();
    }

    public void setPitajNakon2048(boolean vrijednost) {
        this.pitajNakon2048 = vrijednost;
    }
    
    /**
     * Metoda za prikaz trenutnog stanja igre u konzoli.
     */
    public void prikaziStanje() {
        System.out.println("Trenutno stanje igre:");

        int[][] matrica = Podaci.getPodaci().getMatrica();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrica[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void pokreniIgru() {
        System.out.println("Igrica 2048 - Ovo je main funkcija za Konzolu!");

        Scanner scanner = new Scanner(System.in);
        boolean kraj = false;

        while (!kraj) {
            prikaziStanje();
            System.out.println("Unesite potez (A - lijevo, D - desno, W - gore, S - dole, B - bodovi, Q - izlaz): ");
            String unos = scanner.nextLine().toUpperCase();

            switch (unos) {
                case "A":
                    upravljanjePodacima.pomjeriLijevo();
                    break;
                case "D":
                    upravljanjePodacima.pomjeriDesno();
                    break;
                case "W":
                    upravljanjePodacima.pomjeriGore();
                    break;
                case "S":
                    upravljanjePodacima.pomjeriDole();
                    break;
                case "Q":
                    kraj = true;
                    break;
                case "B":
                    System.out.println("Broj bodova: " + upravljanjePodacima.getBodovi());
                    break;
                default:
                    System.out.println("Nepoznat potez. Molimo pokušajte ponovo.");
                    break;
            }

            // Dodaj provere za kraj igre i 2048 pobedu
            if (upravljanjePodacima.provjeriKrajIgre()) {
                System.out.println("Igra završena. Bodovi: " + upravljanjePodacima.getBodovi());
                // Dodaj poziv za čuvanje rezultata
                sacuvajRezultate(upravljanjePodacima.getBodovi());
                kraj = true;
            } else if (upravljanjePodacima.isPostignut2048() && this.pitajNakon2048 == true) {
                setPitajNakon2048(false);
                System.out.println("Čestitamo! Postigli ste pločicu s brojem 2048.");

                System.out.println("Želite li nastaviti igru? (Y/N): ");
                String odgovor = scanner.nextLine().toUpperCase();
                if (odgovor.equals("N")) {
                    // Dodaj poziv za čuvanje rezultata
                    sacuvajRezultate(upravljanjePodacima.getBodovi());
                    System.out.println("Igra završena. Bodovi: " + upravljanjePodacima.getBodovi());
                    kraj = true;
                } else if (odgovor.equals("Y")) {
                    // Nastavi igru
                } else {
                    System.out.println("Nepoznat odgovor. Igra će se nastaviti.");
                }
            }
        }

        System.out.println("Hvala što ste igrali!");
    }

    private void sacuvajRezultate(int bodovi) {
        // Pozovi metodu za dodavanje rezultata u listu rezultata
        upravljanjePodacima.dodajRezultat(bodovi);
        // Pozovi metodu za spremanje rezultata u datoteku
        upravljanjePodacima.sacuvajRezultate();
    }

    public static void main(String[] args) {
        Igra2048Konzola konzola = new Igra2048Konzola();
        konzola.pokreniIgru();
    }
}
