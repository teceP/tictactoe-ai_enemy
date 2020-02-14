package spieler;

public class Computer extends Spieler {

    public Computer() {}

    /**
     * Gibt das Zeichen des Computers wieder ('O')
     * @return
     */
    @Override
    public char getSign() {
        return 'O';
    }

}
