package algs;

import feld.Feld;
import spielbrett.SpielbrettController;

public class Minimax {


    private Minimax() {
    }

    /**
     * Generiere den naechsten, besten Zug
     *
     * @return Bestes Feld
     */
    public static Feld getMinimaxZug(SpielbrettController spielbrettController) {
        double bestScore = Double.NEGATIVE_INFINITY;
        Feld zug = new Feld();

        for (int spalte = 0; spalte < 3; spalte++) {
            for (int zeile = 0; zeile < 3; zeile++) {
                if (spielbrettController.getSpielbrett().getFeld(spalte, zeile).getSign() == ' ') {
                    spielbrettController.getSpielbrett().setSign(spalte, zeile, 'O');
                    double score = minimax(spielbrettController, 0, false);
                    spielbrettController.getSpielbrett().setSign(spalte, zeile, ' ');
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
     * Finde besten naechsten Zug
     *
     * @param depth
     * @param isMaximizing
     * @return
     */
    private static double minimax(SpielbrettController spielbrettController, int depth, boolean isMaximizing) {

        Integer s = score(spielbrettController);

         System.out.println("current depth: " + depth + "\nscore: " + s);
         spielbrettController.spielbrettZeichnen();

        //Gebe ggf. Bewertung zurueck
        if (s != null) {
            if (s == 10) {
                return s - depth;
            } else if (s == -10) {
                return s + depth;
            } else if (s == 0) {
                return 0;
            }

        } else if(depth == 9) {
            return 0;
        }

        //Zug des Computers
        if (isMaximizing) {
            double bestScore = Double.NEGATIVE_INFINITY;
            for (int spalte = 0; spalte < 3; spalte++) {
                for (int zeile = 0; zeile < 3; zeile++) {
                    if (spielbrettController.getSpielbrett().getFeld(spalte, zeile).getSign() == ' ') {
                        spielbrettController.getSpielbrett().setSign(spalte, zeile, 'O');
                        spielbrettController.naechsterSpieler();
                        double score = minimax(spielbrettController, (depth + 1), false);
                        spielbrettController.getSpielbrett().setSign(spalte, zeile, ' ');
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            //Zug des Menschen
            double bestScore = Double.POSITIVE_INFINITY;
            for (int spalte = 0; spalte < 3; spalte++) {
                for (int zeile = 0; zeile < 3; zeile++) {
                    if (spielbrettController.getSpielbrett().getFeld(spalte, zeile).getSign() == ' ') {
                        spielbrettController.getSpielbrett().setSign(spalte, zeile, 'X');
                        spielbrettController.naechsterSpieler();
                        double score = minimax(spielbrettController, (depth + 1), true);
                        spielbrettController.getSpielbrett().setSign(spalte, zeile, ' ');
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
     * @return 10: Spiel gewonnen
     * @return 0: mit diesem Zug endet das Spiel mit einem Unentschieden
     * @return -10: Spiel verloren
     * @return null: Spiel noch nicht vorbei
     */
    private static Integer score(SpielbrettController spielbrettController) {
        int rueckgabe;
        char signAmZug = spielbrettController.getSpielerAmZug();

        if (signAmZug == 'O') {
            rueckgabe = 10;
        } else {
            rueckgabe = -10;
        }

        for (int c = 0; c < spielbrettController.getSpielbrett().getSpielfeld().length; c++) {

            //Zeile
            if (spielbrettController.getSpielbrett().getFeld(c, 0).getSign() == signAmZug
                    && spielbrettController.getSpielbrett().getFeld(c, 1).getSign() == signAmZug
                    && spielbrettController.getSpielbrett().getFeld(c, 2).getSign() == signAmZug) {
                return rueckgabe;
            }

            //Spalte
            if (spielbrettController.getSpielbrett().getFeld(0, c).getSign() == signAmZug
                    && spielbrettController.getSpielbrett().getFeld(1, c).getSign() == signAmZug
                    && spielbrettController.getSpielbrett().getFeld(2, c).getSign() == signAmZug) {
                return rueckgabe;
            }
        }

        /**
         * Diagonal 1
         */
        if (spielbrettController.getSpielbrett().getFeld(0, 0).getSign() == signAmZug
                && spielbrettController.getSpielbrett().getFeld(1, 1).getSign() == signAmZug
                && spielbrettController.getSpielbrett().getFeld(2, 2).getSign() == signAmZug) {
            return rueckgabe;
        }

        /**
         * Diagonal 2
         */
        if (spielbrettController.getSpielbrett().getFeld(0, 2).getSign() == signAmZug
                && spielbrettController.getSpielbrett().getFeld(1, 1).getSign() == signAmZug
                && spielbrettController.getSpielbrett().getFeld(2, 0).getSign() == signAmZug) {
            return rueckgabe;
        }

        /**
         * Mit diesem letzten Zug, endet das Spiel mit einem Unentschieden
         */
        boolean unentschieden = true;
        for (int spalte = 0; spalte < 3; spalte++) {
            for (int zeile = 0; zeile < 3; zeile++) {
                if (spielbrettController.getSpielbrett().getFeld(spalte, zeile).getSign() == ' ') {
                    unentschieden = false;
                }
            }
        }

        if (unentschieden) {
            return 0;
        }

        return null;
    }
}
