package spieler;

import algs.Minimax;
import feld.Feld;
import spielbrett.SpielbrettController;

public class ComputerController{

    /**
     * Computermodel
     */
    private Computer computer;

    public ComputerController(){
        this.computer = new Computer();
    }

    /**
     * Ruft den Minimaxalgorithmus auf, um den naechstbesten Zug des Computers zu bekommen.
     * @param spielbrett das momentane Spielbrett
     * @return naechstbester Zug
     */
    public Feld naechstesFeld(SpielbrettController spielbrett) {
        return Minimax.getMinimaxZug(spielbrett);
    }

    /**
     * Gibt Zeichen des Computers zurueck.
     */
    public char getSign(){
        return this.computer.getSign();
    }
}
