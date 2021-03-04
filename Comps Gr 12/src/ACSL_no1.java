//Jada Chang

import java.util.*;
import java.io.*;

public class ACSL_no1 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//Read in from file--------------------------------------------------------------
		Scanner in = new Scanner(new File("input.txt"));
		int intP, digit, newDigit, lastSpace;
		String result = "", strN, temp1, temp2;

		while(in.hasNextLine()){
			temp1 = in.nextLine();
			lastSpace = temp1.lastIndexOf(" ") - temp1.indexOf(" ") + 1;
			
			temp2 = temp1.substring(temp1.indexOf(" ") + lastSpace, temp1.length());
			temp1 = temp1.substring(0, temp1.indexOf(" "));

			strN = temp1;
			intP = Integer.parseInt(temp2);
			int rP = strN.length() - intP;
			
			//Replace left digits---------------------------------------------------------------
			for(int i = 0; i < rP; i++) {
				digit = Character.getNumericValue(strN.charAt(i));
				newDigit = digit + Character.getNumericValue(strN.charAt(rP));

				result += Integer.toString(newDigit);
			}

			//Replace Pth digit-----------------------------------------------------------------

			//Find number of unique prime factors of N
			long n = Long.parseLong(temp1), primes = 0;

			if(n % 2 == 0) {
				primes++; //prime factor 2
				while(n % 2 == 0)
					n /= 2;
			}

			for (int i = 3; i <= Math.sqrt(n); i+= 2) 
			{ 
				if(n % i == 0) {
					primes++; //add 1 to the # of unique primes
					while (n % i == 0)
						n /= i; 
				}
			} 

			//if n is still a prime # greater than 2
			if(n > 2)
				primes++;

			//Replace Pth character with # of unique prime factors in N
			result += Long.toString(primes);

			//Replace right digits---------------------------------------------------------------
			for(int i = rP + 1; i < strN.length(); i++) {
				digit = Character.getNumericValue(strN.charAt(i));
				newDigit = Math.abs(digit - Character.getNumericValue(strN.charAt(rP)));

				result += Integer.toString(newDigit);
			}

			System.out.println(result);
			result = "";
		}
		in.close();
	}

}
