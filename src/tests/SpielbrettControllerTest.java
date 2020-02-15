package tests;

import feld.Feld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spielbrett.SpielbrettController;

public class SpielbrettControllerTest {

    public SpielbrettController getSpielbrettController(){
        return new SpielbrettController();
    }

    @Test
    public void verifiziereZugTest_sollteFalseSein_weil_FelderBelegt(){
        SpielbrettController controller = getSpielbrettController();

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                controller.zugSetzen(new Feld(i, x), 'X');
                Assertions.assertFalse(controller.verifiziereZug(new Feld(i,x)));
            }
        }
    }

    @Test
    public void verifiziereZugTest_sollteTrueSein_weil_FelderNichtBelegt(){
        SpielbrettController controller = getSpielbrettController();

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                Assertions.assertTrue(controller.verifiziereZug(new Feld(i,x)));
            }
        }
    }

    @Test
    public void naechsterSpielerTest_trueZuFalse_weil_menschWarAmZug(){
        SpielbrettController controller = getSpielbrettController();

        char before = controller.getSpielerAmZug();
        controller.naechsterSpieler();
        Assertions.assertTrue(controller.getSpielerAmZug() != before);

        controller.naechsterSpieler();
        Assertions.assertTrue(controller.getSpielerAmZug() == before);
    }

    @Test
    public void pruefeGewinnerTest_Spalte_0(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x - -
         * x - -
         * x - -
         *
         */

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 1; x++){
                controller.zugSetzen(new Feld(i,x), controller.getSpielerAmZug());
            }
        }

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }

    @Test
    public void pruefeGewinnerTest_Spalte_1(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - x -
         * - x -
         * - x -
         *
         */

        for(int i = 0; i < 3; i++){
            for(int x = 1; x < 2; x++){
                controller.zugSetzen(new Feld(i,x), controller.getSpielerAmZug());
            }
        }

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);;

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }

    @Test
    public void pruefeGewinnerTest_Spalte_2(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - x
         * - - x
         * - - x
         *
         */

        for(int i = 0; i < 3; i++){
            for(int x = 2; x < 3; x++){
                controller.zugSetzen(new Feld(i,x), controller.getSpielerAmZug());
            }
        }

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }

    @Test
    public void pruefeGewinnerTest_Zeile_0(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x x x
         * - - -
         * - - -
         *
         */

        for(int i = 0; i < 1; i++){
            for(int x = 0; x < 3; x++){
                controller.zugSetzen(new Feld(i,x), controller.getSpielerAmZug());
            }
        }

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }
    @Test
    public void pruefeGewinnerTest_Zeile_1(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - -
         * x x x
         * - - -
         *
         */

        for(int i = 1; i < 2; i++){
            for(int x = 0; x < 3; x++){
                controller.zugSetzen(new Feld(i,x), controller.getSpielerAmZug());
            }
        }

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }
    @Test
    public void pruefeGewinnerTest_Zeile_2(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - -
         * - - -
         * x x x
         *
         */

        for(int i = 2; i < 3; i++){
            for(int x = 0; x < 3; x++){
                controller.zugSetzen(new Feld(i,x), controller.getSpielerAmZug());
            }
        }

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }

    @Test
    public void pruefeGewinnerTest_Diagonal_0(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x - -
         * - x -
         * - - x
         *
         */
        controller.zugSetzen(new Feld(0,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(1,1), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(2,2), controller.getSpielerAmZug());

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }

    @Test
    public void pruefeGewinnerTest_Diagonal_1(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - x
         * - x -
         * x - -
         *
         */

        controller.zugSetzen(new Feld(2,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(1,1), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(0,2), controller.getSpielerAmZug());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        Assertions.assertTrue(controller.pruefeGewinner() == controller.getSpielerAmZug());
    }


    @Test
    public void pruefeZug_computerBlockiertMenschen_0(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x - -        x - -
         * - x -   ->   - x -
         * - - -        - - o
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(0,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(1,1), controller.getSpielerAmZug());

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 2,2
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(2,2).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_0");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_computerBlockiertMenschen_1(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x - -        x - -
         * - - -   ->   - o -
         * - - x        - - x
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(0,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(2,2), controller.getSpielerAmZug());

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(3);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 1,1
        controller.naechsterSpieler();

        //System.out.print(controller.getSpielbrett().getFeld(1,1).getSign() + " <- sollte gesetzt sein ");

        Assertions.assertFalse(controller.naechsterZug());

        controller.spielbrettZeichnen();

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(1,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_1");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_computerBlockiertMenschen_2(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - x        - - x
         * - - -   ->   - o -
         * x - -        x - -
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(2,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(0,2), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 1,1
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(1,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_2");
        controller.spielbrettZeichnen();
    }


    @Test
    public void pruefeZug_computerBlockiertMenschen_3(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x - x        x o x
         * - - -   ->   - - -
         * - - -        - - -
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(0,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(0,2), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 0,1
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(0,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_3");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_computerBlockiertMenschen_4(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - -        - - -
         * x - x   ->   x o x
         * - - -        - - -
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(1,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(1,2), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 1,1
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(1,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_4");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_computerBlockiertMenschen_5(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - -        - - -
         * - - -        - - -
         * x - x   ->   x o x
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(2,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(2,2), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 2,1
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(2,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_5");
        controller.spielbrettZeichnen();
    }
    @Test
    public void pruefeZug_computerBlockiertMenschen_6(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x - -        x - -
         * - - -        o - -
         * x - -   ->   x - -
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(0,0), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(2,0), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 1,0
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(1,0).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_6");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_computerBlockiertMenschen_7(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - x -        - x -
         * - - -        - o -
         * - x -   ->   - x -
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(0,1), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(2,1), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 1,1
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(1,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_7");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_computerBlockiertMenschen_8(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - - -        - o -
         * - x -        - x -
         * - x -   ->   - x -
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(1,1), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(2,1), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 0,1
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(0,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_8");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_computerBlockiertMenschen_10(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * - x -        - x -
         * - x -        - x -
         * - - -   ->   - o -
         *
         */

        //X (Mensch) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'X'){
            controller.naechsterSpieler();
        }

        controller.zugSetzen(new Feld(0,1), controller.getSpielerAmZug());
        controller.zugSetzen(new Feld(1,1), controller.getSpielerAmZug());

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(5);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 2,1
        controller.naechsterSpieler();

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(2,1).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_10");
        controller.spielbrettZeichnen();
    }


    @Test
    public void pruefeZug_computerBlockiertMenschen_11(){
        SpielbrettController controller = getSpielbrettController();

        /**
         *
         * x o x        x o x
         * o o x   ->   o o x
         * - x -        - x o
         *
         */

        controller.zugSetzen(new Feld(0,0), 'X');
        controller.zugSetzen(new Feld(1,0), 'O');
        controller.zugSetzen(new Feld(0,1), 'O');
        controller.zugSetzen(new Feld(1,1), 'O');
        controller.zugSetzen(new Feld(2,1), 'X');
        controller.zugSetzen(new Feld(0,2), 'X');
        controller.zugSetzen(new Feld(1,2), 'X');

        controller.spielbrettZeichnen();

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(7);

        //O (Computer) setzt Spielzug und muss den Gewinnerzug des Menschen blockieren
        //In diesem Szenario: 2,2

        //O (Computer) setzt Spielzuege
        while(controller.getSpielerAmZug() != 'O'){
            controller.naechsterSpieler();
        }

        Assertions.assertEquals('O', controller.getSpielerAmZug());

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertFalse(controller.naechsterZug());

        controller.spielbrettZeichnen();

        //Computer muss auf diesem Feld gesetzt haben
        Assertions.assertEquals('O', controller.getSpielbrett().getFeld(2,2).getSign());

        System.out.println("pruefeZug_computerBlockiertMenschen_11");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_unentschieden_11(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * o o x
         * x x o
         * o x o
         *
         */

        controller.zugSetzen(new Feld(0,0), 'O');
        controller.zugSetzen(new Feld(0,1), 'O');
        controller.zugSetzen(new Feld(0,2), 'X');
        controller.zugSetzen(new Feld(1,0), 'X');
        controller.zugSetzen(new Feld(1,1), 'X');
        controller.zugSetzen(new Feld(1,2), 'O');
        controller.zugSetzen(new Feld(2,0), 'O');
        controller.zugSetzen(new Feld(2,1), 'X');
        controller.zugSetzen(new Feld(2,2), 'O');

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(9);

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertEquals('z', controller.pruefeGewinner());

        System.out.println("pruefeZug_unentschieden_11");
        controller.spielbrettZeichnen();
    }

    @Test
    public void pruefeZug_unentschieden_12(){
        SpielbrettController controller = getSpielbrettController();

        /**
         * x o o
         * o x x
         * x o o
         *
         */

        controller.zugSetzen(new Feld(0,0), 'X');
        controller.zugSetzen(new Feld(0,1), 'O');
        controller.zugSetzen(new Feld(0,2), 'O');
        controller.zugSetzen(new Feld(1,0), 'O');
        controller.zugSetzen(new Feld(1,1), 'X');
        controller.zugSetzen(new Feld(1,2), 'X');
        controller.zugSetzen(new Feld(2,0), 'X');
        controller.zugSetzen(new Feld(2,1), 'O');
        controller.zugSetzen(new Feld(2,2), 'O');

        //pruefeGewinner wird erst ausgefuehrt, wenn mehr als 4 Zuege gemacht wurden, weil vorher kann es keinen Gewinner geben
        controller.setAnzahlZuege(9);

        //naechsterZug() gibt false zurueck, wenn Spiel nicht zuende
        Assertions.assertEquals('z', controller.pruefeGewinner());

        System.out.println("pruefeZug_unentschieden_11");
        controller.spielbrettZeichnen();
    }
}
