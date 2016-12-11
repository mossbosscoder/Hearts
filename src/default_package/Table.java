package default_package;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class Table extends JPanel {

    // the state of the game logic
    
    ArrayList<JComponent> contents = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();

    // Game constants
    public static final int COURT_WIDTH = 230;
    public static final int COURT_HEIGHT = 100;

    public Table() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.GRAY);
    }

    public void cardPlayed(Card c){
        System.out.println("1");
        c.getRep().removeMouseListener(c.getML());
        cards.add(c);
        contents.add(c.getRep());
        draw();
        System.out.println("2");
    }
    
    public void draw(){
        for(JComponent c: contents){
            add(c);
        }
    }
    
    public void clear() {
        cards.removeAll(cards);
        contents.removeAll(contents);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
