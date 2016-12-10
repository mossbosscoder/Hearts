package default_package;
import java.util.ArrayList;

public class Player{

    private String name;
    private int score;
    private int scoreThisRound;
    private Hand hand;
    private boolean isUser;
    
    public Player(String name, boolean isUser){
        this.name = name;
        score = 0;
        scoreThisRound = 0;
        this.isUser = isUser;
        if(this.isUser){
            hand = new Hand(Hand.standard(), this);
        } else{
            hand = new Hand(new ArrayList<Card>(), this);
        }
          
    }
    
    public String getName(){
        return name;
    }
    
    public int getScore(){
        return score;
    }
    
    public int getScoreThisRound(){
        return scoreThisRound;
    }

    public Hand getHand(){
        return hand;
    }
    
    public boolean isUser(){
        return isUser;
    }
    
    public Card playCard(){
        if(isUser){
            return getHand().getContents().remove(0);
        }else{
            return getHand().getContents().remove(0);
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hand == null) ? 0 : hand.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + score;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", score=" + score + ", hand=" + hand + "]";
    }

}
