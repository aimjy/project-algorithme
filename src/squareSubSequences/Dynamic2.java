/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package squareSubsequences;

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
        ArrayList<String> yolo = subSequences(s, 0);
        for(int i = 0; i<yolo.size(); i++)System.out.println(yolo.get(i));
        
        return yolo.size();
    }
    
    public static ArrayList<String> subSequences(String s, int index){
        ArrayList<String> opl = new ArrayList<String>();
        for(int i = index + 1; i<s.length(); i++){
            if(s.charAt(0) == s.charAt(i)){
                opl.add(s.substring(index,index+1));
                System.out.println("yolo");
                System.out.println(s.substring(index, index + 1));
                //opl.add(s.substring(index,index + 1).concat(subSequences(s.substring(index, s.length()), 0)));
                ArrayList<String> temp = subSequences(s.substring(index + 1, s.length() + 1),0);
                for(int j = 0; j<temp.size(); j++){
                    opl.add(s.substring(index, index + 1).concat(temp.get(j)));
                    System.out.println("temp: " + temp.get(j));
                }
            }
        }
        //opl.addAll(subSequences(s.substring(index + 1, s.length()), 0));
        
        
        
        return opl;
    }
}
