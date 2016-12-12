package default_package;

import java.util.ArrayList;

public class Trick {

    private int leader;
    private ArrayList<Card> cards;
    private Player[] players;
    private int value;
    private int winner;
    
    public Trick(int leader, Player[] players, GamePage g, int start){
        this.leader = leader;
        this.players = players;
        
        for(int i = leader; i<leader+4; i++){
                if(start == 0){
                    for(Card c: players[leader].getHand().getContents()){
                        if(c.toString().equals("2C")){
                            g.getTable().cardPlayed(c);
                            players[leader].getHand().removeCard(c);
                            System.out.println(players[leader] + " played");
                            break;
                        }
                    }
                    start++;
                    i++;
                }
                players[i%4].playCard(g);
        }
        cards = g.getTable().getCards();
        
        value = 0;
        for(Card c: cards){
            if(c.getSuit() == Suit.HEARTS){
                value++;
            }
            else if(c.getSuit() == Suit.SPADES && c.getVal() == 12){
                value+=13;
            }
        }
        
        int winner = 0;
        for(int i = 1; i<4; i++){
            if(cards.get(i).compareTo(cards.get(winner)) > 0){

                System.out.print(i+ "/" +winner +"->");
                winner = i;
                System.out.println(" " + winner);
            }
        }
        this.winner = (leader+winner)%4;
        
        updateState();
    }
    
    public int getLeader(){
        return leader;
    }
    
    public int getWinner(){
        return winner;
    }
    
    public int getValue(){
        return value;
    }
    
    public void updateState(){
        players[winner].addScore(value);
    }
    
}
