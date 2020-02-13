package spielbrett;

import algs.AlphaBetaPruning;
import feld.Feld;
import spieler.*;

import java.io.*;
import java.util.Random;

public class SpielbrettController {
    /**
     *
     */
    private Spielbrett spielbrett;

    /**
     *
     */
    private BufferedWriter writer;

    /**
     *
     */
    private MenschController mensch;
    private ComputerController computer;

    private int anzahlZuege;

    /**
     * True == Mensch
     * False == Computer
     */
    private boolean menschAmZug;

    public SpielbrettController() {
        this.spielbrett = new Spielbrett();
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));

        this.mensch = new MenschController();
        this.computer = new ComputerController();

        this.menschAmZug = this.anfaengerWaehlen();

        this.anzahlZuege = 0;
    }


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
                            this.anzahlZuege++;
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

            /*
            zug = this.computer.naechstesFeld(this.spielbrett);

            while(!this.verifiziereZug(zug)){
                zug = this.computer.naechstesFeld(this.spielbrett);
            }

            this.zugSetzen(zug, computer.getSign());*/

            zug = AlphaBetaPruning.run(this, 'O', Double.POSITIVE_INFINITY);

            this.zugSetzen(zug, computer.getSign());
            this.anzahlZuege++;
        }

        if(!this.pruefeGewinner()){

            if(menschAmZug){
                menschAmZug = false;
            }else{
                menschAmZug = true;
            }

            return false;
        }

        return true;
    }

    public void zugSetzen(Feld zug, char sign) {
        this.spielbrett.setSign(zug.getSpalte(), zug.getZeile(), sign);
    }

    public boolean verifiziereZug(Feld zug) {

        /**
         * Pruefe ob Feld bereits belegt
         *
         */

        if (this.spielbrett.getFeld(zug.getSpalte(), zug.getZeile()).getSign() != ' ') {
            return false;
        }

        return true;
    }

    /**
     * Prueft ob der Spieler, der zur Zeit am Zug ist gewonnen hat
     *
     * @return true falls gewonnen
     */
    public boolean pruefeGewinner() {

        /**
         * Es kann noch keinen Gewinner geben
         */
        if(anzahlZuege < 5){
            return false;
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
                return true;
            }

            //Spalte
            if (spielbrett.getFeld(0, c).getSign() == signAmZug
                    && spielbrett.getFeld(1, c).getSign() == signAmZug
                    && spielbrett.getFeld(2, c).getSign() == signAmZug) {
                this.spielZuende(signAmZug);
                return true;
            }
        }

        /**
         * Diagonal 1
         */
        if (spielbrett.getFeld(0, 0).getSign() == signAmZug
                && spielbrett.getFeld(1, 1).getSign() == signAmZug
                && spielbrett.getFeld(2, 2).getSign() == signAmZug) {
            this.spielZuende(signAmZug);
            return true;
        }

        /**
         * Diagonal 2
         */
        if (spielbrett.getFeld(0, 2).getSign() == signAmZug
                && spielbrett.getFeld(1, 1).getSign() == signAmZug
                && spielbrett.getFeld(2, 0).getSign() == signAmZug) {
            this.spielZuende(signAmZug);
            return true;
        }

        /**
         * Unentschieden
         */
        if (this.anzahlZuege == 9) {
            this.spielZuende('z');
            return true;
        }

        return false;
    }

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
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wuerfelt zufaellig aus, wer beginnt.
     *
     * @return True = Mensch faengt an
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
    public Spielbrett getSpielbrett(){
        return this.spielbrett;
    }

    public char getSpielerAmZug(){
        if(menschAmZug){
            return 'X';
        }else{
            return 'O';
        }
    }
    public void setSpielbrett(Spielbrett spielbrett){
        this.spielbrett = spielbrett;
    }

}
