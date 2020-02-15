package tests;

import feld.Feld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spielbrett.Spielbrett;

public class SpielbrettTest {

    public Spielbrett getSpielbrett(){
        Spielbrett spielbrett = new Spielbrett();

        spielbrett.setSign(0,0, 'O');
        spielbrett.setSign(1,1, 'O');
        spielbrett.setSign(2,2, 'O');

        return spielbrett;
    }

    @Test
    public void spielfelderToString_unbesetzt_deepcopy_test(){
        Spielbrett spielbrett = new Spielbrett();

        String ist = spielbrett.spielfelderToString();
        String soll = "0#0# ~0#1# ~0#2# ~1#0# ~1#1# ~1#2# ~2#0# ~2#1# ~2#2# ~";

        Assertions.assertEquals(soll, ist);
    }

    @Test
    public void spielfelderToString_besetzt_deepcopy_test(){
        Spielbrett spielbrett = getSpielbrett();

        String ist = spielbrett.spielfelderToString();
        String soll = "0#0#O~0#1# ~0#2# ~1#0# ~1#1#O~1#2# ~2#0# ~2#1# ~2#2#O~";

        Assertions.assertEquals(soll, ist);
    }

    @Test
    public void stringToFelder_unbesetzt_deepcopy_test(){
        Spielbrett spielbrett = new Spielbrett();

        String content = spielbrett.spielfelderToString();
        Feld[][] restored = spielbrett.restoreFelder(content);

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                Assertions.assertEquals(spielbrett.getFeld(i,x).getSign(), restored[i][x].getSign());
            }
        }
    }

    @Test
    public void stringToFelder_besetzt_deepcopy_test(){
        Spielbrett spielbrett = getSpielbrett();

        String content = spielbrett.spielfelderToString();
        Feld[][] restored = spielbrett.restoreFelder(content);

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                Assertions.assertEquals(spielbrett.getFeld(i,x).getSign(), restored[i][x].getSign());
            }
        }
    }

    @Test
    public void deepCopy_id_muss_verschieden_sein(){
        Spielbrett spielbrett = this.getSpielbrett();

        Spielbrett deepCopied = spielbrett.deepCopy();

        //pro Feld
        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                //id's muessen verschieden sein
                Assertions.assertNotEquals(spielbrett.getFeld(i,x), deepCopied.getFeld(i,x));
            }
        }

        //ganzes Object
        Assertions.assertNotEquals(spielbrett, deepCopied);
    }

    @Test
    public void deepCopy_werte_muessen_gleich_sein() {
        Spielbrett spielbrett = this.getSpielbrett();

        Spielbrett deepCopied = spielbrett.deepCopy();

        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < 3; x++) {
                //id's muessen verschieden sein
                Assertions.assertEquals(spielbrett.getFeld(i, x).getSign(), deepCopied.getFeld(i, x).getSign());
            }
        }
    }
}
