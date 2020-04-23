/*Jada Chang
//March 13, 2020
Assignment #2: Cheapest Route*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class CheapestRoute {
    static int gridNum = 0, cheapestRouteNum, cheapestRouteValue = 0, rows, cols;

    //access file
    static BufferedReader getFile() throws IOException {
        Scanner scan = new Scanner(System.in);
        String name;

        do {
            try {
                System.out.print("Enter file name: ");
                name = scan.nextLine();
                File file = new File(name);
                BufferedReader bread = new BufferedReader(new FileReader(file));
                gridNum = Integer.parseInt(bread.readLine());
                return bread;
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            }
        } while (true);
    }

    //access grid
    static int[][] read(BufferedReader bread) throws IOException {
        int count = 0;
        String[] temp;

        //initialize grid
        try {
            rows = Integer.parseInt(bread.readLine());
            cols = Integer.parseInt(bread.readLine());
            int[][] grid = new int[rows][cols];

            //assign grid values
            for (int i = 0; i < grid.length; i++) {
                temp = bread.readLine().split(" ");
                for (String s : temp) {
                    grid[i][count++] = Integer.parseInt(s);
                }
                count = 0;
            }
            return grid;
        } catch (NullPointerException e) {
            System.out.println("Invalid input. Unable to access # of rows/columns.");
        }
        return null;
    }

    //find all routes
    static void findRoutes(List<Integer> currentRoute, int[][] grid, int row, int col, List<String> routes) {
        int maxR = rows - 1, maxC = cols - 1;
        int right = 0;
        int up = 0;
        //if ((row <= 0) && (col >= maxC)) {//doesn't go in
        // if(true){

        //add current step to current route
        currentRoute.add(grid[row][col]);
        //System.out.println(currentRoute);//test
        //checks to see if it has reached ending point in graph
        if (row == 0 && col == maxC) {
            routes.add(storeRoute(currentRoute));
            return;
        }

        //nika's edit: not sure if this is allowed or not, but i am comparing the values to try and 
        //direct the code to either going right or up 
        if (row <= maxR && col + 1 <= maxC)
            right = grid[row][col + 1];

        if (row - 1 >= 0 && row - 1 < maxR && col < maxC)
            up = grid[row - 1][col];

        //move right

        //if (row < maxR && col + 1 < maxC) {
        //nika's edit:
        if (row <= 0) {
            right = up = 0;
            findRoutes(currentRoute, grid, row, col + 1, routes);
        } else if (row <= maxR && col + 1 < maxC && right <= up) {
            right = up = 0;
            findRoutes(currentRoute, grid, row, col + 1, routes);
        }

        //move up
        //if (row - 1 >= 0 && row - 1 < maxR && col < maxC) {
        //nika's edit: base
        if (row - 1 >= 0 && row - 1 < maxR && col < maxC && up <= right) {
            right = up = 0;
            findRoutes(currentRoute, grid, row - 1, col, routes);
        }

        //remove current step from current route
        currentRoute.remove(currentRoute.size() - 1);
    }

    //store current route
    static String storeRoute(List<Integer> currentRoute) {//doesn't go in
        //System.out.println("TEST in storeRoute " + currentRoute);

        StringBuilder temp = new StringBuilder();
        for (int i : currentRoute) {
            temp.append(" ").append(i);
            //System.out.println("Current value = " + i);//test
        }
        return temp.toString();
    }

    //find cheapest route
    static void cheapestR(List<String> routes) {
        int cost = 0;
        String[] nums;

        for (int i = 0; i < routes.size(); i++) {
            nums = routes.get(i).split(" ");
            for (String s : nums) {
                if(s.equals(""))
                    continue;
                //System.out.println("cost = " + cost);//test
            }
            if (cheapestRouteValue == 0) {
                cheapestRouteNum = i;
            } else if (cost < cheapestRouteValue) {
                cheapestRouteNum = i;
            }
        }

        /*cost = 0;//reset cost
        String[] costs = routes.get(cheapestRouteNum).split(" ");
        for (int i = 0; i < costs.length; ++i) {
            if(costs[i].equals(""))
                continue;
            cheapestRouteValue += Integer.parseInt(costs[i]);
            System.out.println(cheapestRouteValue);//test
        }*/
    }

    //get directions for cheapest route
    static String getDirections(int[][] grid, List<String> routes) {
        String directions = "";
        int row = rows - 1, col = 0;
        String[] currentRoute;

        currentRoute = routes.get(cheapestRouteNum).split(" ");
        for (String s : currentRoute) {
            if (s.equals("")) {
                continue;
            } else if (row > 0 && Integer.parseInt(s) == grid[row - 1][col]) {
                directions += "NORTH ";
                cheapestRouteValue += grid[row--][col];
                //System.out.println(directions);//test
            } else if (col < cols && Integer.parseInt(s) == grid[row][col + 1]) {
                directions += "EAST ";
                cheapestRouteValue += grid[row][col++];
                //System.out.println(directions);//test
            }
        }
        return directions;
    }

    public static void main(String[] args) throws IOException {
        int currentGrid = 1;
        BufferedReader bread = getFile();

        System.out.println("Finding the Cheapest Routes:");

        do {
            int[][] grid = read(bread);
            System.out.printf("Grid #%d%n", currentGrid++);
            System.out.println(Arrays.deepToString(grid).replace("], ", "\n")
                    .replace(", ", " ").replace("[[", "")
                    .replace("]]", "").replace("[", ""));
            List<String> routes = new ArrayList<>();

            List<Integer> currentRoute = new ArrayList<>();
            //System.out.println(grid.length-1);
            findRoutes(currentRoute, grid, grid.length - 1, 0, routes);
            cheapestR(routes);

            /*if (routes == null) {//test; doesn't go in
                System.out.println("null routes");//doesn't go in
                System.out.println("it's null");
            }
            for (String route : routes) System.out.println(route + "tested routes");//test; doesn't print anything
            */

            //Display cheapest route
            System.out.println("Cheapest route: " + routes.get(cheapestRouteNum));
            System.out.println("Direction: " + getDirections(grid, routes));
            System.out.print("Cheapest cost: $");
            System.out.println(cheapestRouteValue);

            //reset global vars
            cheapestRouteValue = 0;
            cheapestRouteNum = 0;
        } while (currentGrid <= gridNum);
        System.out.println("Program is complete.");
    }
}