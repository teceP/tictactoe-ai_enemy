package spieler;

import feld.Feld;
import spielbrett.Spielbrett;

public class ComputerController{
    private Computer computer;

    public ComputerController(){
        this.computer = new Computer();
    }

    public Feld naechstesFeld(Spielbrett spielbrett) {

        return Minimax.getMinimaxZug(spielbrett);
    }

    public char getSign(){
        return this.computer.getSign();
    }
}
