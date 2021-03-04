import java.util.*;
import java.io.*;
public class ACSL_no2 {

	static String CAPS(String str) { //Capitalize string
		str = str.toUpperCase();
		return str;
	}

	static String longestCommon(String X, String Y, int m, int n) {         
		int[][] LCSuff = new int[m + 1][n + 1]; 
		int len = 0; //store length of longest common substring
		int row = 0, col = 0; 

		//build LCSuff[m+1][n+1] from bottom up
		for (int i = 0; i <= m; i++) { 
			for (int j = 0; j <= n; j++) { 
				if (i == 0 || j == 0) 
					LCSuff[i][j] = 0; 

				else if (X.charAt(i - 1) == Y.charAt(j - 1)) { 
					LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1; 
					if (len < LCSuff[i][j]) { 
						len = LCSuff[i][j]; 
						row = i; 
						col = j; 
					} 
				} 
				else
					LCSuff[i][j] = 0; 
			} 
		} 
		String resultStr = ""; 

		// search diagonally up from the (row, col) cell until LCSuff[row][col] != 0 
		while (LCSuff[row][col] != 0) { 
			resultStr = X.charAt(row - 1) + resultStr;
			--len; 

			// move diagonally up to previous cell 
			row--; 
			col--; 
		} 

		resultStr = resultStr.trim();
		return resultStr; 
	} 

	static String removal(String str1, String str3) {//run for str 1 and then str 2
		str3 = str3 + " ";
		String str4 = str1.replace(str3, "");
		return str4;
	}

	static int spot(String str1, String str3) {
		int spot = str1.indexOf(str3);
		if(spot != 0){
			if ((str1.charAt(spot - 1)) == ' ')
				spot = spot - 1;
		}
		return spot;
	}

	static int process(String strOne, String strTwo) {
		int ADF = 0, spot1, spot2, lens = 0;
		String str3;
		List<String> str1 = new ArrayList<>();
		List<String> str2 = new ArrayList<>();

		str1.add(strOne);
		str2.add(strTwo);

		for(int i = 0; i < str1.size(); i++) {
			if(str1.get(i).equals("") || str2.get(i).equals(""))
				continue;
			
			//Find longest common string
			int m = str1.get(i).length();	
			int n = str2.get(i).length();
			str3 = longestCommon(str1.get(i), str2.get(i), m, n);
			
			lens = str3.length();
			if(lens <= 0)
				break;
			
			//Find location of common string
			spot1 = spot(str1.get(i), str3);
			spot2 = spot(str2.get(i), str3);

			ADF += lens;//add to ADF

			//remove longest common string
			str1.set(i, removal(str1.get(i), str3));
			str2.set(i, removal(str2.get(i), str3));

			//Split left/right
			str1.add(str1.get(i).substring(0, spot1));
			str1.add(str1.get(i).substring(spot1 + lens));
			str2.add(str2.get(i).substring(0, spot2));
			str2.add(str2.get(i).substring(spot2 + lens));
		}
		return ADF;	
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String str1, str2;
		Scanner in = new Scanner(new File("input1.txt"));
		
		for(int i = 0; i < 5; ++i){
			str1 = in.nextLine();
			System.out.println(str1);
			str2 = in.nextLine();
			System.out.println(str2);

			//Capitalize strings
			str1 = CAPS(str1);
			str2 = CAPS(str2);

			//process(str1, str2);
			System.out.println(process(str1, str2));
		}
		in.close();
	}

}
