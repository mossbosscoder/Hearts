package default_package;

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
    
    public GamePage(String playerName, Color c){
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
    
    public void begin(){
        
        System.out.println("GamePage::begin");
        deal();
        draw();
        revalidate();
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
                    //System.out.println(user.getHand().getContents());
                    user.getHand().removeCard(card);

                   // playRound();

                    draw();
                    System.out.println("3: " + user.getHand().getContents());
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
    
    private void deal(){
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
        System.out.println("GamePage::draw");
        removeAll();
        createTopRow();
        createMiddleRow();
        createBottomRow();
        drawHand();
        drawStatus();
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
    
    public void playRound(){
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
        
    }
    
    
    
}
