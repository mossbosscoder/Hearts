package default_package;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

public class Tests {

    @Test public void testDeterminingWinningCard() {
        Table t = new Table();
        
        //leading card wins
        t.cardPlayed(new Card(Suit.DIAMONDS, 2));
        t.cardPlayed(new Card(Suit.HEARTS, 5));
        t.cardPlayed(new Card(Suit.CLUBS, 6));
        t.cardPlayed(new Card(Suit.HEARTS, 14));
        assertEquals(0, t.evaluate());
        t.clear();
        
        //other card wins
        t.cardPlayed(new Card(Suit.DIAMONDS, 2));
        t.cardPlayed(new Card(Suit.DIAMONDS, 5));
        t.cardPlayed(new Card(Suit.CLUBS, 6));
        t.cardPlayed(new Card(Suit.DIAMONDS, 14));
        assertEquals(3, t.evaluate());
    }
    
    @Test public void testAI() {
        Player p1 = new Player("A", false);
        Table t = new Table();
        GamePage g = new GamePage("", Color.black);
        
        ArrayList<Card> a =  new ArrayList<Card>();
        a.add(new Card(Suit.DIAMONDS, 4));
        a.add(new Card(Suit.SPADES, 5));
        a.add(new Card(Suit.CLUBS, 2));
        a.add(new Card(Suit.HEARTS, 8));
        
        p1.setHand(new Hand(a, p1));
        
        //Lead lowest card
        p1.playCard(g);
        assertEquals( "2C", t.getCards().get(0).toString());
        t.clear();
        
        //Follow Suit
        t.cardPlayed(new Card(Suit.DIAMONDS, 10));
        p1.playCard(g);
        assertEquals( "4D", t.getCards().get(1).toString());
        t.clear();
    }
    
    @Test public void testScoreUpdating(){
        Player[] ps = new Player[4];
        Player p1 = new Player("A", false);
        Player p2 = new Player("B", false);
        Player p3 = new Player("C", false);
        Player p4 = new Player("D", false);
        ps[0] = p1;
        ps[1] = p2;
        ps[2] = p3;
        ps[3] = p4;
        GamePage g = new GamePage("", Color.black);
        Round r = new Round(0, ps, g, 1);
        
        g.getTable().cardPlayed(new Card(Suit.HEARTS, 11));
        g.getTable().cardPlayed(new Card(Suit.DIAMONDS, 14));
        g.getTable().cardPlayed(new Card(Suit.HEARTS, 10));
        g.getTable().cardPlayed(new Card(Suit.HEARTS, 12));
        

        r.evaluate();
        r.updateState();
        
        assertEquals(3, p4.getScore());
        System.out.println("FUCCCCK");
        
    }
    
}
