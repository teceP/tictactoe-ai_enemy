package feld;

public class Feld {
    private int zeile;
    private int spalte;
    private char sign;

    public Feld() {
        this.sign = ' ';
    }

    public Feld(int spalte, int zeile){
        this.spalte = spalte;
        this.zeile = zeile;
    }

    public Feld(char spalte, char zeile) {
        this.spalte = this.konvertierteCharZuInt(spalte);
        this.zeile = this.konvertierteCharZuInt(zeile);
    }

    /**
     * ASCII-Konvertierung
     *
     * @param c
     * @return
     */
    public int konvertierteCharZuInt(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 49:
                return 0;
            case 50:
                return 1;
            case 51:
                return 2;
            default:
                return -1;
        }
    }


    public int getZeile() {
        return zeile;
    }

    public void setZeile(int zeile) {
        this.zeile = zeile;
    }

    public int getSpalte() {
        return spalte;
    }

    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }

    public char getSign() {
        return this.sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
