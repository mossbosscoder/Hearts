package default_package;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Game implements Runnable {
     
    private final JFrame frame = new JFrame("HEARTS");
    private final Color backColor = new Color(208, 22, 22);
    private String playerName = null;
    
    
    public void run() {
        frame.setLocation(0, 0);
         
        CardLayout c1 = new CardLayout();
        
        final JPanel main = new JPanel();
        main.setLayout(c1);
        
        final MainMenu startScreen = new MainMenu(backColor, main, c1);
        final JPanel instructions = new JPanel();
        final JPanel highscores = new JPanel();
        final JPanel gameSetUp = new JPanel();    
        
        main.add(startScreen, "Start Screen");
        main.add(instructions, "Instructions");
        main.add(highscores, "Highscores");
        main.add(gameSetUp, "Game Setup");
        
        c1.show(main, "Start Screen");
            
        createGameSetUp(gameSetUp, main, c1);
        createInstructions(instructions, main, c1);
        createHighscores(highscores, main, c1);
        
        frame.add(main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(540, 700);
    }

    
    private void createGameSetUp(JPanel gsu, JPanel main, CardLayout c){
        
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        JLabel l = new JLabel("Enter your Name:", JLabel.CENTER);
        l.setFont(f);
        JTextField t = new JTextField();
        t.setHorizontalAlignment(JTextField.CENTER);
        t.setFont(f);
        t.setBackground(Color.GRAY);
        JButton b = new JButton("SUBMIT");
        b.setFont(f);
        gsu.add(l);
        gsu.add(t);
        gsu.add(b);
        
        gsu.setBackground(backColor);
        gsu.setLayout(new GridLayout(3, 0));
        b.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                if(t.getText() == null || t.getText().equals("")){
                    l.setText("<html>Enter your Name:<br>(You must enter something)</html>");
                    c.show(main, "Game Setup");
                } else{
                    playerName = t.getText();
                    GamePage g = new GamePage(playerName, backColor, main, c);
                    main.add(g, "Game");
                    c.show(main, "Game");
                    g.begin();
                }
            }
        });
    }
    
    
    private void createInstructions(JPanel i, JPanel main, CardLayout c) {
        
        i.setLayout(new BoxLayout(i, BoxLayout.PAGE_AXIS));
        
        JLabel howTo = new JLabel("<html><body><h1>How to Play Hearts</h1><h2>Objective</h2>"
                + "Hearts is a card game played with a standard 52 card deck. The objective of"
                + " the game is to score the fewest points. When one player hits or exceeds 100"
                + " points, the game ends, and the player with the fewest points wins. <h2>"
                + " Scoring </h2>During the hand, each player counts the number of hearts he"
                + " has taken and the queen of spades. Each heart is worth <b>1 point</b>, "
                + "and the queen is worth <b>13 points</b> to total <b>26 points per hand</b>."
                + " <br> <br><h2>The Play</h2>Hearts is "
                + " a four player game. At the beginning of each round, 13 cards are dealt to each"
                + " player. The player with the \"2 of Clubs\" leads with that card and play "
                + "contunues counterclockwise. Each player must follow suit if possible. If the "
                + "player is out of card is the led suit, the player can choose to discard one"
                + " of his other cards. The queen of hearts or any heart, however, may not be"
                + " played in the first trick. <br><br>The winner of the trick is the one who"
                + " plays the highest card of the led suit. The winner leads for the next trick. "
                + "A heart cannot be lead unless the queen of spades or another heart has already"
                + " been played. The queen may be led at any point.<h2>Controls</h2> To play a "
                + "card in your hand click on its icon at the bottom of the screen. You"
                + " will need to choose a card that can be played by the rules of the game."
                + " Remember, always follow suit if possible. <br><br></body></html> ", JLabel.CENTER);
        
        howTo.setBackground(backColor);
        JButton back = new JButton("BACK");
        
        i.add(howTo);
        i.add(back);
        
        i.setBackground(backColor);
        
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
               c.first(main);
            }
        });
    }
    
    public void createHighscores(JPanel h, JPanel main, CardLayout c) {
        
        h.setLayout(new BoxLayout(h, BoxLayout.PAGE_AXIS));
        String line;
        JPanel p = new JPanel();
        try{
            BufferedReader in = new BufferedReader(new FileReader("highscores.txt"));
            int i = 1;
            
            while(i<=10){
                JLabel l;
                line = in.readLine();
                if(line != null){
                    l = new JLabel(i + " : " + line);
                }
                else{
                    l = new JLabel(i + " : " + "N/A");   
                }
                l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                l.setHorizontalAlignment(JLabel.CENTER);
                p.add(l);
                p.setLayout(new GridLayout(i, 0));
                p.setBackground(backColor);
                i++;
            } 
            in.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        } 
        JButton back = new JButton("BACK");
        
        h.add(p);
        h.add(back);
        
        h.setBackground(backColor);
        
        
        
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
               c.first(main);
            }
        });
    }
    
    
 /*   private void createGameOver(JPanel g, JPanel main, CardLayout c) {
        JLabel l = new JLabel("GAME OVER");
        JButton back = new JButton("BACK");
        
        g.add(l);
        g.add(back);
        
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
               c.first(main);
            }
        });
    } */
    
    /*
     * Main method run to start and run the game Initializes the GUI elements
     * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
     * this in the final submission of your game.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }

}
