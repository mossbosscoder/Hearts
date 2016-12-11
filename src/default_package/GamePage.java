package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePage extends JPanel{

    private Player user;
    private Player cpu1;
    private Player cpu2;
    private Player cpu3;
    private Table table;
    private Color backColor;
    
    public GamePage(String playerName, Color c){
        backColor = c;
        user = new Player(playerName, true);
        cpu1 = new Player("Brint", false);
        cpu2 = new Player("Meekus", false);
        cpu3 = new Player("Rufus", false);
        table = new Table();
        
        deal();
        draw();
      
        setLayout(new GridLayout(5, 0));
        
        table.cardPlayed(new Card(Suit.DIAMONDS, 14));
        table.cardPlayed(new Card(Suit.HEARTS, 10));
        table.cardPlayed(new Card(Suit.HEARTS, 3));
       
        
        
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
        drawPlayer(cpu3, mid);
        mid.add(table);
        drawPlayer(cpu1, mid);
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
            card.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    Card clone = new Card(card.getSuit(), card.getVal());
                    table.cardPlayed(clone);
                    //System.out.println(user.getHand().getContents());
                    user.getHand().removeCard(card);
                    draw();
                    System.out.println("3: " + user.getHand().getContents());
                }
            });
        }
        add(hand);
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
    
    
}
