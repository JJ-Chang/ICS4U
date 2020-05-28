/*
Jada Chang
May 2020
My CD Collection Assignment
*/

import java.util.*;
import java.time.*;

//CD Class
public class CD {
    private String title;
    private int numSongs;
    private ArrayList<Song> songs = new ArrayList<Song>();
    private Duration time;

    //Constructor with CD title & number of songs
    public CD(String title, int numSongs) {
        this.title = title;
        this.numSongs = numSongs;
    }

    //Constructor with CD
    public CD(CD cd){
        this.title = cd.title;
        this.numSongs = cd.numSongs;
        this.time = cd.time;
    }

    //setter methods
    public void addSong(Song newSong) {
        songs.add(newSong);
    }

    public void setTime(Duration time){
        this.time = time;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setNumSongs(int numSongs){
        this.numSongs = numSongs;
    }

    //getter methods
    public String getTitle() {
        return title;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public int getNumSongs(){
        return numSongs;
    }

    public Duration getTime(){
        return time;
    }

    //Override toString
    @Override
    public String toString() {
        String printTime = time.toString();
        int findT = printTime.indexOf('T');
        int findH = printTime.indexOf('H');
        int findM = printTime.indexOf('M');
        int findS = printTime.indexOf('S');

        if(findH > -1) {
            printTime = printTime.substring(findT + 1, findH) + " hours, " + printTime.substring(findH + 1, findM) + " minutes, " + printTime.substring(findM + 1, findS) + " seconds";
            //printTime = printTime.substring(printTime.indexOf("PT") + 2);
        }else if(findM > -1) {
            printTime = printTime.substring(findH + 1, findM) + " minutes, " + printTime.substring(findM + 1, findS) + " seconds";
            printTime = printTime.substring(printTime.indexOf("PT") + 2);
        }else if(findS > -1) {
            printTime = printTime.substring(findM + 1, findS) + " seconds";
            printTime = printTime.substring(printTime.indexOf("PT") + 2);
        }else
            printTime = "0 seconds";

        return ("CD Title: " + title + "\n# Songs: " + numSongs + "\nTime: " + printTime);
    }

    //Override equals
    public boolean equals(CD CD){
        if(this.getTitle().equalsIgnoreCase(CD.getTitle()))
            return true;
        else
            return false;
    }

    //Order by title
    public int compareTo(CD CD) {
        return this.getTitle().compareToIgnoreCase(CD.getTitle());
    }

}
//Info about duration class: https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html