package spieler;

import feld.Feld;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class SpielerController {

    public SpielerController(){

    }


    public abstract Feld naechstesFeld();
}
