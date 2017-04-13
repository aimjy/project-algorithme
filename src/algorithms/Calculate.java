/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import dataset.items.Movie;
import dataset.items.Rating;
import dataset.items.User;
import datastructures.ComparableSimpleEntry;
import datastructures.FixedSizedPriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Leen De Baets
 */
public class Calculate {

    
    public Calculate() {
    }
    
    /**
     * @param a One movie
     * @param relatedMovies an array of other movies
     * @return The minimum of the distances from movie a to the movies in relatedMovies is returned. The distance is calculated content based.
     */
    public static int distanceToRelatedMoviesContentBased(Movie a, ArrayList<Movie> relatedMovies) {
        int min_distance = Integer.MAX_VALUE;
        int checker = a.getAmountOfSquareSubSequences();
        int factor = 1;
        for(int i = 0; i<relatedMovies.size(); i++) {
            factor*=relatedMovies.get(i).getAmountOfSquareSubSequences();
        }
        for(int i = 0; i<relatedMovies.size(); i++) {
            int temp = factor/relatedMovies.get(i).getAmountOfSquareSubSequences();
            int afstand = temp*Math.abs(checker-relatedMovies.get(i).getAmountOfSquareSubSequences());
            min_distance = (afstand<min_distance)?afstand:min_distance;
        }
        return min_distance;
    }

    
    public static double distanceBetweenTwoMovies(ArrayList<Rating> movie1, ArrayList<Rating> movie2, String type) throws Exception{
        Object[] ratingArrays = ratingToArray(movie1, movie2);
        if(type.equals("cosine")){
            return cosineDistance(ratingArrays);
        } else if(type.equals("euclidean")) {
            return euclideanDistance(ratingArrays);
        } else {
            throw new Exception("Type of distance is not defined");
        }
        
        
    }
     
    /**
     * 
     * @param a a movie
     * @param similarToA a fixed sized priority queue containing the movies that are similar to a movie a.
     * @param ratingsIndexedByMovie the rating of all movies stored in a hashmap
     * @return a hashmap containing the rating of this movie for the users that didn't rate this movie a before
     * @throws Exception 
     */
    public static HashMap<User, Double> ratingBasedOnSimilarMovies(Movie a, FixedSizedPriorityQueue similarToA, HashMap<Integer,ArrayList<Rating>> ratingsIndexedByMovie) throws Exception{

        HashMap<User, Double> ratingsMovie = new HashMap<>();
        HashMap<User, Integer> amountUsersRatedMovie = new HashMap<>();
        ComparableSimpleEntry[] copySimilarToA = new ComparableSimpleEntry[similarToA.size()];
        similarToA.toArray(copySimilarToA);
        ArrayList<User> usersAlreadyRate = new ArrayList<User>();
        
        for(int i = 0; i< ratingsIndexedByMovie.get(a.getId()).size() ; i++){
            usersAlreadyRate.add(ratingsIndexedByMovie.get(a.getId()).get(i).getUser());
        }
        
        for(int i = 0; i<copySimilarToA.length; i++){
            Movie m = (Movie) copySimilarToA[i].getValue();
            ArrayList<Rating> ratings = ratingsIndexedByMovie.get(m.getId());
            for(Rating r: ratings){
                User u = r.getUser();
                if(!usersAlreadyRate.contains(u)){                      // zie: http://math.stackexchange.com/questions/106700/incremental-averageing
                    double huidigeScore = 0.0;
                    if(ratingsMovie.containsKey(u))
                        huidigeScore = ratingsMovie.get(u);
                    int aantal = 0;
                    if(amountUsersRatedMovie.containsKey(u))
                        aantal = amountUsersRatedMovie.get(u);
                    aantal++;
                    amountUsersRatedMovie.put(u, aantal);
                    double extraScore = r.getRating();
                    double nieuweScore = huidigeScore + (extraScore - huidigeScore)/aantal;
                    ratingsMovie.put(u, nieuweScore);
                }
                
            }
        }
        
        return ratingsMovie;
    }
    
    /**
     * 
     * @param a a movie
     * @param similarToA a fixed sized priority queue containing the movies that are similar to a movie a.
     * @param ratingsIndexedByMovie the rating of all movies stored in a hashmap
     * @return a hashmap containing the weighted rating of this movie for the users that didn't rate this movie a before
     * @throws Exception 
     */
    public static HashMap<User, Double> ratingBasedOnSimilarMoviesWeighted(Movie a, FixedSizedPriorityQueue similarToA, HashMap<Integer,ArrayList<Rating>> ratingsIndexedByMovie) throws Exception{

        HashMap<User, Double> ratingsMovie = new HashMap<>();
        HashMap<User, Double> amountUsersRatedMovie = new HashMap<>();
        ComparableSimpleEntry[] copySimilarToA = new ComparableSimpleEntry[similarToA.size()];
        similarToA.toArray(copySimilarToA);
        ArrayList<User> usersAlreadyRate = new ArrayList<User>();
        
        for(int i = 0; i< ratingsIndexedByMovie.get(a.getId()).size() ; i++){
            usersAlreadyRate.add(ratingsIndexedByMovie.get(a.getId()).get(i).getUser());
        }
        
        for(int i = 0; i<copySimilarToA.length; i++){
            Movie m = (Movie) copySimilarToA[i].getValue();
            ArrayList<Rating> ratings = ratingsIndexedByMovie.get(m.getId());
            for(Rating r: ratings){
                User u = r.getUser();
                if(!usersAlreadyRate.contains(u)){ 
                    double huidigeScore = 0.0;
                    if(ratingsMovie.containsKey(u))
                        huidigeScore = ratingsMovie.get(u);
                    double extraScore = r.getRating();
                    double nieuweScore = huidigeScore + (copySimilarToA.length - i) * extraScore;
                    ratingsMovie.put(u, nieuweScore);
                    double totaal = 0.0;
                    if(amountUsersRatedMovie.containsKey(u))
                        totaal = amountUsersRatedMovie.get(u);
                    totaal += copySimilarToA.length - i;
                    amountUsersRatedMovie.put(u, totaal);
                }
                
            }
        }
        
        for(User u: ratingsMovie.keySet()){
            double Score = ratingsMovie.get(u);
            Score /= amountUsersRatedMovie.get(u);
            ratingsMovie.put(u, Score);
        }
        
        return ratingsMovie;
    }
       
    /**
     * If 
     *      movie1 is rated by user1 with a 4, and it is rated by user2 with a 2;
     *      movie2 is rated by user1 with a 1, and it is rated by user3 with a 4;
     * Then
     *      result[0] = new ArrayList<>([4,2,2.5]);
     *      result[1] = new ArrayList<>([1,2.5,4]);
     *      result[2] = true;
     * 
     * @param movie1 an arraylist containing the ratings for the first movie
     * @param movie2 an arraylist containing the ratings for the second movie
     * @return An object array is returned. The first element is an arraylist of doubles containing the ratings' values for movie one, if necessary with additional values (see example).
     * The second element is an arraylist of doubles containing the ratings' values for movie two, if necessary with additional values ((see example)).
     * The third element is a boolean stating if the movies were rated by the same user or not.
     * 
     */
    
    public static Object[] ratingToArray(ArrayList<Rating> movie1, ArrayList<Rating> movie2){
        boolean inCommonRatings = false;
        ArrayList<Rating> movie2Copy = new ArrayList<Rating>(movie2.size());
        for(Rating r: movie2) movie2Copy.add(r);
        ArrayList<Double> r1 = new ArrayList<>();
        ArrayList<Double> r2 = new ArrayList<>();
                
        for(int i = 0; i<movie1.size(); i++) {
            Rating temp = movie1.get(i);
           int j = 0;
            boolean gevonden = false;
            while(!gevonden && j<movie2Copy.size()) {
                if(temp.getUser() == movie2Copy.get(j).getUser()) {
                    gevonden = true;
                    inCommonRatings = true;
                    r1.add(temp.getRating());
                    r2.add(movie2Copy.get(j).getRating());
                    movie2Copy.remove(j);
                }
                j++;
            }
            
            if(!gevonden) {
                r1.add(temp.getRating());
                r2.add(2.5);
            }
        }
        
        for(int k = 0; k<movie2Copy.size(); k++) {
            r1.add(2.5);
            r2.add(movie2Copy.get(k).getRating());
        }
        
        Object[] result = new Object[3];
        result[0] = r1;
        result[1] = r2;
        result[2] = inCommonRatings;
        return result;        
    }
    
    /**
     * 
     * @param result an object array where the first element is an ArrayList of doubles containing the ratings for movie1, 
     * the second element is an ArrayList of doubles containing the the ratings for movie2,
     * the third element is a boolean stating if movie1 and movie2 are rated by the same user.
     * @return the cosine distance between the two movies
     */
    private static double cosineDistance(Object[] result){
        ArrayList<Double> a1 = (ArrayList<Double>) result[0];
        ArrayList<Double> a2 = (ArrayList<Double>) result[1];
        boolean inCommonRatings = (boolean) result[2];
        if(inCommonRatings) {
            double teller = 0;
            double noemer1 = 0;
            double noemer2 = 0;
            for(int i = 0; i<a1.size(); i++) {
                teller += a1.get(i)*a2.get(i);
                noemer1 += a1.get(i)*a1.get(i);
                noemer2 += a2.get(i)*a2.get(i);
            }
            return 1-teller/Math.sqrt(noemer1)/Math.sqrt(noemer2);
        }else  return Double.POSITIVE_INFINITY;
    }
    
    /**
     * 
     * @param result an object array where the first element is an ArrayList of doubles containing the ratings for movie1, 
     * the second element is an ArrayList of doubles containing the the ratings for movie2,
     * the third element is a boolean stating if movie1 and movie2 are rated by the same user.
     * @return the Euclidean distance between the two movies
     */
    private static double euclideanDistance(Object[] result){
        ArrayList<Double> a1 = (ArrayList<Double>) result[0];
        ArrayList<Double> a2 = (ArrayList<Double>) result[1];
        boolean inCommonRatings = (boolean) result[2];
        
        if(inCommonRatings){
            double sumOfSquare = 0.0;
            double distance;
            for(int i = 0; i<a1.size(); i++){
                sumOfSquare += (a2.get(i) - a1.get(i))*(a2.get(i) - a1.get(i));
            }
            distance = Math.sqrt(sumOfSquare);
            return distance;
        }
        
        return Double.POSITIVE_INFINITY;
        
    }

    
    
}
