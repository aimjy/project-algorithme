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
public class Dynamic1 {

    /**
     * You can use the class Square Subsequence -> not used
     * 
     * @param s a string   
     * @return The amount of square subsequences in string s is returned. 
     */
    public static int amountOfSquareSubSequences(String s){
        
        if(s == "") {
            System.out.println("Empty string!");
            return 0;
        }
        
        ArrayList<ArrayList<Integer>> temp = findSubsequences(s, 0);
        ArrayList<ArrayList<ArrayList<Integer>>> temp2 = combine(temp, 0);
        System.out.println(s + ":");
        System.out.println("Aantal square subquences: "+ (temp.size() + temp2.size()));
        
        for(int i = 0; i < temp.size(); i++){
            for(int j = 0; j<s.length(); j++){
                if(temp.get(i).contains(new Integer(j))){
                    System.out.print("\u001B[42m" + s.charAt(j) + "\033[0m");
                }else{
                    System.out.print(s.charAt(j));
                }
            }
            System.out.println("\n---------------------------");
        }
        for(int i = 0; i<temp2.size(); i++){
            for(int j = 0; j<s.length(); j++){
                int v = -1;
                for(int k = 0; k<temp2.get(i).size(); k++){
                    for(int l = 0; l<temp2.get(i).get(k).size(); l++){
                        if(j == temp2.get(i).get(k).get(l)) v = l;
                    }
                }
                if(v == -1){
                    System.out.print(s.charAt(j));
                }else{
                    v += 42;
                    System.out.print("\u001B[" + v + "m" + s.charAt(j) + "\033[0m");
                    v = -1;
                }
            }
            System.out.println("\n--------------------------");
        }
       return (temp.size() + temp2.size());
    }
    /*************************
     * geeft een array terug van alle paren van gelijke letters
    **************************/
    private static ArrayList<ArrayList<Integer>> findSubsequences(String a, int index) {
        ArrayList<ArrayList<Integer>> lijst = new ArrayList<ArrayList<Integer>>();
        for(int i = index+1; i<a.length(); i++) {
            if (a.charAt(index) == a.charAt(i)) {
                ArrayList<Integer> tempo = new ArrayList<Integer>();
                tempo.add(index);
                tempo.add(i);
                lijst.add(tempo);
            }
        }
        if(index < a.length() - 1) lijst.addAll(findSubsequences(a,index+1));
        return lijst;
    }
    
    /****************
     * geeft een array terug van de mogelijke alle mogelijke cobminaties van letters samen
     ****************/
    private static ArrayList<ArrayList<ArrayList<Integer>>> combine(ArrayList<ArrayList<Integer>> lijst, int index) {
        ArrayList<ArrayList<ArrayList<Integer>>> combinaties = new ArrayList<ArrayList<ArrayList<Integer>>>();
        for(int i = index+1; i<lijst.size(); i++) {
            if(lijst.get(index).get(0)<lijst.get(i).get(0) &&
               lijst.get(i).get(0) < lijst.get(index).get(1) &&
               lijst.get(index).get(1) < lijst.get(i).get(1)){
                ArrayList<ArrayList<Integer>> xy = new ArrayList<ArrayList<Integer>>();
                xy.add(new ArrayList<Integer>());
                xy.add(new ArrayList<Integer>());
                xy.get(0).add(lijst.get(index).get(0));
                xy.get(0).add(lijst.get(i).get(0));
                xy.get(1).add(lijst.get(index).get(1));
                xy.get(1).add(lijst.get(i).get(1));
                combinaties.add(xy);
                combinaties.addAll(nCombine(xy,lijst));
            }
        }
        if(index + 1 < lijst.size())combinaties.addAll(combine(lijst, index + 1));
        return combinaties;
    }
    
    /*
    * een hulpfunctie om Combine recursief te doen werken 
    */
    private static ArrayList<ArrayList<ArrayList<Integer>>> nCombine(ArrayList<ArrayList<Integer>> xy, ArrayList<ArrayList<Integer>> lijst){
        ArrayList<ArrayList<ArrayList<Integer>>> alleCombinaties = new ArrayList<ArrayList<ArrayList<Integer>>>();
        for(int i = 0; i<lijst.size(); i++){
            int an = xy.get(0).get(xy.get(0).size()-1);
            int b1 = xy.get(1).get(0);
            int bn = xy.get(1).get(xy.get(1).size()-1);
            int c = lijst.get(i).get(0);
            int d = lijst.get(i).get(1);
            if(an < c && c < b1 && bn < d){
                ArrayList<ArrayList<Integer>> nxy = new ArrayList<ArrayList<Integer>>();
                nxy.add(new ArrayList<Integer>());
                nxy.add(new ArrayList<Integer>());
                nxy.get(0).addAll(xy.get(0));
                nxy.get(0).add(c);
                nxy.get(1).addAll(xy.get(1));
                nxy.get(1).add(d);
                alleCombinaties.add(nxy);
                alleCombinaties.addAll(nCombine(nxy, lijst));             
            }
        }
        return alleCombinaties;
        //test2
    }
}
