package default_package;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

 private ArrayList<Card> contents;
 private Player owner;
    
    public Hand(ArrayList<Card> deck, Player p){
        contents = deck;
        owner = p;
    }
    
    public ArrayList<Card> getContents(){
        return contents;
    }
    
    public int getSize(){
        return this.getContents().size();
    }
    
    public Player getOwner(){
        return owner;
    }
    
    public Card get(int a){
        return contents.get(a);
    }
    
    public boolean add(Card c){
        return contents.add(c);
    }
    
    public void removeCard(Card c){
       if(!getContents().contains(c)){
           //do nothing
       } 
        int j = 0;
        for(int i = 0; i<getSize(); i++){
            if(get(i).equals(c)){
                j = i;
            }
        }
        contents.remove(j);
      
    }
    
    public static ArrayList<Card> standard(){
        ArrayList<Card> list = new ArrayList<>();
        for(Suit s: Suit.values()){
            for(int i = 2; i<15; i++){
                list.add(new Card(s, i));
            }
        }
        return list;
    }
    
    public void deal(Player p){
        p.getHand().add(this.get(0));
        this.removeCard(this.get(0));
    }

    public void shuffle(){
        Collections.shuffle(this.contents);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contents == null) ? 0 : contents.hashCode());
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
        Hand other = (Hand) obj;
        if (contents == null) {
            if (other.contents != null)
                return false;
        } else if(this.getSize() != other.getSize()){
            return false;
        } else{
            for(int i = 0; i < this.getSize(); i++){
                if(this.getContents().get(i) != other.getContents().get(i)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getContents().toString();
    }
    
}
