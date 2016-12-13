package default_package;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverPage extends JPanel{

    private Player[] players;
    private String name;
    private int score;
    private Highscores h;
    
    public GameOverPage(Player[] players, JPanel main, CardLayout c){
        Color color = new Color(208, 22, 22);
        this.players = players;
        name = players[0].getName();
        score = calculateScore();
        h = new Highscores(name, score);
        h.write();
        
        JLabel l = new JLabel("GAME OVER", JLabel.CENTER);
        l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        
        JPanel report_panel = new JPanel();
        
        JLabel userP = new JLabel(players[0].getName() + " : " + players[0].getScore(),
                JLabel.CENTER);
        JLabel cpu1P = new JLabel(players[1].getName() + " : " + players[1].getScore(),
                JLabel.CENTER);
        JLabel cpu2P = new JLabel(players[2].getName() + " : " + players[2].getScore(),
                JLabel.CENTER);
        JLabel cpu3P = new JLabel(players[3].getName() + " : " + players[3].getScore(),
                JLabel.CENTER);
        
        report_panel.add(userP);
        report_panel.add(cpu1P);
        report_panel.add(cpu2P);
        report_panel.add(cpu3P);
        
        report_panel.setBackground(color);
        report_panel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        report_panel.setLayout(new GridLayout(4,0));
        
        
        JLabel n;
        if(won()){
            n = new JLabel("YOU WON", JLabel.CENTER);
        }else{
            n = new JLabel("YOU LOST", JLabel.CENTER);
        }
        n.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        JButton back = new JButton("MAIN MENU");
        
        add(l);
        add(report_panel);
        add(n);
        add(back);
        
        setBackground(color);
        setLayout(new GridLayout(4, 0));
        
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
               c.first(main);
               Game g = new Game();
               JPanel h = new JPanel();
               main.add(h, "Highscores");
               g.createHighscores(h, main, c);
            }
        });
    }
    
    /**
     * Score calculated by summing the opponents' scores, dividing by the user's score, and
     * then multiplying by a factor of 1000.
     * There is a 2x win bonus multiplier
     * @return user's score
     */
    private int calculateScore(){
        int sum = players[1].getScore() + players[2].getScore() + players[3].getScore();
        double score = ((double) sum) / players[0].getScore();
        
        if(won()){
            score*=2;
        }
            
        return (int)(score*1000);
    }
    
    private boolean won(){
        return (players[0].getScore() < players[1].getScore() && 
                players[0].getScore() < players[2].getScore() &&
                players[0].getScore() < players[3].getScore());
    }
       
    
}
