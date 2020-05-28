import java.io.*;
import java.nio.Buffer;
import java.time.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/*
Jada Chang
May 2020
My CD Collection Assignment
*/

public class Driver {
    static ArrayList<CD> myCDs = new ArrayList<CD>();

    //prints all CD titles
    static void displayTitles() {
        if (myCDs.isEmpty()) {
            System.out.println("No CDs in database.");
            return;
        }
        System.out.println("\n");
        for (int i = 0; i < myCDs.size(); i++) {
            System.out.print((i + 1) + ") ");
            System.out.println(myCDs.get(i).getTitle());
        }

        System.out.println("\n- End of CDs -\n");
    }

    //prints all CD info
    static void displayInfo(BufferedReader in) throws IOException {
        int input = -1;

        if (myCDs.isEmpty()) {
            System.out.println("No CDs in database.");
            return;
        }
        displayTitles();

        do {
            try {
                System.out.print("Enter the number corresponding to the CD you want to display: ");
                input = Integer.parseInt(in.readLine()) - 1;
                //System.out.println("time = " + myCDs.get(input).getTime());
                System.out.println(myCDs.get(input));

                //TimeUnit.SECONDS.sleep(5);
                //System.out.println("I read input= " + input);
                return;
            } catch (Exception e) {
                //System.out.println("Error in displayInfo()");
                System.out.println("Could not display CD info. Please try again.");
            }
        } while (true);
    }

    //Reads in CD from input file & adds to list of CDs
    static void addCD() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner readFile = null;
        String fileName = "", songTitle = "", input;
        boolean moreCDs = true;
        int skippedSongs = 0;

        do {
            try {
                System.out.print("Enter the file name (ex. input.txt): ");
                fileName = in.readLine();

                //create CD
                readFile = new Scanner(new File(fileName));
                CD thisCD = new CD(readFile.nextLine(), Integer.parseInt(readFile.nextLine()));
                //frequency = Collections.frequency(myCDs, thisCD);
                //System.out.println("Frequency = " + frequency);
                /*if (frequency > 0)
                    thisCD.setTitle(thisCD.getTitle() + " (" + frequency + ")"); //if CD already exists, indicates duplicate #
                */
                //add songs
                System.out.println("\nReading in songs from " + thisCD.getTitle() + "...");
                do {
                    try {
                        songTitle = readFile.nextLine();
                        thisCD.addSong(new Song(songTitle, readFile.nextLine(), readFile.nextLine(), Integer.parseInt(readFile.nextLine()), new Time(readFile.nextLine())));
                    } catch (Exception e) {
                        System.out.println(songTitle + " is an invalid entry");
                        skippedSongs++;
                        //thisCD.setNumSongs(thisCD.getNumSongs() - 1);
                        continue;
                    }
                } while (readFile.hasNext());

                thisCD.setTime(findTime(thisCD));
                thisCD.setNumSongs(thisCD.getSongs().size());
                if (thisCD.getNumSongs() > 0) {
                    myCDs.add(thisCD); //add to list of CDs
                    if (skippedSongs > 0)
                        System.out.println("\n" + thisCD.getTitle() + " added. " + skippedSongs + " songs skipped.");
                    else {
                        System.out.println(thisCD.getTitle() + " successfully added!\n");
                        skippedSongs = 0;
                    }
                } else
                    throw new StringIndexOutOfBoundsException();
                System.out.println("Type anything and press enter to continue. Type 'exit' to return to Menu 1.");
                input = in.readLine();
                if (input.equalsIgnoreCase("exit"))
                    moreCDs = false;
            } catch (Exception e) {
                System.out.println("Error with CD selection. Please try again.");
            }
        } while (moreCDs);
        readFile.close();
    }

    //Reads in CD from input file & adds to list of CDs temp
//    static void tempAddCD(String fileName) throws IOException {
//        Scanner readFile = null;
//        String songTitle = "", input;
//        int frequency, skippedSongs = 0;
//
//        try {
//            //create CD
//            readFile = new Scanner(new File(fileName));
//            CD thisCD = new CD(readFile.nextLine(), Integer.parseInt(readFile.nextLine()));
//            thisCD.setNumSongs(0);
//            //frequency = Collections.frequency(myCDs, thisCD);
//            //System.out.println("Frequency = " + frequency);
//                /*if (frequency > 0)
//                    thisCD.setTitle(thisCD.getTitle() + " (" + frequency + ")"); //if CD already exists, indicates duplicate #
//                */
//            //add songs
//            System.out.println("\nReading in songs from " + thisCD.getTitle() + "...");
//            do {
//                try {
//                    songTitle = readFile.nextLine();
//                    thisCD.addSong(new Song(songTitle, readFile.nextLine(), readFile.nextLine(), Integer.parseInt(readFile.nextLine()), new Time(readFile.nextLine())));
//                    thisCD.setNumSongs(thisCD.getNumSongs() + 1);
//                } catch (Exception e) {
//                    System.out.println(songTitle + " is an invalid entry.");
//                    skippedSongs++;
//                    thisCD.setNumSongs(thisCD.getNumSongs() - 1);
//                    continue;
//                }
//            } while (readFile.hasNextLine());
//
//            thisCD.setTime(findTime(thisCD));
//            thisCD.setNumSongs(thisCD.getSongs().size());
//            if (thisCD.getNumSongs() > 0) {
//                myCDs.add(thisCD); //add to list of CDs
//                if (skippedSongs > 0)
//                    System.out.println("\n" + thisCD.getTitle() + " added. " + skippedSongs + " songs skipped.");
//                else
//                    System.out.println(thisCD.getTitle() + " successfully added!\n");
//            } else
//                throw new StringIndexOutOfBoundsException();
//        } catch (Exception e) {
//            System.out.println("Error in tempAddCD. Could not add CD.");
//        }
//        readFile.close();
//    }

    //Removes a CD
    static void deleteCD(BufferedReader in) throws IOException {
        int input = -1;

        displayTitles();

        do {
            try {
                System.out.print("Enter the number corresponding to the CD you want to delete: ");
                input = Integer.parseInt(in.readLine());

                myCDs.remove(input - 1);
            } catch (Exception e) {
                System.out.println("Error deleting selected CD. Please try again.");
                input = -1;
            }
        } while (input < 0);

        System.out.println("\nSelected CD successfully removed.\n");
    }

    //Copies a CD
    static void copyCD(BufferedReader in) throws IOException {
        int index = -1;
        CD newCD = null;

        displayTitles();

        do {
            try {
                System.out.print("Enter the number corresponding to the CD you want to copy: ");
                index = Integer.parseInt(in.readLine()) - 1;

                newCD = new CD(myCDs.get(index));
                newCD.setTitle(newCD.getTitle() + " *copy*");

                myCDs.add(newCD);
            } catch (Exception e) {
                System.out.println("Error copying selected CD. Please try again.");
            }
        } while (index < 0);

        System.out.println("\n" + newCD.getTitle() + " successfully created\n");
    }

    //Creates a sub CD
    static void subCD(BufferedReader in) throws IOException {
        int CDindex = -1, start = -1, end = -2;
        CD newCD = null;
        ArrayList<Song> theseSongs = new ArrayList<Song>();
        //CopyOnWriteArrayList<Song> theseSongs = null;
        displayTitles();
        if (myCDs.isEmpty())
            return;

        do {
            try {
                System.out.print("Enter the number corresponding to the CD you want to create a sub CD for: ");
                CDindex = Integer.parseInt(in.readLine()) - 1;
                newCD = new CD(myCDs.get(CDindex));
                theseSongs = myCDs.get(CDindex).getSongs(); //same memory location
                //theseSongs.addAll(myCDs.get(CDindex).getSongs()); same memory location
                //theseSongs = (ArrayList<Song>) myCDs.get(CDindex).getSongs().clone(); same memory location
                //theseSongs = new CopyOnWriteArrayList(myCDs.get(CDindex).getSongs()); //same memory location cri

                do {
                    try {
                        displaySongs(myCDs.get(CDindex));
                        /*System.out.println("myCD songs: " + myCDs.get(CDindex).getSongs());
                        System.out.println("These songs: " + theseSongs);
*/
                        System.out.print("Song # to start copying from: ");
                        start = Integer.parseInt(in.readLine()) - 1;
                        if (start < 0)
                            throw new IndexOutOfBoundsException();

                        System.out.print("Song # to stop copying at (this song will be included): ");
                        end = Integer.parseInt(in.readLine()) - 1;
                        if (end <= start || end >= theseSongs.size())
                            throw new IndexOutOfBoundsException();
                        break;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Invalid start/stop entry! Please try again.");
                    }
                } while (true);

                System.out.println("\nCopying selected songs...\n");
                for (int i = start; i < end; i++) {
                    newCD.addSong(theseSongs.get(i));
                    System.out.println(theseSongs.get(i).getTitle() + " added to sub CD");
                }

                newCD.setTitle(newCD.getTitle() + " *sub*");
                newCD.setTime(findTime(newCD));
                myCDs.add(newCD);
                System.out.println("\n" + myCDs.get(myCDs.size() - 1).getTitle() + " successfully created.\n");
                break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                //System.out.println("subCD error");
                System.out.println("Error creating sub CD. Please try again.");
            }
        } while (true);
    }

    //Lists songs in common between 2 CDs
    static void commonSongs(BufferedReader in) throws IOException {
        int input1 = -1, input2 = -1;
        ArrayList<Song> songs1 = null, songs2 = null;

        do {
            displayTitles();
            try {
                System.out.print("Enter the number corresponding to the first CD you would like to check: ");
                input1 = Integer.parseInt(in.readLine()) - 1;
                songs1 = myCDs.get(input1).getSongs();

                System.out.print("Enter the number corresponding to the second CD you would like to check: ");
                input2 = Integer.parseInt(in.readLine()) - 1;
                if (input2 == input1)
                    throw new StringIndexOutOfBoundsException();//can make new exception instead
                songs2 = myCDs.get(input2).getSongs();

                System.out.println("Finding common songs...\n");
                songs1 = new ArrayList<Song>(songs1);
                songs1.retainAll(songs2);

                if (songs1.size() <= 0) {
                    System.out.println("No songs in common.");
                    break;
                } else {
                    for (int i = 0; i < songs1.size(); i++) {
                        System.out.print((i + 1) + ") ");
                        System.out.println(songs1.get(i).getTitle());
                    }
                    System.out.println("\n- End of common songs -\n");
                    break;
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Must enter 2 different CDs! Please try again.");
            } catch (Exception e) {
                System.out.println("Error finding common songs for selected CDs. Please try again.");
            }
        } while (true);
    }

    //rename the CD
    static void rename(BufferedReader in) throws IOException {
        int index = -1;
        String name = null;

        displayTitles();

        do {
            try {
                System.out.print("Enter the number corresponding to the CD you want to rename: ");
                index = Integer.parseInt(in.readLine()) - 1;
                if (index < 0 || index >= myCDs.size())
                    throw new IndexOutOfBoundsException();

                System.out.print("Enter the new title: ");
                name = in.readLine();

                myCDs.get(index).setTitle(name);
                break;
            } catch (Exception e) {
                System.out.println("Error renaming CD. Please try again.");
                index = -1;
            }
        } while (true);

        System.out.println("\n" + myCDs.get(index).getTitle() + " successfully renamed\n");
    }

    //Calculates total time of songs on CD
    static Duration findTime(CD thisCD) {
        Duration thisDuration = Duration.ofMinutes(0);

        for (int i = 0; i < thisCD.getSongs().size(); i++) {
            thisDuration = thisDuration.plusMinutes(thisCD.getSongs().get(i).getTime().getMinutes());
            thisDuration = thisDuration.plusSeconds(thisCD.getSongs().get(i).getTime().getSeconds());
        }

        return thisDuration;
    }

    //prints all songs on a CD - menu 1 ver
    static void displaySongs(CD thisCD) {
        ArrayList<Song> songs = thisCD.getSongs();

        System.out.println("\n");
        for (int i = 0; i < songs.size(); i++) {
            System.out.print((i + 1) + ") ");
            System.out.println(songs.get(i).getTitle());
        }

        System.out.println("\n- End of songs-\n");
    }

    //gets user's CD choice - menu 2
    static int getThisCD(BufferedReader in) throws IOException {
        int index;

        do {
            try {
                displayTitles();
                System.out.print("Enter the number corresponding to the CD you want to access: ");
                index = Integer.parseInt(in.readLine()) - 1;
                if (index < 0 || index >= myCDs.size())
                    throw new IndexOutOfBoundsException();
//                System.out.println("Original CD: " + myCDs.get(index));
//                System.out.println("New CD: " + thisCD);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error! Unable to access selected CD.");
            }
        } while (true);

        return index;
    }

    //prints all songs on a CD - menu 2 ver
    static void displaySongs(int CDindex) {
        ArrayList<Song> songs = myCDs.get(CDindex).getSongs();

        System.out.println("\n");
        for (int i = 0; i < songs.size(); i++) {
            System.out.print((i + 1) + ") ");
            System.out.println(songs.get(i).getTitle());
        }

        System.out.println("\n- End of songs-\n");
    }

    //prints all song info
    static void displayInfo(BufferedReader in, int CDindex) throws IOException {
        int input = -1;

        displaySongs(myCDs.get(CDindex));

        do {
            try {
                System.out.print("Enter the number corresponding to the song you want to display the info for: ");
                input = Integer.parseInt(in.readLine()) - 1;
                System.out.println("\n" + myCDs.get(CDindex).getSongs().get(input));
                return;
            } catch (Exception e) {
                System.out.println("Could not display song info. Please try again.");
            }
        } while (true);
    }

    //add a song
    static void addSong(int CDindex, BufferedReader in) throws IOException {
        Song newSong = null;
        String title = null, genre = null, artist = null;
        int rating, time;

        do {//get title
            try {
                System.out.print("Enter the song title: ");
                title = in.readLine();
                if (title == null || title.equalsIgnoreCase(""))
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid title! Please try again.");
            }
        } while (true);

        do {//get artist
            try {
                System.out.print("Enter the song artist: ");
                artist = in.readLine();
                if (artist == null || artist.equalsIgnoreCase(""))
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid artist! Please try again.");
            }
        } while (true);

        do {//get genre
            try {
                System.out.print("Enter the song genre: ");
                genre = in.readLine();
                if (genre == null || genre.equalsIgnoreCase(""))
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid genre! Please try again.");
            }
        } while (true);

        do {//get rating
            try {
                System.out.print("Enter the song rating: ");
                rating = Integer.parseInt(in.readLine());
                if (rating <= 0 || rating > 5)
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Invalid rating! Please try again.");
            }
        } while (true);

        do {//get time
            try {
                System.out.print("Enter the song time in seconds: ");
                time = Integer.parseInt(in.readLine());
                if (time <= 0)
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Invalid time! Please try again.");
            }
        } while (true);

        newSong = new Song(title, artist, genre, rating, new Time(time));
        myCDs.get(CDindex).getSongs().add(newSong);
        myCDs.get(CDindex).setTime(findTime(myCDs.get(CDindex)));
        myCDs.get(CDindex).setNumSongs(myCDs.get(CDindex).getNumSongs() + 1);
        System.out.println("\n" + myCDs.get(CDindex).getSongs().get(myCDs.get(CDindex).getSongs().size() - 1).getTitle() + " successfully added!\n");
    }

    //delete a song
    static void deleteSong(int CDindex, BufferedReader in) throws IOException {
        int option = -1, index = -1, deleted = 0;
        String title = null;
        ListIterator<Song> iter = null;
        Song temp;
        boolean titleFound = false;

        if (myCDs.get(CDindex).getSongs().isEmpty()) {
            System.out.println("Selected CD has no songs.");
            return;
        }

        //get deletion method
        do {
            try {
                System.out.println("\nHow would you like to delete a song?");
                System.out.println("1) By song number\n2) By song title\n3) Delete the first song on the CD\n4) Delete the last song on the CD");
                System.out.print("Enter the number corresponding to your choice: ");
                option = Integer.parseInt(in.readLine());
                if (option < 1 || option > 4)
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Invalid deletion method. Please try again.");
            }
        } while (true);

        if (option == 1) {
            do {
                displaySongs(myCDs.get(CDindex));
                try {
                    System.out.print("Enter the number corresponding to the song you want to delete: ");
                    index = Integer.parseInt(in.readLine()) - 1;
                    title = myCDs.get(CDindex).getSongs().get(index).getTitle();
                    myCDs.get(CDindex).getSongs().remove(index);
                    myCDs.get(CDindex).setNumSongs(myCDs.get(CDindex).getNumSongs() - 1);
                    myCDs.get(CDindex).setTime(findTime(myCDs.get(CDindex)));
                    System.out.println("\n" + title + " successfully deleted.\n");
                    break;
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Invalid number selection for song deletion. Please try again.");
                }
            } while (true);
        } else if (option == 2) {
            do {
                displaySongs(myCDs.get(CDindex));
                index = 0;
                //try {
                System.out.print("Enter the title of the song you want to delete: ");
                title = in.readLine();
                do {
                    if (index >= myCDs.get(CDindex).getSongs().size()) {
                        titleFound = false;
                        break;
                    }
                    iter = myCDs.get(CDindex).getSongs().listIterator(index);
                    //System.out.println("index  = " + index);
                    do {
                        temp = iter.next();
                        if (temp.getTitle().equalsIgnoreCase(title)) {
                            titleFound = true;
                            break;
                        }
                        index++;
                    } while (iter.hasNext());
                    //System.out.println("Exit at index " + index);
                    if (index >= myCDs.get(CDindex).getSongs().size()) {
                        titleFound = false;
                        break;
                    }
                    title = myCDs.get(CDindex).getSongs().get(index).getTitle();
                    myCDs.get(CDindex).getSongs().remove(index);
                    deleted++;
                    myCDs.get(CDindex).setNumSongs(myCDs.get(CDindex).getNumSongs() - 1);
                    myCDs.get(CDindex).setTime(findTime(myCDs.get(CDindex)));
                } while (titleFound == true);
                System.out.println("\n" + deleted + " copies of " + title + " deleted.\n");
                break;
                // } catch (IndexOutOfBoundsException e) {
                //    System.out.println("Invalid title selection for song deletion. Please try again.");
                //}
            } while (true);
        } else if (option == 3) {
            title = myCDs.get(CDindex).getSongs().get(0).getTitle();
            myCDs.get(CDindex).getSongs().remove(0);
            myCDs.get(CDindex).setNumSongs(myCDs.get(CDindex).getNumSongs() - 1);
            myCDs.get(CDindex).setTime(findTime(myCDs.get(CDindex)));
            System.out.println("\n" + title + " successfully deleted.\n");
        } else if (option == 4) {
            title = myCDs.get(CDindex).getSongs().get(myCDs.get(CDindex).getSongs().size() - 1).getTitle();
            myCDs.get(CDindex).getSongs().remove(myCDs.get(CDindex).getSongs().size() - 1);
            myCDs.get(CDindex).setNumSongs(myCDs.get(CDindex).getNumSongs() - 1);
            myCDs.get(CDindex).setTime(findTime(myCDs.get(CDindex)));
            System.out.println("\n" + title + " successfully deleted.\n");
        }
    }

    //Sorts songs
    static void sortSongs(BufferedReader in, int CDindex) throws IOException {
        int option = -1;

        if (myCDs.get(CDindex).getSongs().isEmpty()) {
            System.out.println("Selected CD has no songs.");
            return;
        }

        //get sorting method
        do {
            try {
                System.out.println("\nHow would you like to sort your songs?");
                System.out.println("1) By title\n2) By artist\n3) By time");
                System.out.print("Enter the number corresponding to your choice: ");
                option = Integer.parseInt(in.readLine());
                if (option < 1 || option > 3)
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Invalid sorting method. Please try again.");
            }
        } while (true);

        if (option == 1) {
            Collections.sort(myCDs.get(CDindex).getSongs());
            System.out.println("\nSorted by title:");
            displaySongs(myCDs.get(CDindex));
        } else if (option == 2) {
            Collections.sort(myCDs.get(CDindex).getSongs(), new sortArtist());
            System.out.println("\nSorted by artist:");
            displaySongs(myCDs.get(CDindex));
        } else if (option == 3) {
            Collections.sort(myCDs.get(CDindex).getSongs(), new sortTime());
            System.out.println("\nSorted by time:");
            displaySongs(myCDs.get(CDindex));
        }
    }

    //prints the menus & allows user to select menu
    static int displayMenu(int menuNum, BufferedReader stdIn) throws IOException {
        int choice = -1;
        if (menuNum == 0) {
            System.out.println("\n----------  MAIN MENU  -----------");
            System.out.println("1) Accessing your list of CDs");
            System.out.println("2) Accessing within a particular CD");
            System.out.println("3) Exit");
            System.out.println("----------------------------------");
        } else if (menuNum == 1) {
            System.out.println("\n---------  SUB-MENU #1  ----------");
            System.out.println("1) Display all of your CDs"); // Just the CD titles, numbered in order
            System.out.println("2) Display info on a particular CD"); // Just the CD titles, numbered in order
            System.out.println("3) Add a CD"); // Adds a CD by reading from input file
            System.out.println("4) Remove a CD");
            System.out.println("5) Copy a CD");
            System.out.println("6) Create a sub-CD");
            System.out.println("7) List songs in common between two CDs");
            System.out.println("8) Rename a CD");
            System.out.println("9) Return back to main menu.");
            System.out.println("----------------------------------");
        } else {
            System.out.println("\n---------  SUB-MENU #2  ----------");
            System.out.println("1) Display all songs (in the last sorted order) ");
            System.out.println("2) Display info on a particular song ");
            System.out.println("3) Add song");
            System.out.println("4) Remove Song (4 options)");
            System.out.println("5) Sort songs (3 options)");
            System.out.println("6) Return back to main menu");
            System.out.println("----------------------------------");
        }

        do {
            try {
                System.out.print("\n\nPlease enter your choice:  ");
                /*String s = stdIn.readLine();
                System.out.println(s);
                 */
                choice = Integer.parseInt(stdIn.readLine());
            } catch (Exception e) {
                System.out.println("Invalid menu selection. Please try again.");
                choice = -1;
                //System.out.println("test catch");
                //TimeUnit.SECONDS.sleep(5);
            }
        } while (choice < 0);
        return choice;
    }

    //binary search method for songs
    static int binarySearch(ArrayList<Song> list, int l, int r, Song x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            if (list.get(mid).equals(x))
                return mid;

            if (list.get(mid).compareTo(x) > 0)
                return binarySearch(list, l, mid - 1, x);

            return binarySearch(list, mid + 1, r, x);
        }
        return -1;
    }

    //driver method
    public static void main(String[] args) throws IOException {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        int mainMenuChoice, subMenuChoice = 0, CDindex = -1;

//        tempAddCD("input1.txt");
//        tempAddCD("input2.txt");
//        tempAddCD("input3.txt");
//        tempAddCD("input4.txt");
//        tempAddCD("input5.txt");
//        tempAddCD("input6.txt");
//        tempAddCD("haley input 1.txt");
//        tempAddCD("haley input 2.txt");
//        tempAddCD("empty.txt");

        do {
            //try {
            mainMenuChoice = displayMenu(0, stdIn);

            if (mainMenuChoice == 1) {
                do {
                    subMenuChoice = displayMenu(1, stdIn);
                    if (subMenuChoice == 1)
                        displayTitles();
                    else if (subMenuChoice == 2)
                        displayInfo(stdIn);
                    else if (subMenuChoice == 3)
                        addCD();
                    else if (subMenuChoice == 4)
                        deleteCD(stdIn);
                    else if (subMenuChoice == 5)
                        copyCD(stdIn);
                    else if (subMenuChoice == 6)
                        subCD(stdIn);
                    else if (subMenuChoice == 7)
                        commonSongs(stdIn);
                    else if (subMenuChoice == 8)
                        rename(stdIn);
                    else if (subMenuChoice == 9)
                        break;
                    else
                        System.out.println("Invalid submenu 1 choice. Please try again.");
//                        }catch (Exception e){
//                            System.out.println("Error in main() submenu 1");
//                            System.out.println("Invalid! Please try again.");
//                        }
                } while (true);
            } else if (mainMenuChoice == 2) {
                if (myCDs.isEmpty()) {
                    System.out.println("Option unavailable when there are no CDs.");
                    continue;
                }
                CDindex = getThisCD(stdIn);
                do {
                    subMenuChoice = displayMenu(2, stdIn);
                    if (subMenuChoice == 1)
                        displaySongs(CDindex);
                    else if (subMenuChoice == 2)
                        displayInfo(stdIn, CDindex);
                    else if (subMenuChoice == 3)
                        addSong(CDindex, stdIn);
                    else if (subMenuChoice == 4)
                        deleteSong(CDindex, stdIn);
                    else if (subMenuChoice == 5)
                        sortSongs(stdIn, CDindex);
                    else if (subMenuChoice == 6)
                        break;
                    else
                        System.out.println("Invalid submenu 2 choice. Please try again.");
                } while (true);
            } else if (mainMenuChoice == 3) {
                stdIn.close();
                System.exit(0);
            } else
                System.out.println("Invalid main menu choice. Please try again.");
//            }catch (Exception e){
//                System.out.println("Error in main()");
//                System.out.println("Invalid! Please try again.");
//            }
        } while (true);
    }
}

/*
Duration methods: http://tutorials.jenkov.com/java-date-time/duration.html
Duration toString format: http://www.java2s.com/Tutorials/Java/java.time/Duration/2960__Duration.toString_.htm
Movie project: https://github.com/JJ-Chang/ICS4U/blob/master/My%20Movies/Movie.java
Duration documentation: https://docs.oracle.com/javase/9/docs/api/javafx/util/Duration.html#Duration-double-
retainAll method: https://www.geeksforgeeks.org/find-common-elements-in-two-arraylists-in-java/
*/