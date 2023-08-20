package ga_training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import genetoPhenotypic.BinnaryGentoPhenotypic;

public class Trainer {
	private boolean isPauseTrain = false;
	private ArrayList<GENE> Populations;
	private Random ran;
	private AiEvolution aiEvolution;
	private int LenOfGen = 250;
	private int Loop = 1000;
	private Boolean naturalFitnessScores = false;
	private int firstClasssize = 10000;
	private int minimumPopsize = 10000;
	private int maximunPopsize = 65000;
	private int numOfChild = 2;
	private double makeEverythingRatio = 0.006;
	private double SelectionRatio = 0.603;
	private double MutantRatio = 0.298;
	private double SomaMutantRatio = 0.006;
	private double HybridRatio = 0.006;
	private double DefendRatio = 0.006;
	private double ValueLevel = 0.5;
	private double NChildPlan = 0.5;
	EvaluatedCandidate result;

	public Trainer() {
		super();
		ran = new Random();
		aiEvolution = new AiEvolution(ran, LenOfGen);
	}

	final float gama = 0.5f;
	ArrayList<EvaluatedCandidate> candidateSet = null;

	public void Train() throws InterruptedException {
		Populations = aiEvolution.getFirstClass(firstClasssize);
		long tmpTime = 0;
		for (int i = 0; i < Loop; i++) {

			while (isPauseTrain) {
				Thread.sleep(2000);
			}

			tmpTime = System.currentTimeMillis();

			System.out.println();
			System.out.println("Start... - loop: " + i);

			double negative = (ran.nextDouble() - ran.nextDouble());
			double plan = gama + (negative > 0 ? (negative + NChildPlan) : (negative - NChildPlan));
			int PlannumOfChild = (int) (numOfChild + plan);

			if (Populations.size() < maximunPopsize) {
				System.out.println("Incorporating...");
				aiEvolution.Incorporate /* ket hop */ (Populations, PlannumOfChild, MutantRatio, SomaMutantRatio,
						HybridRatio, DefendRatio, makeEverythingRatio);

				System.out.println();
				System.out.println("aiEvolution Value...");
				candidateSet = aiEvolution.Value(Populations, ValueLevel);

				System.out.println("candidateSet.size(): [" + candidateSet.size() + "] , PlannumOfChild: "
						+ PlannumOfChild + ", MutantRatio: " + MutantRatio + ", SomaMutantRatio: " + SomaMutantRatio
						+ ", HybridRatio: " + HybridRatio + ", DefendRatio: " + DefendRatio + ", makeEverythingRatio "
						+ makeEverythingRatio);

				System.out.println();
				System.out.println("get Algor Result...");
				EvaluatedCandidate evaluatedCandidate = getalgoritResult(candidateSet);

				System.out.println();
				System.out.println("current result: ");
				if (evaluatedCandidate != null) {
					System.out.println("x: " + BinnaryGentoPhenotypic
							.convertFromBinaryToDec(evaluatedCandidate.getCandidate().getGene()));
					System.out.println("getFitness: " + evaluatedCandidate.getFitness());
				}

				if (result == null) {
					result = evaluatedCandidate;
				} else {
					if (result.getFitness() < evaluatedCandidate.getFitness())
						result = evaluatedCandidate;
				}

				System.out.println();
				System.out.println("best result: ");
				if (result != null) {
					// System.out.println(Arrays.toString(evaluatedCandidate.getCandidate().getGene()));
					double x = BinnaryGentoPhenotypic.convertFromBinaryToDec(result.getCandidate().getGene());
					System.out.println("x: " + x);
					System.out.println("getFitness: " + result.getFitness());
					if (x == -1) {
						System.out.println("Array: " + Arrays.toString(result.getCandidate().getGene()));
					}
				}
			}
			int size = (int) (SelectionRatio * Populations.size());
			size = size > minimumPopsize ? size : Populations.size();

			System.out.println();
			System.out.println("minPopSize: " + minimumPopsize + ", SelectionRatio: " + SelectionRatio
					+ ", RouletteWheelSelecting Size: [" + size + "])...");
			Populations = RouletteWheelSelection.select(candidateSet, naturalFitnessScores, ran, size);
			System.out.println();

			System.out.println("===End...===");
			System.out.println("Total time: " + (System.currentTimeMillis() - tmpTime));
			System.out.println();
			System.out.println();
			System.out.println();
		}

	}

	private EvaluatedCandidate getalgoritResult(ArrayList<EvaluatedCandidate> candidateSet) {
		double max = -1 * Double.MAX_VALUE; //
		EvaluatedCandidate result = null;

		for (EvaluatedCandidate evaluatedCandidate : candidateSet) {

//			 System.out.println("evaluatedCandidate.getFitness(): " +
//			 evaluatedCandidate.getFitness() + ", max: " + max);

			if (evaluatedCandidate.getFitness() >= max) {
				result = evaluatedCandidate;
				max = evaluatedCandidate.getFitness();
			}
		}

		return result;
	}

	public boolean isPauseTrain() {
		return isPauseTrain;
	}

	public void setPauseTrain(boolean isPauseTrain) {
		this.isPauseTrain = isPauseTrain;
	}

	public ArrayList<GENE> getPopulations() {
		return Populations;
	}

	public void setPopulations(ArrayList<GENE> populations) {
		Populations = populations;
	}

	public int getLenOfGen() {
		return LenOfGen;
	}

	public void setLenOfGen(int lenOfGen) {
		LenOfGen = lenOfGen;
	}

	public int getLoop() {
		return Loop;
	}

	public void setLoop(int loop) {
		Loop = loop;
	}

	public Boolean getNaturalFitnessScores() {
		return naturalFitnessScores;
	}

	public void setNaturalFitnessScores(Boolean naturalFitnessScores) {
		this.naturalFitnessScores = naturalFitnessScores;
	}

	public int getFirstClasssize() {
		return firstClasssize;
	}

	public void setFirstClasssize(int firstClasssize) {
		this.firstClasssize = firstClasssize;
	}

	public int getMinimumPopsize() {
		return minimumPopsize;
	}

	public void setMinimumPopsize(int minimumPopsize) {
		this.minimumPopsize = minimumPopsize;
	}

	public int getNumOfChild() {
		return numOfChild;
	}

	public void setNumOfChild(int numOfChild) {
		this.numOfChild = numOfChild;
	}

	public double getMakeEverythingRatio() {
		return makeEverythingRatio;
	}

	public void setMakeEverythingRatio(double makeEverythingRatio) {
		this.makeEverythingRatio = makeEverythingRatio;
	}

	public double getSelectionRatio() {
		return SelectionRatio;
	}

	public void setSelectionRatio(double selectionRatio) {
		SelectionRatio = selectionRatio;
	}

	public double getHybridRatio() {
		return HybridRatio;
	}

	public void setHybridRatio(double hybridRatio) {
		HybridRatio = hybridRatio;
	}

	public double getMutantRatio() {
		return MutantRatio;
	}

	public void setMutantRatio(double mutantRatio) {
		MutantRatio = mutantRatio;
	}

	public double getSomaMutantRatio() {
		return SomaMutantRatio;
	}

	public void setSomaMutantRatio(double somaMutantRatio) {
		SomaMutantRatio = somaMutantRatio;
	}

	public double getDefendRatio() {
		return DefendRatio;
	}

	public void setDefendRatio(double defendRatio) {
		DefendRatio = defendRatio;
	}

	public double getValueLevel() {
		return ValueLevel;
	}

	public void setValueLevel(double valueLevel) {
		ValueLevel = valueLevel;
	}

	public void setNChildPlan(int NChildPlan) {
		this.NChildPlan = NChildPlan;

	}

	public void setMaximunPopsize(int maximunPopsize) {
		this.maximunPopsize = maximunPopsize;
	}

	public int getMaximunPopsize() {
		return maximunPopsize;
	}

}
