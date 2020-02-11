package spieler;

import feld.Feld;
import spielbrett.Spielbrett;

public class Computer extends Spieler {

    public Computer() {

    }

    @Override
    public char getSign() {
        return 'O';
    }

}
