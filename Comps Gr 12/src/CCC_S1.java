import java.util.*;
public class CCC_S1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String temp;
		int search, tempInt;
		double maxV = 0, v;

		int n = Integer.parseInt(in.nextLine());
		int[] t = new int[n]; 
		int[] d = new int[n];

		//get inputs
		for(int i = 0; i < n; ++i) {
			temp = in.nextLine();
			search = temp.indexOf(' ');

			t[i] = Integer.parseInt(temp.substring(0, search));
			d[i] = Integer.parseInt(temp.substring(search + 1));
		}

		//order array
		for(int i = 0; i < n; ++i) {
			for(int a = i; a > 0; --a) {
				tempInt = t[a];
				if(tempInt < t[a - 1]) {
					t[a] = t[a - 1];
					t[a - 1] = tempInt;
					tempInt = d[a];
					d[a] = d[a - 1];
					d[a - 1] = tempInt;
				}
			}
		}		

		//find velocity
		for(int i = 0; i < n - 1; ++i) {
			v = (d[i + 1] - d[i] * 1.0)/(t[i + 1] - t[i] * 1.0);
			if(Math.abs(v) > Math.abs(maxV))
				maxV = Math.abs(v);
		}
		
		System.out.print(maxV);

		in.close();
	}

}
