import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ControlMyRectangles {
    static String findLargestRectangle(ArrayList<MyRectangle> r) {
        Collections.sort(r);
        return r.get(r.size() - 1).toString();
    }

    public static void main(String[] args) {
        MyRectangle r1 = new MyRectangle(10, 10, 100, 100);
        MyRectangle r2 = new MyRectangle(5, 10, 20, 30);
        MyRectangle r3 = new MyRectangle(200, 547, 10, 20);
        MyRectangle r4 = new MyRectangle(-500, -20, 30, 47);
        ArrayList<MyRectangle> r = new ArrayList<MyRectangle>();
        r.add(r1);
        r.add(r2);
        r.add(r3);
        r.add(r4);

        System.out.printf("r1: %s%nr2: %s%nr3: %s%nr4: %s%n%n", r1, r2, r3, r4);
        System.out.println("r1 height = " + r1.getHeight());
        System.out.println("r2 width = " + r2.getWidth());
        System.out.printf("There are %d rectangles.%n%n", MyRectangle.getNumRectangles());

        System.out.println("r3 height = " + r3.getHeight());
        r3.setHeight(45);
        System.out.println("r3 height = " + r3.getHeight());
        System.out.println("r4 height = " + r4.getWidth());
        r4.setWidth(500);
        System.out.println("r4 height = " + r4.getWidth());

        System.out.println("\nLargest rectangle: " + findLargestRectangle(r));
        System.out.println("Rectangles smallest to largest: " + r);
    }
}
