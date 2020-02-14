package spielbrett;

import feld.Feld;
import spieler.*;
import java.io.*;
import java.util.Random;

public class SpielbrettController {

    /**
     * Spielbrettmodel
     */
    private Spielbrett spielbrett;

    /**
     * Konsolenwriter
     */
    private BufferedWriter writer;

    /**
     * Controller des Menschen
     */
    private MenschController mensch;

    /**
     * Controller des Computers
     */
    private ComputerController computer;

    /**
     * Anzahl der Zuege
     */
    private int anzahlZuege;

    /**
     * True == Mensch
     * False == Computer
     */
    private boolean menschAmZug;

    /**
     * SpielbrettController-Konstruktor
     */
    public SpielbrettController() {
        this.spielbrett = new Spielbrett();
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));

        this.mensch = new MenschController();
        this.computer = new ComputerController();

        this.menschAmZug = this.anfaengerWaehlen();

        this.anzahlZuege = 0;
    }

    /**
     * Ruft die naechstesFeld()-Methode des jeweiligen Spielers auf.
     *
     * @return False = Spiel ist noch nicht vorbei.
     */
    public boolean naechsterZug() {
        Feld zug = null;

        if (menschAmZug) {

            try {
                writer.write("Gebe deinen naechsten Zug ein (Beispiel: A2)\n=> ");
                writer.flush();
                while (zug == null) {
                    zug = this.mensch.naechstesFeld();
                    if (zug == null) {
                        writer.write("Eingabe falsch. Beispiel: B3.\n=> ");
                        writer.flush();
                    } else {
                        if (this.verifiziereZug(zug)) {
                            this.zugSetzen(zug, mensch.getSign());
                        } else {
                            writer.write("Dieses Feld ist bereits besetzt!\nNeue Eingabe:\n=> ");
                            writer.flush();
                            zug = null;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            zug = computer.naechstesFeld(this);

            //durch Minimax Algorithmus wird diese Variable zeitweise geaendert: wieder Zuruecksetzen
            this.menschAmZug = false;
            this.zugSetzen(zug, computer.getSign());
        }

        char status = this.pruefeGewinner();

        /**
         * n = zu wenig zuege
         * z = unentschieden
         * X = X gewonnen
         * O = O gewonnen
         *
         */

        if (status == 'n') {
            this.naechsterSpieler();
            return false;
        } else {
            this.spielZuende(status);
            return true;
        }
    }

    /**
     * Aendert die Variable, jeweils zum naechsten Spieler
     */
    public void naechsterSpieler() {
        if (menschAmZug) {
            this.menschAmZug = false;
        } else {
            this.menschAmZug = true;
        }
    }

    /**
     * Setzt einen Zug auf das Feld und erhört die Anzahl der Spielzuege
     *
     * @param zug  der Zug
     * @param sign das Zeichen, mit dem das Feld besetzt wird
     */
    public void zugSetzen(Feld zug, char sign) {
        this.spielbrett.setSign(zug.getSpalte(), zug.getZeile(), sign);
        this.anzahlZuege++;
    }

    /**
     * Prueft, ob auf dem Feld ein weiterer Zug moeglich ist
     *
     * @param zug der Zug
     * @return false, wenn Feld bereits belegt.
     */
    public boolean verifiziereZug(Feld zug) {

        if (this.spielbrett.getFeld(zug.getSpalte(), zug.getZeile()).getSign() != ' ') {
            return false;
        }

        return true;
    }

    /**
     * Prueft ob der Spieler, der zur Zeit am Zug ist, gewonnen hat
     *
     * @return true falls gewonnen
     */
    public char pruefeGewinner() {

        /**
         * Es kann noch keinen Gewinner geben
         */
        if (anzahlZuege < 5) {
            return 'n';
        }

        char signAmZug;

        if (menschAmZug) {
            signAmZug = mensch.getSign();
        } else {
            signAmZug = computer.getSign();
        }

        for (int c = 0; c < this.spielbrett.getSpielfeld().length; c++) {

            //Zeile
            if (spielbrett.getFeld(c, 0).getSign() == signAmZug
                    && spielbrett.getFeld(c, 1).getSign() == signAmZug
                    && spielbrett.getFeld(c, 2).getSign() == signAmZug) {
                this.spielZuende(signAmZug);
                return signAmZug;
            }

            //Spalte
            if (spielbrett.getFeld(0, c).getSign() == signAmZug
                    && spielbrett.getFeld(1, c).getSign() == signAmZug
                    && spielbrett.getFeld(2, c).getSign() == signAmZug) {
                this.spielZuende(signAmZug);
                return signAmZug;
            }
        }

        /**
         * Diagonal 1
         */
        if (spielbrett.getFeld(0, 0).getSign() == signAmZug
                && spielbrett.getFeld(1, 1).getSign() == signAmZug
                && spielbrett.getFeld(2, 2).getSign() == signAmZug) {
            this.spielZuende(signAmZug);
            return signAmZug;
        }

        /**
         * Diagonal 2
         */
        if (spielbrett.getFeld(0, 2).getSign() == signAmZug
                && spielbrett.getFeld(1, 1).getSign() == signAmZug
                && spielbrett.getFeld(2, 0).getSign() == signAmZug) {
            this.spielZuende(signAmZug);
            return signAmZug;
        }

        /**
         * Unentschieden
         */
        if (this.anzahlZuege == 9) {
            this.spielZuende('z');
            return 'z';
        }

        return 'n';
    }

    /**
     * Gibt den Gewinner aus und beendet das Spiel.
     *
     * @param gewinner der Gewinner. Falls gewinner == 'z', dann unentschieden.
     */
    public void spielZuende(char gewinner) {
        try {
            if (gewinner != 'z') {
                this.writer.write("========================\n");
                this.writer.write("-> " + gewinner + " hat gewonnen! <-\n");
            } else {
                this.writer.write("Unentschieden!\n");
            }

            this.writer.flush();
            this.spielbrettZeichnen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wuerfelt zufaellig aus, wer beginnt.
     *
     * @return Flase = Computer faengt an
     */
    public boolean anfaengerWaehlen() {
        Random rand = new Random();
        int n = rand.nextInt(2);

        if (n == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Zeichnet das momentante Spielbrett in die Konsole.
     */
    public void spielbrettZeichnen() {
        int buchstabeZeile = 65;

        try {
            writer.write("#######################\n");
            writer.write("     |  1  |  2  |  3\n");
            for (int spalte = 0; spalte < 3; spalte++) {
                writer.write("  --------------------\n");
                writer.write(buchstabeZeile);
                writer.write("  ");

                for (int zeile = 0; zeile < 3; zeile++) {
                    writer.write("  |  " + spielbrett.getFeld(spalte, zeile).getSign());
                }
                buchstabeZeile++;
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gibt das Spielbrett zurueck.
     */
    public Spielbrett getSpielbrett() {
        return this.spielbrett;
    }

    /**
     * Gibt den Spieler zurueck, der zur Zeit am Zug ist.
     */
    public char getSpielerAmZug() {
        if (menschAmZug) {
            return 'X';
        } else {
            return 'O';
        }
    }

    /**
     * Setzt die Anzahl der Spielzuege
     *
     * @param anzahl
     */
    public void setAnzahlZuege(int anzahl) {
        this.anzahlZuege = anzahl;
    }
}
