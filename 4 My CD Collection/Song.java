/*
Jada Chang
May 2020
My CD Collection Assignment
*/

import java.util.Comparator;

//Song class
public class Song implements Comparable<Song>{
    private String title;
    private String artist;
    private String genre;
    private int rating;
    private Time time;

    //constructor
    public Song(String title, String artist, String genre, int rating, Time time) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.rating = rating;
        this.time = time;
    }

    //getter methods
    public Time getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    //Override equals
    public boolean equals(Song s) {
        if (this.getTitle().equalsIgnoreCase(s.getTitle()))
            return true;
        else
            return false;
    }

    //Override toString
    public String toString() {
        return ("Title: " + title + "\nArtist: " + artist + "\nGenre: " + genre + "\nRating: " + rating + "\nTime: " + time);
    }

    //Order by title
    public int compareTo(Song song) {
        return this.getTitle().compareToIgnoreCase(song.getTitle());
    }
}

//Sort in alpha order by artist
class sortArtist implements Comparator<Song> {
    public int compare(Song s1, Song s2) {
        return s1.getArtist().compareToIgnoreCase(s2.getArtist());
    }
}

//Sort smallest to largest by time
class sortTime implements Comparator<Song> {
    public int compare(Song s1, Song s2) {
        return s1.getTime().compareTo(s2.getTime());
    }
}