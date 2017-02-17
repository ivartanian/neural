package com.company;

public class Main {

    public static void main(String[] args) {

        double i1 = 1, i2 = 0, w1 = 0.45, w2 = 0.78, w3 = -0.12, w4 = 0.13, w5 = 1.5, w6 = -2.3;

        calculate(1, i1, i2, w1, w2, w3, w4, w5, w6);

    }

    private static void calculate(int iteration, double i1, double i2, double w1, double w2, double w3, double w4, double w5, double w6) {

        if (iteration > 1){
            return;
        }

        double h1input = i1 * w1 + i2 * w3;
        double h1output = sigmoid(h1input);

        double h2input = i1 * w2 + i2 * w4;
        double h2output = sigmoid(h2input);

        double o1input = h1output * w5 + h2output * w6;
        double o1output = sigmoid(o1input);

        double o1ideal = 1 ^ 0;

        double error = Math.pow(o1ideal - o1output, 2) / 1;

        double qO1H1 = morO(o1output, o1ideal);
        double qH1 = mor(h1output, o1ideal, w5, qO1H1);
        double gradW5 = grad(h1output, qO1H1);
        w5 = w5 + getWeight(iteration, w5, gradW5);

        double qO1H2 = morO(o1output, o1ideal);
        double qH2 = mor(h2output, o1ideal, w6, qO1H2);
        double gradW6 = grad(h2output, qO1H2);
        w6 = w6 + getWeight(iteration, w6, gradW6);

        double gradW1 = grad(i1, qH1);
        w1 = w1 + getWeight(iteration, w1, gradW1);
        double gradW2 = grad(i1, qH2);
        w2 = w2 + getWeight(iteration, w2, gradW2);
        System.out.println(w2);

        calculate(++iteration, i1, i2, w1, w2, w3, w4, w5, w6);
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
