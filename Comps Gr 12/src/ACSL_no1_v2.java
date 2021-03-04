//Jada Chang

import java.util.*;
import java.io.*;

public class ACSL_no1_v2 {
	public static String result = "";
	
	static void left_digits(int rP, String strN){
		int digit, NewDigit;
		
		for(int i = 0; i < rP; i++) {
			digit = Character.getNumericValue(strN.charAt(i));
			NewDigit = digit + Character.getNumericValue(strN.charAt(rP));

			result += Integer.toString(NewDigit);
		}
	}
	
	static void right_digits(int rP, String strN){
		int digit, newDigit;
		
		for(int i = rP + 1; i < strN.length(); i++) {
			digit = Character.getNumericValue(strN.charAt(i));
			newDigit = Math.abs(digit - Character.getNumericValue(strN.charAt(rP)));

			result += Integer.toString(newDigit);
		}
	}
	
	static void replace_P(String temp1) {
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
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//Read in from file--------------------------------------------------------------
		Scanner in = new Scanner(new File("input.txt"));
		int intP, lastSpace;
		String strN, temp1, temp2;

		while(in.hasNextLine()){
			temp1 = in.nextLine();
			lastSpace = temp1.lastIndexOf(" ") - temp1.indexOf(" ") + 1;
			
			temp2 = temp1.substring(temp1.indexOf(" ") + lastSpace, temp1.length());
			temp1 = temp1.substring(0, temp1.indexOf(" "));

			strN = temp1;
			intP = Integer.parseInt(temp2);
			int rP = strN.length() - intP;
			
			left_digits(rP, strN);
			replace_P(temp1);
			right_digits(rP, strN);

			System.out.println(result);
			result = "";
		}
		in.close();
	}

}
