package ga_training;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class AiEvolution {
	private Random ran;
	private int LenOfGen;
	private Valuer valuer;

	public AiEvolution(Random r, int LenOfGen) {
		this.ran = r;
		this.LenOfGen = LenOfGen;
		valuer = new Valuer();
	}

	private GENE Hybrid(GENE gene, GENE gene2, double HybridRatio) {
		double rt = ran.nextDouble();
		if (rt > HybridRatio)
			return null;
		double[] DNA1 = gene.getGene();
		double[] DNA2 = gene2.getGene();

		double[] fusion = new double[LenOfGen];
		for (int i = 0; i < LenOfGen; i++) {
			fusion[i] = (DNA1[i] + DNA2[i]) / 2;
		}

		GENE candidate = new GENE();
		candidate.setGene(fusion);
		return candidate;
	}

	private GENE Defend(GENE gene, GENE gene2, double Defend) {
		double rt = ran.nextDouble();
		if (rt > Defend)
			return null;
		double[] DNA1 = gene.getGene();
		double[] DNA2 = gene2.getGene();

		double[] Defendend = new double[LenOfGen];
		for (int i = 0; i < LenOfGen; i++) {
			double df = (DNA1[i] - DNA2[i]) * 2;
			Defendend[i] = Math.abs(df - 1);
		}

		GENE candidate = new GENE();
		candidate.setGene(Defendend);
		return candidate;
	}

	private GENE Mutation(GENE candidate, double mutantRatio) {
		double rt = ran.nextDouble();
		if (rt > mutantRatio)
			return candidate;
		int type = (int) ran.nextInt(8);
		int knot = LenOfGen / (ran.nextInt(3) + 2);
		if (type == 0) {
			// dao doan
			double[] child = candidate.getGene();
			for (int i = 0; i < knot; i++) {
				double tmp = child[i];
				child[i] = child[LenOfGen - i - 1];
				child[LenOfGen - i - 1] = tmp;
			}
			return candidate;
		} else if (type < 4) {
			// dao doan 2
			double[] child = candidate.getGene();
			for (int i = 0; i < knot; i++) {
				double tmp = child[i];
				child[i] = child[LenOfGen / 2 + i];
				child[LenOfGen / 2 + i] = tmp;
			}
			return candidate;
		} else {
			// thay the
			int genIdx = ran.nextInt(LenOfGen);
			// double DNA = ran.nextDouble();
			double DNA = ran.nextInt(2);
			candidate.getGene()[genIdx] = DNA;
			return candidate;
		}
	}

	private void SomaMutation(GENE candidate, double SomaMutationRatio) {
		double rt = ran.nextDouble();
		if (rt > SomaMutationRatio)
			return;
//		System.out.println("rt " + rt + " SomaMutation " + SomaMutationRatio);
		int len = candidate.getGene().length;
		int DNAposi = (int) ran.nextInt(len);
		candidate.getGene()[DNAposi] = ran.nextDouble();

	}

	GENE getChild(GENE gene, GENE gene2) {
		double[] DNA1 = gene.getGene();
		double[] DNA2 = gene2.getGene();
		double[] child = new double[LenOfGen];
		int knot = LenOfGen / (ran.nextInt(2) + 2);
		int randomChild = ran.nextInt(2);
		if (randomChild == 0) {
			for (int i = 0; i < LenOfGen; i++) {
				if (i > knot) {
					child[i] = DNA1[i];
				} else {
					child[i] = DNA2[i];
				}
			}
		} else if (randomChild == 1) {
			for (int i = 0; i < LenOfGen; i++) {
				if (i > knot) {
					child[i] = DNA2[i];
				} else {
					child[i] = DNA1[i];
				}
			}
		}
		GENE candidate = new GENE();
		candidate.setGene(child);
		return candidate;
	}

	GENE[] MakeEverything(GENE gene, GENE gene2, GENE gene3, double makeEverythingRatio) {
		double rt = ran.nextDouble();
		if (rt > makeEverythingRatio)
			return null;
		double[] DNA1 = gene.getGene();
		double[] DNA2 = gene2.getGene();
		double[] DNA3 = gene3.getGene();
		GENE[] result = new GENE[6];
		int x = 0;
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (j != i)
					for (int k = 1; k < 4; k++) {
						if (k != j && k != i) {
							result[x] = new GENE();
							double[] gen = new double[LenOfGen];
							for (int g = 0; g < LenOfGen; g++) {
								gen[g] = (i * DNA1[g] + j * DNA2[g] + k * DNA3[g]) / 6;
							}
							result[x].setGene(gen);
							x++;

						}
					}
			}
		}
		return null;
	}

	public void Incorporate(ArrayList<GENE> pop, int numOfChild, double MutantRatio, double SomaMutationRatio,
			double HybridRatio, double DefendRatio, double makeEverythingRatio) {

		int size = pop.size();
		for (int i = 0; i < size; i++) {
			int woman = ran.nextInt(size);
			int fusionPathner = ran.nextInt(size);
			int defendPathner = ran.nextInt(size);
			int[] makeEverythingPathner = new int[2];
			makeEverythingPathner[0] = ran.nextInt(size);
			makeEverythingPathner[1] = ran.nextInt(size);

			for (int c = 0; c < numOfChild; c++) {
				GENE chil = getChild(pop.get(i), pop.get(woman));
				pop.add(Mutation(chil, MutantRatio));

				GENE fusion = Hybrid(pop.get(i), pop.get(fusionPathner), HybridRatio);
				if (fusion != null) {
					pop.add(fusion);
				}

				GENE defend = Defend(pop.get(i), pop.get(defendPathner), DefendRatio);
				if (defend != null) {
					pop.add(defend);
				}

				SomaMutation(pop.get(i), SomaMutationRatio);

				GENE[] makeEverything = MakeEverything(pop.get(i), pop.get(makeEverythingPathner[0]),
						pop.get(makeEverythingPathner[1]), makeEverythingRatio);
				if (makeEverything != null) {
					for (int j = 0; j < makeEverything.length; j++) {
						pop.add(makeEverything[j]);
					}
				}

			}
		}
	}

	public ArrayList<GENE> getFirstClass(double firstClass_size) {
		ArrayList<GENE> rs = new ArrayList<>();
		for (int i = 0; i < firstClass_size; i++) {
			GENE gene = new GENE();
			double[] g = new double[LenOfGen];
			for (int j = 0; j < LenOfGen; j++) {
				g[j] = ran.nextDouble();
			}
			gene.setGene(g);
			rs.add(gene);
		}
		return rs;
	}

	public ArrayList<EvaluatedCandidate> Value(ArrayList<GENE> part, double ValueLevel) {
		ArrayList<EvaluatedCandidate> result = new ArrayList<>();
		int size = part.size();
		valuer.setValueLevel(ValueLevel);
		for (int i = 0; i < size; i++) {
			EvaluatedCandidate evaluatedCandidate = new EvaluatedCandidate();
			GENE element = part.get(i);
			evaluatedCandidate.setCandidate(element);
			evaluatedCandidate.setIndex(valuer.getValue(element));
			result.add(evaluatedCandidate);
		}
		return result;
	}

}
