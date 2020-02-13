package algs;

import feld.Feld;
import spielbrett.Spielbrett;

public class Minimax {


    private Minimax() {
    }

    /**
     * Generiere den naechsten, besten Zug
     *
     * @param spielbrett Das momentante Spielbrett
     * @return Bestes Feld
     */
    public static Feld getMinimaxZug(Spielbrett spielbrett) {
        double bestScore = Double.NEGATIVE_INFINITY;
        Feld zug = new Feld();

        for (int spalte = 0; spalte < 3; spalte++) {
            for (int zeile = 0; zeile < 3; zeile++) {
                if (spielbrett.getFeld(spalte, zeile).getSign() == ' ') {
                    spielbrett.setSign(spalte, zeile, 'O');
                    double score = minimax(spielbrett, 0, false);
                    spielbrett.setSign(spalte, zeile, ' ');
                    if (score > bestScore) {
                        bestScore = score;
                        zug = new Feld(spalte, zeile);
                    }
                }
            }
        }

        return zug;
    }

    /**
     * Simuliere Spielzug
     *
     * @param spielbrett
     * @param depth
     * @param isMaximizing
     * @return
     */
    private static double minimax(Spielbrett spielbrett, int depth, boolean isMaximizing) {

        Integer s = score(spielbrett, isMaximizing);

        //Wenn score nicht null, bewerte Zug
        if (s != null) {
            if (s == 10) {
                return s - depth;
            } else if (s == -10) {
                return s + depth;
            } else if (s == 0) {
                return 0;
            }
        }

        /**
         * TODO
         * Score methode immer nur, wenn Spielfeld "zuende".
         * Muss bestimmt nach jedem Zug, auch wenn noch freie Felder (?)
         * Muesste dann aber sicher am Ende der Methode aufgerufen werden,
         * weil es sonst nie zu der unteren if-Bedingung kommt
         *
         * Eventuell: falls s nicht null, zu bestScore zaehlen?
         */

        //Wenn score null, simuliere weitere Zuege
        if (isMaximizing) {
            double bestScore = Double.NEGATIVE_INFINITY;
            for (int spalte = 0; spalte < 3; spalte++) {
                for (int zeile = 0; zeile < 3; zeile++) {
                    if (spielbrett.getFeld(spalte, zeile).getSign() == ' ') {
                        spielbrett.setSign(spalte, zeile, 'O');
                        double score = minimax(spielbrett, (depth + 1), false);
                        spielbrett.setSign(spalte, zeile, ' ');
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            double bestScore = Double.POSITIVE_INFINITY;
            for (int spalte = 0; spalte < 3; spalte++) {
                for (int zeile = 0; zeile < 3; zeile++) {
                    if (spielbrett.getFeld(spalte, zeile).getSign() == ' ') {
                        spielbrett.setSign(spalte, zeile, 'X');
                        double score = minimax(spielbrett, (depth + 1), true);
                        spielbrett.setSign(spalte, zeile, ' ');
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    /**
     * Bewertet das Spielbrett mit dem naechsten Spielzug
     *
     * @param spielbrett Spielbrett inkl. naechstem Zug
     * @param computer   Computer am Zug
     * @return 0: mit diesem Zug endet das Spiel mit einem Unentschieden
     */
    private static Integer score(Spielbrett spielbrett, boolean computer) {
        int rueckgabe;
        char signAmZug;

        if (computer) {
            rueckgabe = 10;
            signAmZug = 'O';
        } else {
            rueckgabe = -10;
            signAmZug = 'X';
        }


        for (int c = 0; c < spielbrett.getSpielfeld().length; c++) {

            //Zeile
            if (spielbrett.getFeld(c, 0).getSign() == signAmZug
                    && spielbrett.getFeld(c, 1).getSign() == signAmZug
                    && spielbrett.getFeld(c, 2).getSign() == signAmZug) {
                return rueckgabe;
            }

            //Spalte
            if (spielbrett.getFeld(0, c).getSign() == signAmZug
                    && spielbrett.getFeld(1, c).getSign() == signAmZug
                    && spielbrett.getFeld(2, c).getSign() == signAmZug) {
                return rueckgabe;
            }
        }

        /**
         * Diagonal 1
         */
        if (spielbrett.getFeld(0, 0).getSign() == signAmZug
                && spielbrett.getFeld(1, 1).getSign() == signAmZug
                && spielbrett.getFeld(2, 2).getSign() == signAmZug) {
            return rueckgabe;
        }

        /**
         * Diagonal 2
         */
        if (spielbrett.getFeld(0, 2).getSign() == signAmZug
                && spielbrett.getFeld(1, 1).getSign() == signAmZug
                && spielbrett.getFeld(2, 0).getSign() == signAmZug) {
            return rueckgabe;
        }

        /**
         * Mit diesem letzten Zug, endet das Spiel mit einem Unentschieden
         */
        boolean unentschieden = true;
        for (int spalte = 0; spalte < 3; spalte++) {
            for (int zeile = 0; zeile < 3; zeile++) {
                if (spielbrett.getFeld(spalte, zeile).getSign() != ' ') {
                    unentschieden = false;
                }
            }
        }

        if (unentschieden) {
            return 0;
        }

        return null;
    }


    /**
     *
     * TODO:
     * AI blockt gewinnerzuege manchmal bzw. am Ende des Spiels nicht
     * Während des Spiels werden Siegerzuege geblockt
     *
     *Eventuell mit Integer.Max/Min_integer anstatt Double.infinity versuchen
     *
     * eventuell beide zuege gleich schlecht, zuege speichern?
     */
}
