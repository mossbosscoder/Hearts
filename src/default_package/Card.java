package default_package;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Card extends JComponent implements Comparable<Card>{

    private final Suit SUIT;
    private final int VAL; 
    private final String img_file;
    private static BufferedImage img;
    
    public Card(Suit suit, int val){
        if(val < 2 || val > 14){
            throw new IllegalArgumentException();
        }
        this.SUIT = suit;
        this.VAL = val;
        this.img_file = VAL + "_of_" + SUIT.toString().toLowerCase() + ".png";
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(img_file));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public Suit getSuit(){
        return SUIT;
    }
    
    public int getVal(){
        return VAL;
    }
    
    public boolean canBePlayed(Suit trumps){
        return SUIT.equals(trumps);
    }
    
    public void draw(JPanel p){
        ImageIcon ima = new ImageIcon(img, null);
        JLabel l = new JLabel(ima, JLabel.CENTER);
        p.add(l);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((SUIT == null) ? 0 : SUIT.hashCode());
        result = prime * result + VAL;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (SUIT != other.SUIT)
            return false;
        if (VAL != other.VAL)
            return false;
        return true;
    }

    @Override
    public String toString(){
        String suit = "";
        switch(SUIT){
            case DIAMONDS: suit += 'D';
                           break;
            case HEARTS:   suit += 'H';
                           break;
            case SPADES:   suit += 'S';
                           break;
            case CLUBS:    suit += 'C';
                           break;
            default:       throw new IllegalArgumentException();
        }
        if(VAL < 11){
            int num = VAL;
            return num + suit;
        } else{
            String value = "";
            switch(VAL){
                case 11: value += "J";
                         break;
                case 12: value += "Q";
                         break;
                case 13: value += "K";
                         break;
                case 14: value += "A";
                         break;
                default: throw new IllegalArgumentException();
            }
            return value + suit;
        }
    }
    
    @Override
    public int compareTo(Card c) {
        if(this.getSuit() != c.getSuit() || this.getVal() < c.getVal()){
            return -1;
        }
        else if(this.getVal() > c.getVal()){
            return 1;
        }
        else{
            return 0;
        }
    }
    
}
