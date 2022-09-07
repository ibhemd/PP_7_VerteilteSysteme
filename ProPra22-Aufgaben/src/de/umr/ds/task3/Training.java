package de.umr.ds.task3;

import java.util.Collection;
import java.util.Collections;

public class Training {

	private static final double alpha = 0.05; // learning rate
	private static final int epochs = 100; // number of epochs
	static Perceptron bestPerceptron;
	static double bestAccuracy;

	/**
	 * A perceptron is trained on a dataset. After each epoch the perceptron's
	 * parameters are updated, the dataset is shuffled and the accuracy is computed.
	 * 
	 * @param perceptron the perceptron to train
	 * @param dataset the training dataset
	 */
	private static void train(Perceptron perceptron, Dataset dataset) {
		// TODO Task 3c)
		Visualization v = new Visualization(dataset, perceptron.w, perceptron.b);
		bestPerceptron = perceptron;
		bestAccuracy = 0;

		for (int i = 1; i <= epochs; i++) {
			for (DataPoint d : dataset) {
				int p = perceptron.predict(d);
				int t = d.getLabel();
				if (p != t) {
					perceptron.w = perceptron.w.add(d.mult(alpha*(t-p)));
					perceptron.b += alpha*(t-p)*1;
				} else {
					perceptron.correct += 1;
				}
			}
			double acc = Evaluation.accuracy(perceptron, dataset);
			v.update(perceptron.w, perceptron.b, i, acc);


			if (acc > bestAccuracy) {
				bestAccuracy = acc;
				bestPerceptron = perceptron;
			}
			if (bestAccuracy == 1.0) {
				break;
			}
			perceptron.correct = 0;

			Collections.shuffle(dataset);
		}
	}

	public static void main(String[] args) {

		Dataset dataset = new Dataset(1000);
		Perceptron perceptron = new Perceptron();
		train(perceptron, dataset);

	}

}
