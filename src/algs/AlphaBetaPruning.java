package algs;

import feld.Feld;
import spielbrett.Spielbrett;
import spielbrett.SpielbrettController;

import java.util.ArrayList;

public class AlphaBetaPruning {

    private static double maxDepth;
    private static Feld besterZug;

    private AlphaBetaPruning() {
    }

    public static Feld run(SpielbrettController spielbrettController,char player, double maxDepth) {
        if (maxDepth < 1) {
            throw new IllegalArgumentException("Max depth must be greater than 0.");
        }

        AlphaBetaPruning.besterZug = new Feld(-1,-1);
        AlphaBetaPruning.maxDepth = maxDepth;
        alphaBetaPruning(spielbrettController, player, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
        return besterZug;
    }

    private static int alphaBetaPruning(SpielbrettController spielbrettController, char player, double alpha, double beta, int currentDepth) {

        //Methode geht nicht weiter, wegen spielVorbei oder currentDepth++ == max

        if (currentDepth++ == maxDepth || spielVorbei(spielbrettController.getSpielbrett())) {
            return score(spielbrettController.getSpielbrett(), player, currentDepth);
        }

        if (spielbrettController.getSpielerAmZug() == 'O')
            return getMax(spielbrettController, player, alpha, beta, currentDepth);
        else // == 'X'
            return getMin(spielbrettController, player, alpha, beta, currentDepth);
    }

    private static int zugZuInt(Feld zug) {
        if (zug.getSpalte() == 0 && zug.getZeile() == 0) {
            return 0;

        } else if (zug.getSpalte() == 0 && zug.getZeile() == 1) {
            return 1;

        } else if (zug.getSpalte() == 0 && zug.getZeile() == 2) {
            return 2;

        } else if (zug.getSpalte() == 1 && zug.getZeile() == 0) {
            return 3;

        } else if (zug.getSpalte() == 1 && zug.getZeile() == 1) {
            return 4;

        } else if (zug.getSpalte() == 1 && zug.getZeile() == 2) {
            return 5;

        } else if (zug.getSpalte() == 2 && zug.getZeile() == 0) {
            return 6;
        } else if (zug.getSpalte() == 2 && zug.getZeile() == 1) {
            return 7;
        } else if (zug.getSpalte() == 2 && zug.getZeile() == 2) {
            return 8;

        }

        return -1;
    }

    public static int getMax(SpielbrettController spielbrettController, char player, double alpha, double beta, int currentDepth) {

        for (Feld zug : freieFelder(spielbrettController.getSpielbrett())) {


            SpielbrettController modSpielbrettController = new SpielbrettController();
            modSpielbrettController.setSpielbrett(spielbrettController.getSpielbrett().deepCopy());

            modSpielbrettController.zugSetzen(zug, player);

            int score = alphaBetaPruning(modSpielbrettController, player, alpha, beta, currentDepth+1);

            if (score > alpha) {
                alpha = score;
                besterZug = zug;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if(besterZug.getSpalte() != -1 && besterZug.getZeile() != -1){
            spielbrettController.zugSetzen(besterZug, player);
        }

        return (int) alpha;

    }

    public static int getMin(SpielbrettController spielbrettController, char player, double alpha, double beta, int currentDepth) {

        for (Feld zug : freieFelder(spielbrettController.getSpielbrett())) {

            //Dummy
            SpielbrettController modSpielbrettController = new SpielbrettController();
            modSpielbrettController.setSpielbrett(spielbrettController.getSpielbrett().deepCopy());

            modSpielbrettController.zugSetzen(zug, player);

            int score = alphaBetaPruning(modSpielbrettController, player, alpha, beta, currentDepth+1);

            if (score < beta) {
                beta = score;
                besterZug = zug;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if(besterZug.getSpalte() != -1 && besterZug.getZeile() != -1){
            spielbrettController.zugSetzen(besterZug, player);
        }

        return (int) beta;
    }

    public static boolean spielVorbei(Spielbrett spielbrett) {
        for (int spalte = 0; spalte < 3; spalte++) {
            for (int zeile = 0; zeile < 3; zeile++) {
                if (spielbrett.getFeld(spalte, zeile).getSign() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static int score(Spielbrett spielbrett, char sign, int currentDepth) {
        int rueckgabe;

        if (sign == 'O') {
            rueckgabe = 10 - currentDepth;
        } else {
            rueckgabe = -10 + currentDepth;
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

        return 0;
    }

    public static ArrayList<Feld> freieFelder(Spielbrett spielbrett) {
        ArrayList<Feld> felder = new ArrayList<>();

        for (int spalte = 0; spalte < 3; spalte++) {
            for (int zeile = 0; zeile < 3; zeile++) {
                if (spielbrett.getFeld(spalte, zeile).getSign() == ' ') {
                    felder.add(spielbrett.getFeld(spalte, zeile));
                }
            }
        }

        return felder;
    }

}
