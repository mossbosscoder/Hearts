package default_package;

import java.util.ArrayList;

public class Round {

    private int leader;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Player[] players;
    private int value;
    private int winner;
    private boolean start;
    private int num;
    private GamePage g;
    private boolean heartsBroken;
    
    public Round(int leader, Player[] players, GamePage g){
        this.leader = leader;
        this.num = leader;
        this.players = players;
        this.start = true;
        this.g = g;
        this.winner = leader;
        heartsBroken = false;
    }
    
    public void setStartToFalse(){
        start = false;
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
    
    public void setLeader(int l){
        num = l;
    }
    
    public void setActualLeader(int l){
        leader = l;
    }
    
    public void playRound(){
        for(int i = num; i<4; i++){
            if(g.getTable().getCards().size() == 4){
                for(Card c: g.getTable().getCards()){
                    cards.add(c);
                }
                evaluate();
                updateState();
                Table t = new Table();
                g.setTable(t);
                cards = new ArrayList<Card>();
                value = 0;
                System.out.println("Winner:: " + players[getWinner()].getName());
                i = leader;
            }
            if(i%4 == 0){
                return;
            }
            
            if(!checkReDeal()){
                for(Player p: players){
                    p.resetScoreThisRound();
                }
                start = true;
                g.newBegin();
                return;
            }
            
            
            if(start){
                for(Card c: players[num].getHand().getContents()){
                    if(c.toString().equals("2C")){
                        g.getTable().cardPlayed(c);
                        players[num].getHand().removeCard(c);
                        System.out.println("2C led by " + players[num].getName());
                        break;
                    }
                }
                start = false;
                i++;
            }
            
            if(i%4 == 0){
                return;
            }
            
            for(Player p: players){
                if(p.getScoreThisRound() != 0){
                    if(heartsBroken == false){
                        System.out.println("HEARTS BROKEN");
                    }
                    heartsBroken = true;
                    break;
                }
            }
            
            players[i%4].playCard(g, heartsBroken);
            if(g.getTable().getCards().size() == 4){
                for(Card c: g.getTable().getCards()){
                    cards.add(c);
                }
                evaluate();
                updateState();
                value = 0;
                Table t = new Table();
                g.setTable(t);
                System.out.println("Winner:: " + players[getWinner()].getName());
                cards = new ArrayList<Card>();
                i = leader;
                i--;
            }
        }

    }
    
    public void evaluate(){
        winner = 0;
        System.out.println("Cards in Round: " + cards );
        for(int i = 1; i<4; i++){
            if(cards.get(i).compareTo(cards.get(winner)) > 0){

                System.out.print(i+ "/" + winner +"->");
                winner = i;
                System.out.println(" " + winner);
            }
        }
        winner = (leader+winner)%4;
        leader = winner;
    }
    
    public void updateState(){
        for(Card c: cards){
            if(c.getSuit() == Suit.HEARTS){
                value++;
            }
            if(c.toString().equals("QS")){
                value+=13;
            }
        }
        
        players[winner].addScore(value);
        
        if(players[winner].getScore() >= 100){
            g.end();
        }
    }
    
    public boolean checkReDeal(){
        boolean stillActive = false;
        for(Player p: players){
            if(p.getHand().getContents().size() != 0){
                stillActive = true;
            }
        }
        
        return stillActive;
        
    }
    
    
}
