package tests;

import feld.Feld;
import org.junit.jupiter.api.Test;
import spielbrett.Spielbrett;
import spielbrett.SpielbrettController;

public class SpielbrettControllerTest {



    @Test
    public void test(){
        SpielbrettController controller = new SpielbrettController();
        controller.zugSetzen(new Feld(0,0), 'X');
        controller.zugSetzen(new Feld(1,1), 'X');
        controller.zugSetzen(new Feld(2,2), 'X');


    }
}
