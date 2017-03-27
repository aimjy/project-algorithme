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
import java.util.List;

/**
 *
 * @author Leen De Baets
 */
public class Select {

    public Select() {
    }
    
    /**
     * 
     * @param ratingsUser all the ratings of one single user
     * @return the movies containing the highest rating
     */
    public static ArrayList<Movie> maxRating(ArrayList<Rating> ratingsUser){
        double max_rating = 0.0;
        ArrayList<Movie> maxRatings = new ArrayList<>();
        for(int i = 0; i<ratingsUser.size(); i++) {
            double test = ratingsUser.get(i).getRating();
            max_rating = (test>max_rating)?test:max_rating;
        }
        for(int i = 0; i<ratingsUser.size(); i++) {
            if(ratingsUser.get(i).getRating() == max_rating) maxRatings.add(ratingsUser.get(i).getMovie());
        }
        return maxRatings;
    }
    /**
     * 
     * @param likedMovies the movies of a user that were liked the most 
     * @param ratedMovies all movies rated by a user
     * @param allMovies all the movies present in the database
     * @param amountOfRelatedMovies the amount of related movies you want
     * @return a FixedSizedPriorityQueue containing the related movies
     */
    public static FixedSizedPriorityQueue relatedMoviesContentBased(ArrayList<Movie> likedMovies, ArrayList<Movie> ratedMovies, ArrayList<Movie> allMovies, int amountOfRelatedMovies){
        FixedSizedPriorityQueue fspq = new FixedSizedPriorityQueue(amountOfRelatedMovies);
        if(amountOfRelatedMovies<=likedMovies.size()) {
            for(int i = 0; i<amountOfRelatedMovies; i++) {
                ComparableSimpleEntry temp = new ComparableSimpleEntry((double)likedMovies.get(i).getAmountOfSquareSubSequences(),likedMovies.get(i));
                fspq.add(temp);
            }
        }
        else {
            if(amountOfRelatedMovies<=ratedMovies.size()) {
                for(int i = 0; i<likedMovies.size(); i++) {
                ComparableSimpleEntry temp = new ComparableSimpleEntry((double)likedMovies.get(i).getAmountOfSquareSubSequences(),likedMovies.get(i));
                fspq.add(temp);
                }
                int toegevoegd = likedMovies.size();
                int index = 0;
                while(toegevoegd<amountOfRelatedMovies) {
                    ComparableSimpleEntry test = new ComparableSimpleEntry((double)ratedMovies.get(index).getAmountOfSquareSubSequences(),ratedMovies.get(index));
                    if(!fspq.contains(test)) {
                        fspq.add(test);
                        toegevoegd++;
                    }
                    index++;
                }
            }
            else {
                if(amountOfRelatedMovies<=allMovies.size()) {
                    for(int i = 0; i<ratedMovies.size(); i++) {
                        ComparableSimpleEntry tijdelijk = new ComparableSimpleEntry((double)ratedMovies.get(i).getAmountOfSquareSubSequences(),ratedMovies.get(i));
                        fspq.add(tijdelijk);
                    }
                    int toegevoegd = ratedMovies.size();
                    int index = 0;
                    while(toegevoegd<amountOfRelatedMovies) {
                        ComparableSimpleEntry temp = new ComparableSimpleEntry((double)allMovies.get(index).getAmountOfSquareSubSequences(),allMovies.get(index));
                        if(!fspq.contains(temp)) {
                            fspq.add(temp);
                            toegevoegd++;
                        }
                        index++;
                    }
                }
                else {
                    System.out.println("Er zijn niet zoveel films. Kies een kleiner aantal!");
                    return null;
                }
            }
        }
        return fspq;
    }
    
    /**
     * 
     * @param likedMovie a movie where you want to find the similar movies for
     * @param allMovies all the movies present in the database
     * @param ratingsIndexedByMovie the ratings for all the movies present in the database, given in a Hashmap where the key is the movie id and the value contains a list with the corresponding ratings
     * @param amountOfRelatedMovies the amount of related movies you want
     * @return a FixedSizedPriorityQueue containing the related movies
     * @throws Exception 
     */
    public static FixedSizedPriorityQueue relatedMoviesCollaborative(Movie likedMovie, ArrayList<Movie> allMovies, HashMap<Integer,ArrayList<Rating>> ratingsIndexedByMovie, int amountOfRelatedMovies) throws Exception{
        FixedSizedPriorityQueue fspq = new FixedSizedPriorityQueue(amountOfRelatedMovies);
               
        //TODO: Delete exception and implement here
        if (true){
            throw new UnsupportedOperationException("Implement relatedMoviesContentBased in class Select.");
        }
        return fspq;
    }

    
    
    
}
