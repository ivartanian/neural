package com.company;

public class MainFirst {

    public static void main(String[] args) {

        double i1 = 1, i2 = 0;
        double w1 = 0.45, w2 = 0.78, w3 = -0.12, w4 = 0.13, w5 = 1.5, w6 = -2.3;

        Result result = calculate(1, i1, i2, new Result(100, w1, w2, w3, w4, w5, w6, Double.MIN_VALUE), true);

        Result calculate = calculate(1, 0, 0, new Result(100, result.w1, result.w2, result.w3, result.w4, result.w5, result.w6, Double.MIN_VALUE), false);

    }

    private static Result calculate(int iteration, double i1, double i2, Result result, boolean learning) {

        if ((iteration > 100 || result.getError() == 0d) && learning) {
            return result;
        }

        double w1 = result.getW1();
        double w2 = result.getW2();
        double w3 = result.getW3();
        double w4 = result.getW4();
        double w5 = result.getW5();
        double w6 = result.getW6();

        double h1input = i1 * w1 + i2 * w3;
        double h1output = sigmoid(h1input);

        double h2input = i1 * w2 + i2 * w4;
        double h2output = sigmoid(h2input);

        double o1input = h1output * w5 + h2output * w6;
        double o1output = sigmoid(o1input);

        double o1ideal = 1 ^ 0;

        double error = Math.pow(o1ideal - o1output, 2) / 1;

        if (!learning){
            return new Result(error, w1, w2, w3, w4, w5, w6, o1output);
        }

        double qO1 = morO(o1output, o1ideal);
        double qH1 = mor(h1output, o1ideal, w5, qO1);
        double gradW5 = grad(h1output, qO1);
        w5 = w5 + getWeight(iteration, w5, gradW5);

        double qH2 = mor(h2output, o1ideal, w6, qO1);
        double gradW6 = grad(h2output, qO1);
        w6 = w6 + getWeight(iteration, w6, gradW6);

        double gradW1 = grad(i1, qH1);
        w1 = w1 + getWeight(iteration, w1, gradW1);
        double gradW2 = grad(i1, qH2);
        w2 = w2 + getWeight(iteration, w2, gradW2);
        double gradW3 = grad(i2, qH1);
        w3 = w3 + getWeight(iteration, w3, gradW3);
        double gradW4 = grad(i2, qH2);
        w4 = w4 + getWeight(iteration, w4, gradW4);

        return calculate(++iteration, i1, i2, new Result(error, w1, w2, w3, w4, w5, w6, o1output), learning);
    }

    private static double sigmoid(double input) {
        return 1 / (1 + Math.exp(-1 * input));
    }

    private static double morO(double output, double ideal) {
        return (ideal - output) * ((ideal - output) * output);
    }

    private static double mor(double output, double ideal, double w, double mor) {
        return ((ideal - output) * output) * (w * mor);
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

    static class Result{
        double error, w1, w2, w3, w4, w5, w6, out;

        public Result(double error, double w1, double w2, double w3, double w4, double w5, double w6, double out) {
            this.error = error;
            this.w1 = w1;
            this.w2 = w2;
            this.w3 = w3;
            this.w4 = w4;
            this.w5 = w5;
            this.w6 = w6;
            this.out = out;
        }

        public double getW1() {
            return w1;
        }

        public Result setW1(double w1) {
            this.w1 = w1;
            return this;
        }

        public double getW2() {
            return w2;
        }

        public Result setW2(double w2) {
            this.w2 = w2;
            return this;
        }

        public double getW3() {
            return w3;
        }

        public Result setW3(double w3) {
            this.w3 = w3;
            return this;
        }

        public double getW4() {
            return w4;
        }

        public Result setW4(double w4) {
            this.w4 = w4;
            return this;
        }

        public double getW5() {
            return w5;
        }

        public Result setW5(double w5) {
            this.w5 = w5;
            return this;
        }

        public double getW6() {
            return w6;
        }

        public Result setW6(double w6) {
            this.w6 = w6;
            return this;
        }

        public double getError() {
            return error;
        }

        public Result setError(double error) {
            this.error = error;
            return this;
        }

        public double getOut() {
            return out;
        }

        public Result setOut(double out) {
            this.out = out;
            return this;
        }
    }
}
