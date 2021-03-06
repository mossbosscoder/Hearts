package default_package;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePage extends JPanel{

    private Player user;
    private Player cpu1;
    private Player cpu2;
    private Player cpu3;
    private Player[] players = new Player[4];
    private Table table;
    private Color backColor;
    private Round round;
    private JPanel main;
    private CardLayout c;
    
    
    public GamePage(String playerName, Color c, JPanel mainPanel, CardLayout c1){
        backColor = c;
        user = new Player(playerName, true);
        cpu1 = new Player("Brint", false);
        cpu2 = new Player("Meekus", false);
        cpu3 = new Player("Rufus", false);
        players[0] = user;
        players[1] = cpu1;
        players[2] = cpu2;
        players[3] = cpu3;
        table = new Table();
        round = null;
        main = mainPanel;
        this.c = c1;
        
        //draw();
        /*
        createTopRow();
        createMiddleRow();
        createBottomRow();
        drawHand();
        drawStatus();
      */
        setLayout(new GridLayout(5, 0));
    }
    
    public Table getTable(){
        return table;
    }
    
    public void setTable(Table t){
        table = t;
    }
    
    public void begin(){
        
        System.out.println("GamePage::begin");
        deal();
        draw();
        revalidate();
        
        for(Card c: user.getHand().getContents()){
            addListener(c);
        }
        
        //find who has the two of clubs
        int leader = 0;
        boolean found = false;
        for(Player p: players){
            for(Card c: p.getHand().getContents()){
                if(c.getSuit() == Suit.CLUBS && c.getVal() == 2){
                    found = true;
                    break;
                }
            }
            if(found) break;
            leader++;
        }
        System.out.println("---------------------------");
        System.out.println(found + ": " + leader + ": " + players[leader]);
        System.out.println(players[0]);
        System.out.println(players[1]);
        System.out.println(players[2]);
        System.out.println(players[3]);
        System.out.println("---------------------------");
        round = new Round(leader, players, this);
        round.playRound();
        
    }
    public void newBegin(){
        
        System.out.println("GamePage::begin");
        deal();
        draw();
        revalidate();
        
        for(Card c: user.getHand().getContents()){
            addListener(c);
        }
        
        //find who has the two of clubs
        int leader = 0;
        boolean found = false;
        for(Player p: players){
            for(Card c: p.getHand().getContents()){
                if(c.getSuit() == Suit.CLUBS && c.getVal() == 2){
                    found = true;
                    break;
                }
            }
            if(found) break;
            leader++;
            
        }
        System.out.println("--------------------biuhjnjlbkjn-------");
        System.out.println(found + ": " + leader + ": " + players[leader]);
        System.out.println(players[0]);
        System.out.println(players[1]);
        System.out.println(players[2]);
        System.out.println(players[3]);
        System.out.println("-----------------------.,.,...----");
        round.setLeader(leader);
        round.setActualLeader(leader);
        round.playRound();
    }
    
    
    
    private void createTopRow(){
      //top row
        JPanel top = new JPanel();
        drawPlayer(cpu2, top);
        add(top);
        top.setBackground(backColor);
    }
    
    private void createMiddleRow(){
        //middle row
        JPanel mid = new JPanel();
        drawPlayer(cpu1, mid);
        mid.add(table);
        drawPlayer(cpu3, mid);
        add(mid);
        mid.setBackground(backColor);
    }
    
    private void createBottomRow(){
        //bottom row
        JPanel bottom = new JPanel();
        drawPlayer(user, bottom);
        add(bottom);
        bottom.setBackground(backColor);
    }
    
    private void drawHand(){
      //player's hand
        JPanel hand = new JPanel();
        hand.add(new JLabel("Hand: "));
        for(Card card: user.getHand().getContents()){
            hand.add(card.getRep());
        }
        add(hand);
    }
    
    public void addListener(Card card){
        card.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    Card clone = new Card(card.getSuit(), card.getVal());
                    table.cardPlayed(clone);
                    user.getHand().removeCard(card);

                    System.out.println(user.getName()+ " played");
                    round.setStartToFalse();
                    round.setLeader(1);
                    round.playRound();
                    table.draw();
                    table.revalidate();
                    if(table.getCards().size() == 0 && user.getHand().getContents().size() == 0){
                        newBegin();
                    }

                    draw();
                }
            });
    }
    
    
    public void removeListener(Card card){
        card.removeMouseListener(card.getML());
    }
    
    private void drawStatus(){
      //status bar
        JPanel status = new JPanel();
        status.add(new JLabel("Status:"));
        add(status);
    }
    
    private void drawPlayer(Player p, JPanel g){
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        JLabel n = new JLabel(" " + p.getName() + " ");
        n.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        n.setFont(f);
        JLabel s = new JLabel(p.getScore() + " (" + p.getScoreThisRound() + ") ");
        s.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        s.setFont(f);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(n);
        panel.add(s);
        g.add(panel);
        
    }
    
    public void deal(){
        Hand h = new Hand(Hand.standard(), user);
        for(Player p: players){
            p.setHand(new Hand(new ArrayList<Card>(), p));
        }
        user.setHand(h);
        user.getHand().shuffle();
        for(int i = 0; i<13; i++){
            user.getHand().deal(cpu1);
            user.getHand().deal(cpu2);
            user.getHand().deal(cpu3);
        }
    }
    
    public void draw(){
       // System.out.println("GamePage::draw");
        removeAll();
        createTopRow();
        createMiddleRow();
        createBottomRow();
        drawHand();
        drawStatus();
    }
    
    public void end(){
        System.out.print("GamePage::END::START");
        GameOverPage g = new GameOverPage(players, main, c);
        main.add(g, "Game Over");
        c.show(main, "Game Over");
        System.out.print("GamePage::END::END");
    }
    
    
/*    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        createTopRow();
        createMiddleRow();
        createBottomRow();
        drawHand();
        drawStatus();
    }
    */
    
 /*   public void playRound(){
        boolean gameOver = false;
        while(!gameOver){
            int leader = 0;
            boolean found = false;
            for(Player p: players){
                for(Card c: p.getHand().getContents()){
                    if(c.getSuit() == Suit.CLUBS && c.getVal() == 2){
                        found = true;
                        break;
                    }
                }
                if(found) break;
                leader++;
            }
            A: {
                for(int j =0; j<13; j++){
                Table tab = new Table();
                table = tab;
                System.out.println(leader);
                Trick t = new Trick(leader, players, this, j);
                leader = t.getWinner();
                table.clear();
                
                }
                for(Player p:players){
                    if(p.getScore()>= 100){
                        System.out.println(p.getScore());
                        gameOver = true;
                        break A;
                    }
                }
                for(Player p: players){
                p.resetScoreThisRound();
                }
                deal();
            }
            for(Player p: players){
               System.out.println(p);
            }
          }
        
    } */
    
    
    
}
