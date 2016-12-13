package default_package;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Highscores {

    private String inName;
    private int inScore;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();
    
    public Highscores(String name, int score){
        inName = name;
        inScore = score;
    }
    

    public void write(){
        for(String s: read()){
            System.out.println("HERE");
            parseLine(s);
        }
        
        int pos = findPlacement();
        System.out.println(pos);
        
        if(names.size() == 0 || (names.size()<=10 && pos == -1)){
            names.add(inName);
            scores.add(inScore);
        }else if(pos == -1){
            return;
        }else{
            names.add(pos, inName);
            scores.add(pos, inScore);
        }
        System.out.println(pos);
        System.out.println(inScore);

        
        FileWriter fw;
        try{
            fw = new FileWriter("highscores.txt");
            for(int i = 0; i<=10 && i<names.size(); i++){
                fw.write(names.get(i) + " - " + scores.get(i) + "\n");
                System.out.println("writing");
            }
            fw.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
    private ArrayList<String> read(){
        ArrayList<String> hScores = new ArrayList<>();
        String line = "";
        try{
            BufferedReader in = new BufferedReader(new FileReader("highscores.txt"));
            int i = 0;
            line = in.readLine();
            System.out.println(line);
            while(line != null){
                if(i<=10){
                    hScores.add(line);
                }
                i++;
                line = in.readLine();
            } 
            in.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        } 
        System.out.println(hScores);
        return hScores;
    }
    
    
    private void parseLine(String s){
        String name = "";
        for(int i = 0; Character.isLetter(s.charAt(i)); i++){
            name += s.charAt(i);
        }
        String score = "";
        int index2 = s.indexOf('-');
        for(int i = (index2+2); i< s.length() && Character.isDigit(s.charAt(i)) ; i++){
            score += s.charAt(i);
        }
        names.add(name);
        scores.add(Integer.valueOf(score));
    }
    
    private int findPlacement(){
        int val = -1;
        int it = 0;
        for(Integer i: scores){
            if(inScore > i){
                val = it;
                break;
            }
            it++;
        }
        
        return val;
    }
    
}
