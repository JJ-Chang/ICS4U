/*
Jada Chang
April 2020
My Favourite Movies Assignment
*/

import java.io.*;
import java.util.*;

//Main Class
public class Main {
    private static Scanner in0 = new Scanner(System.in);//to get user inputs

    /* Get list of movies from input file
    parameters: List to store movies
    returns list filled with movies from file */
    static ArrayList<Movie> getMovies(ArrayList<Movie> movieList) throws IOException {
        String file = null;
        BufferedReader in = null;
        Movie currentMovie;
        String thisLine, thisTitle, thisGenre;
        double thisRating;
        int percent, lastSpace, ranking = 1;

        do {
            System.out.print("Enter the input file name (eg.'input.txt'): ");
            file = in0.nextLine();
            try {
                in = new BufferedReader(new FileReader(file));
            } catch (Exception e) {
                System.out.println("Error! File not found. Please try again.");
            }
        } while (in == null);

        //add movies to list
        do {
            thisLine = in.readLine();
            if (thisLine != null)
                thisLine = thisLine.trim();//trim leading/trailing spaces
            else
                break;//exit at EOF
            percent = thisLine.indexOf('%');
            if (percent < 1)
                continue;//Skip line if no rating or % sign is first character

            lastSpace = thisLine.lastIndexOf(' ');
            thisRating = Double.parseDouble(thisLine.substring(0, percent));
            if (thisRating < 0)
                continue; //skip line if rating is < 0%
            thisRating = Math.round(thisRating * 100.0) / 100.0;//round to 2 decimals for legibility & limit large decimals for efficiency
            thisGenre = thisLine.substring(lastSpace + 1);
            thisTitle = thisLine.substring(percent + 2, lastSpace);

            if (validGenre(thisGenre) > 0) {
                movieList.add(new Movie(thisRating, thisTitle, thisGenre));
                ranking++;
            }
        } while (thisLine != null);

        in.close();
        return movieList;
    }

    /*checks if genre is valid
    parameters: genre from current line of input file
    returns 1 if valid genre, -1 if invalid genre*/
    static int validGenre(String genre) {
        String[] genres = {"action", "adventure", "comedy", "drama", "fantasy", "horror", "romance", "thriller",
                "animation", "documentary"};

        for (int i = 0; i < 10; i++) {
            if (genre.equalsIgnoreCase(genres[i]))
                return 1;
        }

        return -1;
    }

    /*
    checks if movie is valid using iterator
    parameters: movie list, current movie entered by user
    returns 1 if valid movie, -1 if invalid movie
    */
    static int validMovie(ArrayList<Movie> movies, String query){
        Iterator<Movie> iter = movies.iterator();
        while(iter.hasNext()){
            if(iter.next().getTitle().equalsIgnoreCase(query))
                return 1;
        }

        return -1;
    }

//    /*
//    checks if movie is valid using binary search
//    parameters: movie list, current movie entered by user
//    returns 1 if valid movie, -1 if invalid movie
//    */
//    static int validMovie(ArrayList<Movie> movieList, String title) {
//        if (binarySearch(movieList, title, 0, movieList.size() - 1) > -1)
//            return 1;
//        else
//            return -1;
//    }

//    //binary search algorithm
//    public static int binarySearch(ArrayList<Movie> movies, String key, int left, int right) {
//        if (left > right)
//            return -left - 1;
//        int mid = (left + right) / 2;
//        if (movies.get(mid).getTitle().equalsIgnoreCase(key))
//            return mid;
//        if (movies.get(mid).getTitle().compareToIgnoreCase(key) < 0)
//            return binarySearch(movies, key, mid + 1, right);
//        else
//            return binarySearch(movies, key, left, mid - 1);
//    }

    /*
    sets rankings of all movies according to rating
    parameters: list of movies ordered by rating
    */
    static ArrayList<Movie> setRankings(ArrayList<Movie> movies){
        Iterator<Movie> iter = movies.iterator();
        int count = 1;

        while (iter.hasNext()) {
            iter.next().setRanking(count);
            count++;
        }
       return movies;
    }

    /*prints movies according to user input of title
    parameters: movieList sorted by order requested by user
    */
    static void printMovies(ArrayList<Movie> movies, Movie query) {
        Iterator<Movie> iter = movies.iterator();
        int count = 0;
        while (iter.hasNext()) {
            if (iter.next().getTitle().equalsIgnoreCase(query.getTitle())) {
                System.out.println(movies.get(count));
                System.out.printf("Ranking: %d out of %d%n", movies.get(count).getRanking(), Movie.getNumMovies());
            }
            count++;
        }
        System.out.println("All results have been found.");
    }

    /*prints movies according to user input of genre
    parameters: movieList sorted by order requested by user*/
    static void printMovies(ArrayList<Movie> movies, String query) {
        Iterator<Movie> iter = movies.iterator();
        int count = 0;
        while (iter.hasNext()) {
            if (iter.next().getGenre().equalsIgnoreCase(query)) {
                System.out.println(movies.get(count));
                System.out.printf("Ranking: %d out of %d%n", movies.get(count).getRanking(), Movie.getNumMovies());
            }
            count++;
        }
        System.out.println("\n\nAll results have been found.");
    }

    //driver method
    public static void main(String[] args) throws IOException {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        String query, sortBy, type;
        Movie key = null;

        getMovies(movieList);//get list of movies

        //create 3 lists based on each sort type
        Collections.sort(movieList);
        ArrayList<Movie> moviesByRating = new ArrayList<Movie>(movieList);
        moviesByRating = setRankings(moviesByRating);//set all rankings

        Collections.sort(moviesByRating, new sortGenre());
        ArrayList<Movie> moviesByGenre = new ArrayList<Movie>(moviesByRating);

        Collections.sort(moviesByRating, new sortTitle());
        ArrayList<Movie> moviesByTitle = new ArrayList<Movie>(moviesByRating);

        System.out.println("\nEnter 'exit' at any point to quit.\n");

        do {
            do {//determine if query is a movie or genre
                System.out.print("\nWould you like to search a movie or a genre? ");
                type = in0.nextLine();

                if (type.trim().equalsIgnoreCase("movie") || type.trim().equalsIgnoreCase("genre"))
                    break;
                else if (type.trim().equalsIgnoreCase("exit"))
                    break;
                else
                    System.out.println("Invalid! Please try again.");
            } while (true);

            //ask for sorting type and provide results according to query type
            if (type.trim().equalsIgnoreCase("exit")) {
                break;
            } else if (type.trim().equalsIgnoreCase("movie")) {//outputs if query is a movie
                do {
                    System.out.print("Enter a movie title: ");
                    query = in0.nextLine();
                    if (query.trim().equalsIgnoreCase("exit"))
                        break;
                    key = new Movie(query);

                    if (validMovie(movieList, query) > -1)
                        break;
                    else
                        System.out.println("No results found. Please try again.");
                } while (true);
                if (query.trim().equalsIgnoreCase("exit"))
                    break;

                do {
                    System.out.print("How would you like results to be sorted (rating or genre)? ");
                    sortBy = in0.nextLine();
                    if (sortBy.trim().equalsIgnoreCase("exit"))
                        break;
                    else if (sortBy.trim().equalsIgnoreCase("rating")) {
                        printMovies(moviesByRating, key);
                        break;
                    } else if (sortBy.trim().equalsIgnoreCase("genre")) {
                        printMovies(moviesByGenre, key);
                        break;
                    } else
                        System.out.println("Invalid. Please try again.");
                } while (true);

                if (sortBy.trim().equalsIgnoreCase("exit"))
                    break;
            } else { //outputs if query is a genre
                do {
                    System.out.print("Enter a genre: ");
                    query = in0.nextLine();
                    if (query.trim().equalsIgnoreCase("exit"))
                        break;
                    if (validGenre(query) > -1)
                        break;
                    else
                        System.out.println("No results found. Please try again.");
                } while (true);
                if (query.trim().equalsIgnoreCase("exit"))
                    break;

                do {
                    System.out.print("How would you like results to be sorted (rating or title)? ");
                    sortBy = in0.nextLine();
                    if (sortBy.trim().equalsIgnoreCase("exit"))
                        break;
                    else if (sortBy.trim().equalsIgnoreCase("rating")) {
                        printMovies(moviesByRating, query);
                        break;
                    } else if (sortBy.trim().equalsIgnoreCase("title")) {
                        printMovies(moviesByTitle, query);
                        break;
                    } else
                        System.out.println("Invalid. Please try again.");
                } while (true);
                if (sortBy.trim().equalsIgnoreCase("exit"))
                    break;
            }
        } while (!query.equalsIgnoreCase("exit"));
        System.out.println("\n\nThank you for using the My Favourite Movies database.");

        in0.close();
    }
}
