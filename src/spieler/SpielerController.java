package spieler;

import feld.Feld;

public abstract class SpielerController {

    public SpielerController(){}

    public abstract Feld naechstesFeld();

    public abstract  char getSign();
}
