/*
Jada Chang
August 6, 2020
Making your own class - exercises
*/

public class MyRectangle implements Comparable<MyRectangle>{
    private int left;
    private int bottom;
    private int width;
    private int height;
    static int numRectangles = 0;

    public MyRectangle(int left, int bottom, int width, int height) {//Constructor
        this.left = left < 0 ? 0 : left;
        this.bottom = bottom < 0 ? 0 : bottom;
        this.width = width < 0 ? 0 : width;
        this.height = height < 0 ? 0 : height;
        numRectangles++;
    }

    //Getter methods
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static int getNumRectangles() {
        return numRectangles;
    }

    //Setter methods
    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    //Override methods
    @Override
    public String toString() {
        return String.format("base: (%d,%d) w:%d h:%d", left, bottom, width, height);
    }

    public int compareTo(MyRectangle r){
        int result = this.area() < r.area() ? -1 : this.area() == r.area() ? 0 : 1;
        return result;
    }

    //Other methods
    public int area() {//returns area of rectangle
        return width * height;
    }
}
