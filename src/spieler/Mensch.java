package spieler;

public class Mensch extends Spieler  {

    public Mensch() {}

    /**
     * Gibt das Zeichen des Menschen wieder ('X')
     * @return
     */
    @Override
    public char getSign() {
        return 'X';
    }

}
