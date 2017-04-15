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
        Movie[] temp = new Movie[amountOfRelatedMovies];
        boolean niet_vol = true;
        for(int i = 0; i<allMovies.size(); i++) {
            Movie vgl = allMovies.get(i);
            if(!ratedMovies.contains(vgl)) {
                if(niet_vol) {
                    int j = 0;
                    boolean toegevoegd = false;
                    while(j<amountOfRelatedMovies && !toegevoegd) {
                        if(temp[j] == null) {
                            toegevoegd = true;
                            temp[j] = vgl;
                        }
                        j++;
                    }
                    niet_vol = !(j == amountOfRelatedMovies);
                }
                else {
                    int max_index = 0;
                    for(int k = 1; k<amountOfRelatedMovies; k++) {
                        if (Calculate.distanceToRelatedMoviesContentBased(temp[k], likedMovies) > Calculate.distanceToRelatedMoviesContentBased(temp[max_index], likedMovies)) {
                            max_index = k;
                        }
                    }
                    if(Calculate.distanceToRelatedMoviesContentBased(vgl, likedMovies)< Calculate.distanceToRelatedMoviesContentBased(temp[max_index],likedMovies)) {
                        temp[max_index] = vgl;
                    }
                }
            }
        }
        for(int l = 0; l<amountOfRelatedMovies; l++) {
                Movie film = temp[l];
                ComparableSimpleEntry voeg_toe = new ComparableSimpleEntry((double)Calculate.distanceToRelatedMoviesContentBased(film, likedMovies),film);
                fspq.add(voeg_toe);
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
        ArrayList<Rating> ratingsLikedMovie = ratingsIndexedByMovie.get(likedMovie.getId());
        
        for(int i = 0; i<allMovies.size(); i++) {
            Movie film = allMovies.get(i);
            if(film != likedMovie) {
            ArrayList<Rating> tempo = ratingsIndexedByMovie.get(film.getId());
            double afstand = Calculate.distanceBetweenTwoMovies(ratingsLikedMovie, tempo, "euclidean");
            ComparableSimpleEntry temp = new ComparableSimpleEntry(afstand,film);
            fspq.add(temp);
            }
        }
        
        return fspq;
    }
}
