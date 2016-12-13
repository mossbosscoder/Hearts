package default_package;
import java.util.ArrayList;
import java.util.Collections;

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
    
    public void playCard(GamePage g, boolean heartsBroken){
        g.draw();
        g.validate();
        Suit led = null;
        ArrayList<Card> playables = new ArrayList<>();
        Card played;
        
        if(g.getTable().getCards().size() != 0){
            led = g.getTable().getCards().get(0).getSuit();
        }
        
        if(led == null){
            if(!heartsBroken){
                for(Card c: this.getHand().getContents()){
                    if(c.getSuit() != Suit.HEARTS){
                        playables.add(c);
                    }
                }
                System.out.println(playables);
                if(playables.size() == 0){
                    for(Card c: this.getHand().getContents()){
                        playables.add(c);
                    }
                }
            } else{
                for(Card c: this.getHand().getContents()){
                    playables.add(c);
                }
            }
            int minIndex = 0;
            for(int i = 1; i<playables.size(); i++){
                if(playables.get(i).getVal() < playables.get(minIndex).getVal()){
                    minIndex = i;
                }
            }
            played = playables.get(minIndex);
            g.getTable().cardPlayed(playables.get(minIndex));
        }
        
        else{
            boolean canFollow = false;
            if(led != null){
                for(int i = 0 ; i<getHand().getContents().size(); i++){
                    if(getHand().getContents().get(i).canBePlayed(led)){
                        playables.add(getHand().getContents().get(i));
                        canFollow = true;
                    }
                }
            }
            if(canFollow == false){
                playables.addAll(getHand().getContents());
                int maxIndex = 0;
                for(int i = 1; i<playables.size(); i++){
                    if(playables.get(i).toString().equals("QS")){
                        maxIndex = i;
                        break;
                    }
                    if(playables.get(i).getVal() > playables.get(maxIndex).getVal()){
                        maxIndex = i;
                    }
                }
                played = playables.get(maxIndex);
                g.getTable().cardPlayed(playables.get(maxIndex));
                
            } else{
                int win = g.getTable().evaluate();
                Card winning = g.getTable().getCards().get(win);
                
                int index = 0;
                Collections.sort(playables);
                for(int i = 1; i<playables.size(); i++){
                    if(playables.get(i).getVal() < winning.getVal()){
                        index = i;
                    }
                }
                played = playables.get(index);
                g.getTable().cardPlayed(playables.get(index)); 
            }
            
        }
        
        System.out.println("Played::::" + played.toString());
        getHand().removeCard(played);
        
        System.out.println(this.getName() + " played");
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
