package spielbrett;

import feld.Feld;

import java.io.*;

public class Spielbrett implements Serializable{

    private Feld[][] spielfelder;

    String delimeter = "~";
    String delimeterAttribut = "#";


    public Spielbrett(){

        this.spielfelder = new Feld[3][3];

        for(int spalte = 0; spalte < 3; spalte++){
            for(int zeile = 0; zeile < 3; zeile++){
                this.spielfelder[spalte][zeile] = new Feld(spalte, zeile);
            }
        }
    }

    public Spielbrett(Spielbrett spielbrett){
        this.spielfelder = spielbrett.getSpielfeld();
    }

    public Spielbrett(Feld[][] spielfelder){
        this.spielfelder = spielfelder;
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

        System.out.println("fehler");
    return null;
    }

}
