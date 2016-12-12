package default_package;

import java.util.ArrayList;

public class Round {

    private int leader;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Player[] players;
    private int value;
    private int winner;
    private boolean start;
    private GamePage g;
    
    public Round(int leader, Player[] players, GamePage g, int start){
        this.leader = leader;
        this.players = players;
        if(start == 0){ 
            this.start =true;
        }else{
            this.start = false;
        }
        this.g = g;
        
        this.winner = leader;
        
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
    
    public void playRound(){
        for(int i = leader; i<4; i++){
            if(g.getTable().getCards().size() == 4){
                for(Card c: g.getTable().getCards()){
                    cards.add(c);
                }
                evaluate();
                updateState();
                Table t = new Table();
                g.setTable(t);
                cards = new ArrayList<Card>();
                i = leader;
            }
            if(i%4 == 0){
                return;
            }
            if(start){
                for(Card c: players[leader].getHand().getContents()){
                    if(c.toString().equals("2C")){
                        g.getTable().cardPlayed(c);
                        players[leader].getHand().removeCard(c);
                        System.out.println(players[leader] + " played");
                        break;
                    }
                }
                start = false;
                i++;
            }
            if(i%4 == 0){
                return;
            }
            players[i%4].playCard(g);
            if(g.getTable().getCards().size() == 4){
                for(Card c: g.getTable().getCards()){
                    cards.add(c);
                }
                evaluate();
                updateState();
                Table t = new Table();
                g.setTable(t);
            }
        }

    }
    
    public void evaluate(){

        for(int i = 1; i<4; i++){
            if(cards.get(i).compareTo(cards.get(winner)) > 0){

                System.out.print(i+ "/" +winner +"->");
                winner = i;
                System.out.println(" " + winner);
            }
        }
        winner = (leader+winner)%4;
        leader = winner;
    }
    
    public void updateState(){
        players[winner].addScore(value);
    }
    
}
