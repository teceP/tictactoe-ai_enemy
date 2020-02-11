package spieler;

import feld.Feld;
import spielbrett.Spielbrett;

public class Minimax {


    private Minimax() {
    }

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

    private static double minimax(Spielbrett spielbrett, int depth, boolean isMaximizing) {

        /* Pruefe: Spiel mit diesem Zug gewonnen
         *
         * 10 = Letzter Zug des Computers
         * -10 = Letzter Zug des Menschens
         * 0 = unentschieden
         *
         * sonst: schauen, welcher Zug als naechstes am besten waere
         */
        if(score(spielbrett, isMaximizing) != null) {
            if (score(spielbrett, isMaximizing) == 10) {
                return 10;
            } else if (score(spielbrett, isMaximizing) == -10) {
                return -10;
            } else if (score(spielbrett, isMaximizing) == 0) {
                return 0;
            }
        }

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

    private static Integer score(Spielbrett spielbrett, boolean computer) {
        char sign;
        int rueckgabe;

        if (computer) {
            sign = 'O';
            rueckgabe = 10;
        } else {
            sign = 'X';
            rueckgabe = -10;
        }


        for (int c = 0; c < spielbrett.getSpielfeld().length; c++) {

            //Zeile
            if (spielbrett.getFeld(c, 0).getSign() == sign
                    && spielbrett.getFeld(c, 1).getSign() == sign
                    && spielbrett.getFeld(c, 2).getSign() == sign) {
                return rueckgabe;
            }

            //Spalte
            if (spielbrett.getFeld(0, c).getSign() == sign
                    && spielbrett.getFeld(1, c).getSign() == sign
                    && spielbrett.getFeld(2, c).getSign() == sign) {
                return rueckgabe;
            }
        }

        /**
         * Diagonal 1
         */
        if (spielbrett.getFeld(0, 0).getSign() == sign
                && spielbrett.getFeld(1, 1).getSign() == sign
                && spielbrett.getFeld(2, 2).getSign() == sign) {
            return rueckgabe;
        }

        /**
         * Diagonal 2
         */
        if (spielbrett.getFeld(0, 2).getSign() == sign
                && spielbrett.getFeld(1, 1).getSign() == sign
                && spielbrett.getFeld(2, 0).getSign() == sign) {
            return rueckgabe;
        }

        /**
         * Mit diesem letzten Zug, endet das Spiel mit einem Unentschieden
         */
        for(int spalte = 0; spalte < 3; spalte++){
            for(int zeile = 0; zeile < 3; zeile++){
                if(spielbrett.getFeld(spalte, zeile).getSign() != ' '){
                    System.out.println("Spiel vorbei - unentschieden");
                    return 0;
                }
            }
        }

        return null;
    }
}
