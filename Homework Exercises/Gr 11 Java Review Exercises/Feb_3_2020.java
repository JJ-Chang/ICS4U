import java.util.*;
public class Feb_3_2020 {

	static void q1a() {
		double a = 0, b = 0;
		Scanner in  = new Scanner(System.in);

		System.out.print("enter side 1: ");
		a = Integer.parseInt(in.nextLine());
		System.out.print("enter side 2: ");
		b = Integer.parseInt(in.nextLine());

		System.out.print("c = " + Math.round(Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))));
		in.close();
	}

	static void q1b() {
		double a = 0, b = 0;
		Scanner in  = new Scanner(System.in);

		do {
			try {
				System.out.print("enter side 1: ");
				a = Integer.parseInt(in.nextLine());
			}catch(Exception NumberFormatException) {
				System.out.println("Invalid! Please enter again.");
			}
		}while(a == 0);

		do {
			try {
				System.out.print("enter side 2: ");
				b = Integer.parseInt(in.nextLine());
			}catch(Exception NumberFormatException) {
				System.out.println("Invalid! Please enter again.");
			}
		}while(b == 0);

		System.out.print("c = " + Math.round(Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))));
		in.close();
	}

	static void q2a(){
		int avg = 0;
		Scanner in  = new Scanner(System.in);

		for(int i = 0; i < 5; i++) {
			System.out.print("Enter a number: ");
			avg += Integer.parseInt(in.nextLine());
		}

		avg = avg/5;
		System.out.println("average = " + avg);
		in.close();
	}

	static void q2b() {
		int avg = 0, lowest;
		String temp;
		Scanner in  = new Scanner(System.in);

		System.out.print("Enter a number: ");
		temp = in.nextLine();
		avg += Integer.parseInt(temp);
		lowest = Integer.parseInt(temp);

		for(int i = 0; i < 4; i++) {
			System.out.print("Enter a number: ");
			temp = in.nextLine();
			avg += Integer.parseInt(temp);

			if(Integer.parseInt(temp) < lowest)
				lowest = Integer.parseInt(temp);
		}

		avg = avg/5;
		System.out.println("average = " + avg);
		System.out.println("lowest of entered #s = " + lowest);
		in.close();
	}

	static void q3() {
		Scanner in = new Scanner(System.in);
		String str;
		char ch;
		int vow = 0, con = 0;

		System.out.print("Enter a word: ");
		str = in.nextLine();

		for(int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);

			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
				vow++;
			else
				con++;
		}

		System.out.println("# vowels = " + vow);
		System.out.println("# consonants = " + con);

		in.close();
	}

	static int MultipleThree(int[] nums) {
		int mults = 0;

		for(int i = 0; i < nums.length; i++) {
			if(nums[i] % 3 == 0)
				mults++;
		}

		return mults;
	}

	static int Difference(int[][] nums) {
		int dif, lowest, highest;

		lowest = nums[0][0];
		highest = nums[0][0];
		for(int row = 0; row < nums.length; row++) {
			for(int col = 0; col < nums[row].length; col++) {
				if(nums[row][col] < lowest)
					lowest = nums[row][col];
				else if(nums[row][col] > highest)
					highest = nums[row][col];
			}
		}

		dif = highest - lowest;

		return dif;
	}

	static int[] insertElement(int[] nums, int ins){
		int[] num = new int[nums.length + 1];
		int temp = ins;

		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] < ins)
				num[i] = nums[i];
			else if(nums[i] > ins) {
				num[i] = temp;
				temp = nums[i];
			}else
				num[i] = ins;
		}

		if(temp > ins)
			num[nums.length] = temp;
		else
			num[nums.length] = ins;

		return num;
	}

	static String encryptMessage(String str, int pos) {
		char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		int it;
		char temp;
		String enc = "";

		str = str.toUpperCase();

		for(int a = 0; a < str.length(); ++a) {
			temp = str.charAt(a);
			for(int i = 0; i < 26; ++i) {
				if(alpha[i] == temp) {
					try {
						enc += alpha[i + pos];
					}catch(Exception ArrayIndexOutOfBoundsException){
						it = Math.abs(-25 + pos + 24);
						str = str.replace(alpha[i], alpha[it]);
					}break;
				}else if(temp == ' ') {
					enc += ' ';
					break;
				}
			}
		}

		return enc;
	}

	static String decryptMessage(String str, int pos) {
		char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		int it;
		char temp;
		String dec = "";

		str = str.toUpperCase();

		for(int a = 0; a < str.length(); ++a) {
			temp = str.charAt(a);
			for(int i = 0; i < 26; ++i) {
				if(alpha[i] == temp) {
					try {
						dec += alpha[i - pos];
					}catch(Exception ArrayIndexOutOfBoundsException){
						it = Math.abs(26 - pos);
						str = str.replace(alpha[i], alpha[it]);
					}break;
				}else if(temp == ' ') {
					dec += ' ';
					break;
				}
			}
		}

		return dec;
	}

	static String commonLetters(String str1, String str2) {
		String common="";
		char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int x = -1, y = -1;
		
		str1 = str1.toLowerCase();
		str2= str2.toLowerCase();
		for(int i = 0; i < 26; ++i) {
			x = str1.indexOf(alpha[i]);
			y = str2.indexOf(alpha[i]);
			
			if(x > -1 && y > -1)
				common += alpha[i];
			x = -1;
			y = -1;
		}
		
		return common;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*int[] nums= {1, 5, 18, 321, 4076, 97, 83, 12, 0, 6, 63, 69};
		int[][] vals = {{1, 5, 18, 321, 4076}, {97, 83, 12, 0, 6, 63, 69}};
		int[] originalNumbers = {10, 20, 30, 40, 50, 60, 70};
		int key = 3;
		String a = "Good Luck on this question", b = "Plan your solution carefully", 
				c = "apple!", d = "kiwi", e="ABCD", f="calculate";*/

		//q1a();
		//q1b();
		//q2a();
		//q2b();
		//q3();
		//q4a
		//System.out.println(MultipleThree(nums) + " multiples of 3");
		//q4b
		//System.out.println("Difference = " + Difference(vals));

		//More challenging:
		//q1
		/*int[] newNumbers = insertElement(originalNumbers, 45);
		System.out.println(Arrays.toString(newNumbers));*/
		//q2a
		//System.out.print("The encrypted message is " + encryptMessage("GOOD LUCK", key));
		//q2b
		//System.out.print("The decrypted message is " + decryptMessage("JRRG OXFN", key));
		//q3
		/*System.out.println(commonLetters(a, b));
		System.out.println(commonLetters(c, d));
		System.out.println(commonLetters(e, f));*/
	}

}
