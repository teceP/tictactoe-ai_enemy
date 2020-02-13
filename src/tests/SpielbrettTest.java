package tests;

import spielbrett.Spielbrett;

public class SpielbrettTest {
    public Spielbrett modifiziertesSpielbrett(){
        Spielbrett spielbrett = new Spielbrett();

        spielbrett.setSign(0,0, 'O');
        spielbrett.setSign(1,1, 'O');
        spielbrett.setSign(2,2, 'O');

        return spielbrett;
    }

}
