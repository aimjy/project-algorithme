/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package squareSubSequences;

import java.util.ArrayList;

/**
 *
 * @author Leen De Baets
 */
public class Dynamic2 {

    public Dynamic2() {
    }
    
    /**
    * @param s a string   
    * @return The amount of square subsequences in string s is returned. 
    */
    public static int amountOfSquareSubSequences(String s){
        //TODO: Delete exception and implement here
        ArrayList<String> yolo = subSequences(s, 0, 1, new ArrayList<Integer>());
        for(int i = 0; i<yolo.size(); i++)System.out.println(yolo.get(i));
        
        return yolo.size();
    }
    
    public static ArrayList<String> subSequences(String s, int x, int y, ArrayList<Integer> ban){
        ArrayList<String> opl = new ArrayList<String>();
        if(y < s.length() &&  !ban.contains(new Integer(x))){
            for(int i = y; i<s.length(); i++){
                if(s.charAt(x) == s.charAt(i)){
                    opl.add(s.substring(x,x+1));
                    ban.add(i);
                    ArrayList<String> temp = subSequences(s, x+1, i+1, ban);
                    for(String a: temp) opl.add(s.substring(x,x+1).concat(a));
                    ban.remove(new Integer(i));
                }
            }
            opl.addAll(subSequences(s,x+1,y+1,ban));
        }
        return opl;
        //git-test
    }
}
