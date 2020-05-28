/*
Jada Chang
May 2020
My CD Collection Assignment
*/

public class Time {
    private int minutes;
    private int seconds;

    //constructor for strings
    Time(String str) {
        this.minutes = Integer.parseInt(str.substring(0, str.indexOf(":")));
        this.seconds = Integer.parseInt(str.substring(str.indexOf(":") + 1));
    }

    //constructor for seconds
    Time(int s) {
        if (s > 60) {
            minutes = s / 60;
            seconds = s % 60;
        } else if (s == 60){
            minutes = 1;
            seconds = 0;
        } else{
            minutes = 0;
            seconds = s;
        }
    }

    //getter methods
    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    //Override toString
    @Override
    public String toString() {
        return (minutes + " minutes, " + seconds + " seconds");
    }

    //compare to
    public int compareTo(Time t){
        return ((this.minutes - t.minutes) + (this.seconds - t.seconds));
    }
}
