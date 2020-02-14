package spielbrett;

import feld.Feld;

import java.io.*;

public class Spielbrett implements Serializable{

    /**
     * Alle Spielfelder des Spielbretts
     */
    private Feld[][] spielfelder;

    /**
     * Felddelimeter (fuer deepcopy)
     */
    private String delimeter = "~";

    /**
     * Attributedelimeter (fuer deepcopy)
     */
    private String delimeterAttribut = "#";

    /**
     * Spielbrett-konstruktor
     */
    public Spielbrett(){

        this.spielfelder = new Feld[3][3];

        for(int spalte = 0; spalte < 3; spalte++){
            for(int zeile = 0; zeile < 3; zeile++){
                this.spielfelder[spalte][zeile] = new Feld(spalte, zeile);
            }
        }
    }

    /**
     * Spielbrett-konstruktor, um ein Spielbrett zuerzeugen, mit Spielfeldern eines anderes Spielbrettes
     * @param spielfelder
     */
    public Spielbrett(Feld[][] spielfelder){
        this.spielfelder = spielfelder;
    }

    /**
     * Gibt ein Feld zurueck
     * @param spalte Spalte
     * @param zeile Zeile
     * @return das Feld
     */
    public Feld getFeld(int spalte, int zeile){
        return this.spielfelder[spalte][zeile];
    }

    /**
     * Setzt ein Zeichen auf ein Feld
     * @param spalte Spalte
     * @param zeile Zeile
     * @param sign Zeichnen
     */
    public void setSign(int spalte, int zeile, char sign){
        this.spielfelder[spalte][zeile].setSign(sign);
    }

    /**
     * Gibt die Spielfelder zurueck
     * @return die Spielfelder
     */
    public Feld[][] getSpielfeld(){
        return this.spielfelder;
    }

    /**
     * Konvertiert alle Spielfelder zu einem String (fuer deepcopy)
     * @return alle Spielfelder als String
     */
    public String spielfelderToString(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                sb.append(spielfelder[i][x].getSpalte());
                sb.append(delimeterAttribut);
                sb.append(spielfelder[i][x].getZeile());
                sb.append(delimeterAttribut);
                sb.append(spielfelder[i][x].getSign());
                sb.append(delimeter);

            }
        }
        return sb.toString();
    }

    /**
     * Stellt alle Spielfelder aus einem String wiederher
     * @param s alle Spielfelder als String
     * @return alle Spielfelder als Feld-Variablen
     */
    public Feld[][] restoreFelder(String s){
        Feld[][] restored = new Feld[3][3];
        String[] felder = s.split(delimeter);
        int counter = 0;

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                restored[i][x] = new Feld();

                String[] attribute = felder[counter].split(delimeterAttribut);
                restored[i][x].setSpalte(Integer.parseInt(attribute[0]));
                restored[i][x].setZeile(Integer.parseInt(attribute[1]));
                restored[i][x].setSign(attribute[2].charAt(0));
                counter++;
            }
        }
        return restored;
    }

    /**
     * Deepcopyverfahren fuer ein Spielbrett.
     * Es ist moeglich dieses Verfahren zu nehmen und immer ein neues Spielbrett zuerstellen,
     * welches komplett unabhaengig vom Ursprungsspielbrett ist.
     * @return Neues Spielbrett mit den gleichen besetzten Feldern des Ursprungsspielbretts.
     */
    public Spielbrett deepCopy(){

        try {
            for(int spalte = 0; spalte < 3; spalte++){
                for(int zeile = 0; zeile < 3; zeile++){

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(this.spielfelderToString());
                    oos.flush();
                    oos.close();
                    bos.close();

                    byte[] byteSpielfeld = bos.toByteArray();

                    ByteArrayInputStream bais = new ByteArrayInputStream(byteSpielfeld);
                    String spielfelderString = (String) new ObjectInputStream(bais).readObject();

                    return new Spielbrett(restoreFelder(spielfelderString));
                }
            }
        } catch (IOException e) {
            System.out.println("IO ex");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Classnotfound ex");
            e.printStackTrace();
        }
    return null;
    }
}
