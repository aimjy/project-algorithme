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
        int totaal = 0;
        for(int i = 0; i<s.length()-1; i++){
            totaal += telSubSequences(s.substring(0,i+1), s.substring(i+1, s.length()));
        }
        System.out.println(s + ": " + totaal);
        return totaal;
    }
    
    private static int telSubSequences(String s1, String s2){
        int[][] aantal = new int[s1.length()][s2.length()];
        
        for(int i = 0; i<s1.length(); i++){
            if(s1.charAt(i) == s2.charAt(0))
                aantal[i][0] = 1;
            if(i>0)
                aantal[i][0] += aantal[i-1][0];
        }
        
        for(int i = 0; i<s1.length(); i++){
            for(int j = 1; j<s2.length(); j++){
                if(i == 0) aantal[i][j] = aantal[i][j-1];
                else{
                    aantal[i][j] = aantal[i-1][j] + aantal[i][j-1] - aantal[i-1][j-1];
                    if(s1.charAt(i) == s2.charAt(j)){
                        aantal[i][j] += aantal[i-1][j-1];
                    }
                }
            }
        }
        
        return aantal[s1.length() - 1][s2.length() - 1];   
        
    }
}
