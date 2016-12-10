package default_package;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel{

    public MainMenu(Color backColor, JPanel main, CardLayout c){
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        setLayout(gridBag);
        setBackground(backColor);
        
        JLabel welcome = new JLabel("HEARTS", JLabel.CENTER);
        welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        JLabel by = new JLabel("By: Jack Buttimer", JLabel.CENTER);
    
        
        ImageIcon img = new ImageIcon("heart.png", null);
        JLabel l = new JLabel(img, JLabel.CENTER);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout());
        JButton play = new JButton("PLAY");
        JButton instructions = new JButton("HOW TO PLAY");
        JButton highscores = new JButton("HIGHSCORES");
        JButton quit = new JButton("QUIT");
        
        buttons.add(play);
        buttons.add(instructions);
        buttons.add(highscores);
        buttons.add(quit);
        buttons.setBackground(backColor);
        
        con.gridwidth = GridBagConstraints.REMAINDER;
        gridBag.setConstraints(welcome, con);
        gridBag.setConstraints(by, con);
        con.gridheight = 1;
        gridBag.setConstraints(l, con);
        con.gridheight = 1;
        gridBag.setConstraints(buttons, con);
        
        add(welcome);
        add(by);
        add(l);
        add(buttons);
        
        play.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
               c.show(main, "Game Setup");
            }
        });
        
        instructions.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
               c.show(main, "Instructions");
            }
        });
        
        highscores.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                c.show(main, "Highscores");
            }
        });
        
        quit.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
               System.exit(0);
            }
        });
    }
    
}
