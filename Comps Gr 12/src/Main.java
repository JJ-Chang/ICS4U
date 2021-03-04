import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int r, c, search, here = 0, x = 0, outx, outy;
		String temp;
		boolean exit = false, changed = false;
		ArrayList<Integer> factors = new ArrayList<Integer>();

		r = Integer.parseInt(in.readLine());
		c = Integer.parseInt(in.readLine());

		int[][] grid = new int[r][c];
		outx = r;
		outy = c;

		//fill grid
		for(int row = 0; row < r; ++row) {
			temp = in.readLine();
			for(int col = 0; col < c; ++col) {
				search = temp.indexOf(' ', here);
				try {
					grid[row][col] = Integer.parseInt(temp.substring(here, search));
				}catch(StringIndexOutOfBoundsException e){
					grid[row][col] = Integer.parseInt(temp.substring(here));
				}finally {
					here = search + 1;
				}
			}
		}
		in.close();

		//jump through
		r = 0;
		c = 0;
		do {
			x = grid[r][c];
			for(int a = 1; a <= x; ++a) {//find all factors
				if(x % a == 0) {
					factors.add(a);
				}
			}

			for(int a = 0; a <= factors.size() / 2; ++a) {
				for(int z = factors.size() - 1; z > factors.size() / 2; --z) {
					if(factors.get(a) * factors.get(z) == x && a <= outx && z <= outy) {
						r = a;
						c = z;
						changed = true;
					}
				}
			}

			if(r == outx && c == outy)
				exit = true;
			else if(changed == false) {
				break;
			}
			changed = false;
		}while(exit == false);

		if(exit == true)
			System.out.print("yes");
		else
			System.out.print("no");

		in.close();
	}

}
