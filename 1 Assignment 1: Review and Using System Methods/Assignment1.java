//Jada Chang
//Feb. 2020
//Assignment 1 - Java Review & Using System Methods

import java.io.*;
import java.util.*;

public class Assignment1 {

	//Question A
	static void A() {
		String name, highestP = null, temp;
		double price = 0, totalV, highestV = 0, totalVI = 0;
		int quantity = 0;
		Scanner in = new Scanner(System.in);
		boolean run, go = true;

		System.out.println("Inventory Management Program\n");

		do {
			//get item name
			do {
				System.out.print("Please enter the name of the next product: ");
				name = in.nextLine();
				if(name.equals(""))
					throw new IllegalArgumentException("Invalid name! Please enter a product.");
				else
					run = false;
			}while(run);
			run = true;

			//get item price
			do {
				try {
					System.out.printf("Please enter the unit price for %s: $", name);
					price = Double.parseDouble(in.nextLine());
					if(price < 0)
						throw new IllegalArgumentException("Invalid price! Please enter a price of at least $0.");
					else
						run = false;
				}catch(NumberFormatException e) {
					System.out.println("Invalid price! Please enter a number.");
				}
			}while(run);
			run = true;

			//get item amount
			do {
				try {
					System.out.printf("How many %s do you have? ", name);
					quantity = Integer.parseInt(in.nextLine());
					if(quantity < 0)
						throw new IllegalArgumentException("Invalid quantity! Please enter a quantity of at least 0.");
					else
						run = false;
				}catch(NumberFormatException e){
					System.out.println("Invalid quantity! Please enter an integer number.");
				}
			}while(run);
			run = true;

			totalV = quantity * price;
			if(totalV > highestV) {
				highestV = totalV;
				highestP = name;
			}
			totalVI += totalV;

			//Display info
			System.out.printf("You have %d %s for a total value of $%,.2f. %n", quantity, name, totalV);

			//more products?
			do {
				System.out.print("Do you have any more products? (y/n): ");
				temp = in.nextLine();
				if(temp.equalsIgnoreCase("n") || temp.equalsIgnoreCase("no")) {
					run = false;
					go = false;
				}else if(temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes"))
					run = false;
				else
					System.out.println("Invalid response. Please try again.");
			}while(run);
		}while(go);

		System.out.printf("%nThe total value of all inventory is $%,.2f.%n", totalVI);
		System.out.printf("The item with the highest total value is %s ($%,.2f).%n", highestP, highestV);
		System.out.println("Thank you for using the Inventory Management Program.");

		in.close();
	}

	//Question B
	@SuppressWarnings("resource")
	static void B() {
		int digits = 0;
		Scanner in = new Scanner(System.in);
		String org;
		String mult;
		StringBuilder org1 = new StringBuilder();
		String mult1 = "";
		boolean run = true;
		char ch;

		while(run) {
			try {
				System.out.print("Enter a number of digits from 1 to 7: ");
				digits = Integer.parseInt(in.nextLine());

				if(digits < 1 || digits > 7)
					throw new IllegalArgumentException();
				else
					run = false;
			}catch(Exception e) {
				System.out.println("Invalid! Please enter a number from 1 to 7.");
			}
		}

		System.out.println("Searching...");
		for(int i = (int)(Math.pow(10, digits - 1)); i < Math.pow(10, digits); i++) {
			org = Integer.toString(i);
			mult = Integer.toString(i * 3);
			for(int a = 0; a < 10; ++a) {
				ch = (char)(a + '0');
				if(org.indexOf(ch) > -1)
					org1.append(ch);
				if(mult.indexOf(ch) > -1)
					mult1 += ch;
			}
			if(org1.toString().contentEquals(mult1))
				System.out.println(org);
		}

		System.out.println("All possible numbers have been found.");
		in.close();
	}

	//Question C
	static void C() {
		String str, palindrome, pLongest = "", fileName;
		int start = 0;
		Scanner in1 = new Scanner(System.in);
		boolean run = true;


		while(run) {
			//get name of input file
			try {
				System.out.print("Enter the name of the file to read from: ");

				fileName = in1.nextLine();
				File temp = new File(fileName);
				Scanner in = new Scanner(temp);

				//get input
				while(in.hasNextLine()){
					str = in.nextLine();
					str = str.toUpperCase();

					System.out.println("Finding the largest palindrome...");

					//check if a palindrome exists that starts with the char at a
					for(int a = 0; a < str.length(); ++a) {
						palindrome = isPalindrome(a, str);
						if(palindrome.length() > pLongest.length()) {
							pLongest = palindrome;
							start = a + 1;
						}
					}

					if(pLongest.contentEquals(""))
						System.out.println("No palindrome found. \n");
					else {
						System.out.println("Largest palindrome: " + pLongest);
						System.out.println("Starting position: " + start);
						System.out.println("Length: " + pLongest.length() + "\n");
					}
					pLongest = "";
					start = 0;
				}
				run = false;
				System.out.println("All palindromes have been found.");

			}catch(FileNotFoundException e){
				System.out.println("ERROR! File not found. Please try again.");
			}
		}
		in1.close();
	}

	//find longest palindrome in specified range
	static String isPalindrome(int a, String str) {
		StringBuilder rev = new StringBuilder(str.length());
		String palindrome = "", part, tempPalindrome = "";

		//check from a to the end of the string for a palindrome
		for(int b = a + 1; b < str.length(); ++b) {
			part = str.substring(a, b);
			rev.replace(0, rev.length(), part);
			rev = rev.reverse();
			if(rev.toString().equals(part))
				tempPalindrome = rev.toString();
			if(tempPalindrome.length() > palindrome.length())
				palindrome = tempPalindrome;
		}

		return palindrome;
	}

	//find longest palindrome in a specified range
	//	static String isPalindrome(int a, String str) {
	//		StringBuilder palindrome = new StringBuilder(str.length());
	//		String part;
	//
	//		//check from a to the end of the string for a palindrome
	//		for(int b = a + 1; b < str.length(); ++b) {
	//			part = str.substring(a, b);
	//			int c = a, d = b;
	//			while(c < d) {
	//				if(str.charAt(a) == str.charAt(b))
	//					palindrome.append(str.charAt(a));
	//			}
	//		}
	//
	//		palindrome = palindrome.append(palindrome.reverse());
	//		part = palindrome.toString();
	//		return part;
	//	}

	//find longest palindrome in specified range
	//	static String isPalindrome(int a, String str) {
	//		int forward = a, backward = str.length() - 1, count = 0;
	//		char forwardChar, backwardChar;
	//		String palindrome = "";
	//		
	//		while (backward >= forward) {
	//	        forwardChar = str.charAt(forward++);
	//	        backwardChar = str.charAt(backward--);
	//	        if (forwardChar == backwardChar) {
	//	        	palindrome = palindrome.substring(0, count) + forwardChar + backwardChar + palindrome.substring(count++);
	//	        }
	//	    }
	//		
	//		System.out.println("palindrome = " + palindrome);//*****
	//		return palindrome;
	//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//A();
		//B();
		//C();

//		try {
//			PrintWriter outFile = new PrintWriter(new FileWriter("out.txt"));
//			outFile.println("part 2");
//			
//			outFile.close();
//		}catch(IOException e) {
//			System.out.println("Writing error!");
//		}
	}

}
