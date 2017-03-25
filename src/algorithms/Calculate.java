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
        if (true) {
            throw new UnsupportedOperationException("Implement distanceToRelatedMovies in class Calculate.");
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
        
        //TODO: Delete exception and implement here
        if (true){
            throw new UnsupportedOperationException("Implement ratingBasedOnSimilarMovies in class Calculate.");
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
        
        //TODO: Delete exception and implement here
        if (true){
            throw new UnsupportedOperationException("Implement ratingBasedOnSimilarMoviesWeighted in class Calculate.");
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
        
        ArrayList<Double> r1 = new ArrayList<>();
        ArrayList<Double> r2 = new ArrayList<>();
                
        //TODO: Delete exception and implement here
        if (true){
            throw new UnsupportedOperationException("Implement ratingToArray in class Calculate.");
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
        
        //TODO: Delete exception and implement here
        if (true){
            throw new UnsupportedOperationException("Implement cosineDistance in class Calculate.");
        }
        return 0.0;
        
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
        
        //TODO: Delete exception and implement here
        if (true){
            throw new UnsupportedOperationException("Implement euclideanDistance in class Calculate.");
        }
        
        return 0.0;
        
    }

    
    
}
