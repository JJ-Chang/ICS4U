import java.util.*;
public class CCC_J1 {

	static int find(int n) {
		int ways = 0, left, right;
		
		for(int i = 0; i < 5; ++i) {
			right = i;
			left = n - right;
			if(left < 6 && right < 6 && left >= right)
				ways++;
		}
		
		return ways;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n, ways = 1;
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();

		if(n != 10 && n != 1)
			ways = find(n);
			
		System.out.print(ways);
		in.close();
	}

}
