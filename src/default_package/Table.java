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
    
    private ArrayList<JComponent> contents = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();

    // Game constants
    public static final int COURT_WIDTH = 200;
    public static final int COURT_HEIGHT = 70;

    public Table() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.GRAY);
    }

    public void cardPlayed(Card c){
        if(!cards.contains(c)){
            System.out.println("Table::cardPlayed " + c);
            c.getRep().removeMouseListener(c.getML());
            cards.add(c);
            contents.add(c.getRep());

            System.out.println(cards);
            
            draw();
            //repaint();
            revalidate();

            //draw();
            //System.out.println("exit Table::cardPlayed");
        }
    }
    
    public ArrayList<Card> getCards(){
        return cards;
    }
    
    public void draw(){
        for(JComponent c: contents){
            add(c);
        }
    }
    
    public void clear() {
        cards = new ArrayList<>();
        contents = new ArrayList<>();
        draw();
        revalidate();
    }
    
    public int evaluate(){
        int winner = 0;
        System.out.println("Cards in Round: " + cards );
        for(int i = 1; i<cards.size(); i++){
            if(cards.get(i).compareTo(cards.get(winner)) > 0){

                System.out.print(i+ "/" + winner +"->");
                winner = i;
                System.out.println(" " + winner);
            }
        }
        return winner;
    }
    
    public int value(){
        int v = 0;
        for(Card c: cards){
            if(c.getSuit() == Suit.HEARTS){
                v++;
            }
            if(c.toString().equals("QS")){
                v+=13;
            }
        }
        return v;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(JComponent c: contents){
            add(c);
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
