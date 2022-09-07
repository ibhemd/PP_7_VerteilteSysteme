package de.umr.ds.task3;

import java.util.Random;

/**
 * A Perceptron holds weights and bias and can be applied to a data vector to
 * predict its class. Weights and bias are initialized randomly.
 */
public class Perceptron {
	// TODO Task 3b)

    Vector w;
    double b;
    int correct;

    public Perceptron() {
        this.correct = 0;
        this.b = Math.random();

        double[] arr = new double[2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random();
        }

        this.w = new Vector(arr);
    }

    /**
     * Apply the perceptron to classify a data vector.
     *
     * @param x An input vector
     * @return 0 or 1
     */
    public int predict(Vector x) {
        // TODO Task 3b)
        if ((x.dot(w) + b) > 0) {
            return 1;
        } else return 0;
    }
}
