package spielbrett;

import feld.Feld;

public class Spielbrett {

    private Feld[][] spielfelder;


    public Spielbrett(){

        this.spielfelder = new Feld[3][3];

        for(int spalte = 0; spalte < 3; spalte++){
            for(int zeile = 0; zeile < 3; zeile++){
                this.spielfelder[spalte][zeile] = new Feld();
            }
        }

    }


    public Feld getFeld(int spalte, int zeile){
        return this.spielfelder[spalte][zeile];
    }

    public void setSign(int spalte, int zeile, char sign){
        this.spielfelder[spalte][zeile].setSign(sign);
    }

    public Feld[][] getSpielfeld(){
        return this.spielfelder;
    }


}
