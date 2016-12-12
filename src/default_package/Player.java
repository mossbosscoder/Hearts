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
        hand = new Hand(new ArrayList<Card>(), this);
          
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
    
    public void setHand(Hand h){
        hand = h;
    }
    
    public boolean isUser(){
        return isUser;
    }
    
    public void addScore(int a){
        score += a;
        scoreThisRound += a;
    }
    
    public void resetScoreThisRound(){
        scoreThisRound = 0;
    }
    
    
    
    public void playCard(GamePage g){
        g.draw();
        g.validate();
        Suit led = null;
        if(g.getTable().getCards().size() != 0){
            led = g.getTable().getCards().get(0).getSuit();
        }
        boolean canFollow = false;
        
        if(isUser){
            if(led != null){
                for(int i = 0 ; i<getHand().getContents().size(); i++){
                        if(getHand().getContents().get(i).canBePlayed(led)){
                            g.addListener(getHand().getContents().get(i));
                            g.getTable().cardPlayed(this.getHand().getContents().remove(i));
                            canFollow = true;
                            break;
                    }
                }
            }
            
            if(canFollow == false){
                g.getTable().cardPlayed(this.getHand().getContents().remove(0));
            }
            
            for(Card c: getHand().getContents()){
                g.removeListener(c);
            }
            
     
            //int i = g.getTable().getCards().size();
          /* while(g.getTable().getCards().size() == i){
                System.out.println("STUCK");
                System.out.println(g.getTable().getCards());
           }*/
            
        }else{
            if(led != null){
                for(int i = 0 ; i<getHand().getContents().size(); i++){
                        if(getHand().getContents().get(i).canBePlayed(led)){
                        g.getTable().cardPlayed(this.getHand().getContents().remove(i));
                        canFollow = true;
                        break;
                    }
                }
            }
            
            if(canFollow == false){
                g.getTable().cardPlayed(this.getHand().getContents().remove(0));
            }
            
        }
        System.out.println(this + "played");
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
