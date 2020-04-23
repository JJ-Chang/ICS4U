import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//mainclassandmypathfindingmethod
public class Main
{
	private static long bestFrom(long[][] grid, long[][] dp, int x, int y)
	{
		if(x < 0 || y >= grid[0].length)
			return -1;
		if(dp[x][y] != -1)
			return dp[x][y];
		long costRight = bestFrom(grid, dp, x - 1, y);
		long costUp = bestFrom(grid, dp, x, y + 1);
		if(costRight != -1 && (costRight < costUp || costUp == -1))
			dp[x][y] = costRight + grid[x][y];
		else
			dp[x][y] = costUp + grid[x][y];
		return dp[x][y];
	}
//catchingerrorsandsuch
	public static void main(String[] argv) throws IOException
	{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		int numTests = Integer.parseInt(sc.readLine());
		long[][] grid, dp;
		System.out.println("Finding the Cheapest Routes:");
		for(int gridNum = 1; gridNum <= numTests; gridNum++)
		{
			int rows, cols;
			rows = Integer.parseInt(sc.readLine());
			cols = Integer.parseInt(sc.readLine());
			grid = new long[rows][cols];
			dp = new long[rows][cols];
			for(int i = 0; i < rows; i++)
			{
				String[] tokens = sc.readLine().split("\\s");
				for(int j = 0; j < cols; j++)
				{
					grid[i][j] = Long.parseLong(tokens[j]);
					dp[i][j] = -1;
				}
			}
			dp[0][cols - 1] = grid[0][cols - 1];
			boolean[] direction = new boolean[rows + cols];
			long[] cheapestRoute = new long[rows + cols];
			long cheapestCost = bestFrom(grid, dp, rows - 1, 0);
			System.out.println("Grid #" + gridNum + ":");
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < cols; j++)
					System.out.print(grid[i][j] + " ");
				System.out.println();
			}
			int x = rows - 1, y = 0, i = 0;
			while(!(x == 0 && y == cols -1))
			{
				cheapestRoute[i] = grid[x][y];
				if(y + 1 < cols && dp[x][y] == grid[x][y] + dp[x][y + 1])
				{
					direction[i] = true;
					y++;
				}
				else
				{
					x--;
				}
				i++;
			}
			System.out.print("Cheapest Route:");
			for(int j = 0; j < i; j++)
				System.out.print(" " + cheapestRoute[j]);
			System.out.print(" " + grid[0][cols - 1]);
			System.out.println();
			System.out.print("Direction:");
			for(int j = 0; j < i; j++)
				System.out.print(" " + (direction[j] ? "EAST" : "NORTH"));
			System.out.println();
			System.out.println("Cheapest Cost: $" + cheapestCost);
		}
		System.out.println("Program is Complete");
	}
}
//imdoneeeeeee

