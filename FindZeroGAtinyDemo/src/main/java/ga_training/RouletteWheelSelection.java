package ga_training;

import java.util.ArrayList;
import java.util.Random;

public class RouletteWheelSelection {

	private static int WHEEL_BinnarySearch(double candi_position, double arr[]) {
		if (candi_position < arr[0])
			return 0;

		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			if (candi_position > arr[i]) {
				idx = i + 1;
			} else {
//				System.out.println("index:" + idx + ", cumulativeFitnesses: " + arr[i]);
				break;
			}
		}
		return idx;
	}

	private static double getAdjustedFitness(double rawFitness, Boolean naturalFitness) {
		if (naturalFitness) {// option
			return rawFitness;
		} else {
			if (rawFitness == 0) {
				return Double.POSITIVE_INFINITY;
			} else {
				return (1 / (rawFitness));
			}
		}
	}

	public static ArrayList<GENE> select(ArrayList<EvaluatedCandidate> population, Boolean naturalFitnessScores,
			Random ran, int selectionSize) {
		int popSize = population.size();
		double cumulativeFitnesses[] = new double[popSize];
		cumulativeFitnesses[0] = getAdjustedFitness(population.get(0).getFitness(), naturalFitnessScores);
//		System.out.println("cumulativeFitnesses[" + 0 + "]: " + cumulativeFitnesses[0]);
		for (int i = 1; i < popSize; i++) {
			double fitness = getAdjustedFitness(population.get(i).getFitness(), naturalFitnessScores);
			// tao banh xe //rawFitness cang lon, fitness tuong thich cang nho.
			cumulativeFitnesses[i] = cumulativeFitnesses[i - 1] + fitness;
//			System.out.println("cumulativeFitnesses[" + i + "]: " + cumulativeFitnesses[i]);
		}

		ArrayList<GENE> selection = new ArrayList<>();

		for (int i = 0; i < selectionSize; i++) {
			double randomFitness = ran.nextDouble() * cumulativeFitnesses[popSize - 1];
			int index = WHEEL_BinnarySearch(randomFitness, cumulativeFitnesses);
			GENE candidate = population.get(index).getCandidate();
			selection.add(candidate);
		}

		return selection;
	}

}
