package spieler;

import feld.Feld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenschController{
    private Mensch mensch;
    private BufferedReader reader;

    public MenschController() {
        this.mensch = new Mensch();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Feld naechstesFeld() {

        try {
            String eingabe = reader.readLine();

            if(eingabe.length() != 2 ||
                    eingabe.charAt(0) < 65 || //ASCII: 65 == A, 66 == B, 67 == C
                    eingabe.charAt(0) > 67 ||
                    eingabe.charAt(1) < 49 || //ASCII: 49 == 1, 50 == 2, 51 == 3
                    eingabe.charAt(1) > 51 ) {
                return null;
            }

            return new Feld(eingabe.charAt(0), eingabe.charAt(1));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public char getSign(){
        return this.mensch.getSign();
    }
}
