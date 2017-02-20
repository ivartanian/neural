package com.company;

/**
 * Created by super on 2/20/17.
 */
public class MainCoursera {

    public MainCoursera() {
        run();
    }

    public static void main(String[] args) {
        new MainCoursera();
    }

    public void run() {

        double x1 = 2104; double y1 = 460;
        double x2 = 1416; double y2 = 232;
        double x3 = 1534; double y3 = 315;

        double h1 = ((0.2 * x1 + 0.1 - y1) + (0.2 * x2 + 0.1 - y2) + (0.2 * x3 + 0.1 - y3))/(2*3);
    }

}
