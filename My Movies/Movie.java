/*
Jada Chang
April 2020
My Favourite Movies Assignment
*/
import java.util.*;

//Movie Class
public class Movie implements Comparable<Movie>{
    private double rating;
    private String title;
    private String genre;
    private int ranking;
    static int numMovies = 0;

    //constructor with rating, title, & genre
    public Movie(double rating, String title, String genre) {
        this.rating = rating;
        this.title = title;
        this.genre = genre;
        numMovies++;
    }

    //constructor with only title
    public Movie(String title){
        this.title = title;
        numMovies++;
    }

    //getter methods
    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getRanking(){
        return ranking;
    }

    public static int getNumMovies(){
        return numMovies;
    }

    //setter methods
    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    //Override toString
    @Override
    public String toString() {
        return String.format("%nMovie Title: %s%nGenre: %s%nRating: %.2f%%", title, genre, rating);
    }//rating rounded to 2 decimals for legibility

    //Order by rating (Default sorting method)
    public int compareTo(Movie m) {
        return -Double.compare(this.rating, m.rating);
    }
}

//Sort in alpha order by title
class sortTitle implements Comparator<Movie> {
    public int compare(Movie m1, Movie m2) {
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    }
}

//Sort in alpha order by genre
class sortGenre implements Comparator<Movie> {
    public int compare(Movie m1, Movie m2) {
        return m1.getGenre().compareToIgnoreCase(m2.getGenre());
    }
}