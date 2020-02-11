package spieler;

import feld.Feld;
import spielbrett.Spielbrett;

public class Mensch extends Spieler  {

    public Mensch() {

    }

    @Override
    public char getSign() {
        return 'X';
    }

}
