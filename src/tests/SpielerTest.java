package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spieler.Computer;
import spieler.Mensch;
import spieler.Spieler;

public class SpielerTest {

    @Test
    public void menschSign(){
        Spieler mensch = new Mensch();
        Assertions.assertEquals('X', mensch.getSign());
    }

    @Test
    public void computerSign(){
        Spieler computer = new Computer();
        Assertions.assertEquals('O', computer.getSign());
    }
}
