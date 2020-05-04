import java.util.*;

public class PartB {

    static int fib(int n) {//q1
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }

    static int multiply(int a, int b) {//q2
        if (b == 0)
            return 0;
        else if (b <= 1)
            return a;
        return a + multiply(a, b - 1);
    }

    static String repeat(char ch, int n) {//q3
        if (n <= 1)
            return Character.toString(ch);
        return ch + repeat(ch, n - 1);
    }

    static String reverse(String s) {//q4
        if (s.length() <= 1)
            return Character.toString(s.charAt(0));
        return s.charAt(s.length() - 1) + "-" + reverse(s.substring(0, s.length() - 1));
    }

    static int count(String s, char c) {
        if (s.charAt(0) == c && s.length() > 1)
            return 1 + count(s.substring(1), c);
        else if(s.charAt(0) != c && s.length() > 1)
            return count(s.substring(1), c);
        else if(s.charAt(0) == c)
            return 1;
        else
            return 0;
    }

    //I really don't understand how this one works
    static int rectangle(int n){//q6
        if(n <= 2)
            return n;
        return rectangle(n-1) + rectangle(n-2);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //System.out.println(fib(Integer.parseInt(in.nextLine())));//q1
        //System.out.println(multiply(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine())));//q2
        //System.out.println(repeat(in.nextLine().charAt(0), Integer.parseInt(in.nextLine())));//q3
        //System.out.println(reverse(in.nextLine()));//q4
        //System.out.println(count(in.nextLine(), in.nextLine().charAt(0)));//q5
        //System.out.println(rectangle(Integer.parseInt(in.nextLine())));//q6

        in.close();
    }
}
