package spiel;

import spielbrett.SpielbrettController;

public class Spiel {

    /**
     * Spielbrettcontroller
     */
    private SpielbrettController spielbrettController;

    public Spiel(){
        spielbrettController = new SpielbrettController();
    }

    /**
     * Ruft so oft naechsterZug() auf, bis jemand gewonnen hat oder unentschieden.
     */
    public void starteSpiel(){
        System.out.println("-----------------------------------------");
        System.out.println("TicTacToe - Mario Teklic - Programmfabrik");
        System.out.println("-----------------------------------------");
        System.out.println("Dein Gegner ist der unbeatable Computer!");
        System.out.println("Das Spielfeld wird in ASCII-Art gezeichnet. :)");
        System.out.println("-----------------------------------------");
        System.out.println("-----    Spiel wird gestartet!    -------");
        System.out.println("-----------------------------------------\n");

        spielbrettController.spielbrettZeichnen();

        while(!spielbrettController.naechsterZug()){
            spielbrettController.spielbrettZeichnen();
        }

        System.out.println("\n---------- Spiel vorbei ---------\n");

    }
}
