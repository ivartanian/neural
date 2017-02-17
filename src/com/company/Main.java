package com.company;

public class Main {

    public static void main(String[] args) {

        double i1 = 1, i2 = 0, w1 = 0.45, w2 = 0.78, w3 = -0.12, w4 = 0.13, w5 = 1.5, w6 = -2.3;

        double result = calculate(i1, i2, w1, w2, w3, w4, w5, w6);

    }

    private static double calculate(double i1, double i2, double w1, double w2, double w3, double w4, double w5, double w6) {
        double h1input = i1 * w1 + i2 * w3;
        double h1output = sigmoid(h1input);

        double h2input = i1 * w2 + i2 * w4;
        double h2output = sigmoid(h2input);

        double o1input = h1output * w5 + h2output * w6;
        double o1output = sigmoid(o1input);

        double o1ideal = 1 ^ 0;

        double error = Math.pow(o1ideal - o1output, 2) / 1;

        return o1output;
    }

    private static double sigmoid(double input) {
        return 1 / (1 + Math.exp(-1 * input));
    }

    private static double morO(double output, double ideal) {
        return (ideal - output) * ((ideal - output) *  output);
    }

    private static double mor(double output, double ideal, double w, double mor) {
        return ((ideal - output) *  output) * (w * mor);
    }

    private static double grad(double output, double mor) {
        return output * mor;
    }

    private static double getWeight(int iteration, double w, double grad) {
        double e = 0.7;
        double a = 0.3;
        int delta = iteration - 1;
        return e * grad + delta * a;
    }
}
