package ga_training;

import genetoPhenotypic.BinnaryGentoPhenotypic;

public class Valuer {

	private double ValueLevel = 0.5d; // luong gia cang cao cang gan loi giai

	public double getValueLevel() {
		return ValueLevel;
	}

	public void setValueLevel(double valueLevel) {
		ValueLevel = valueLevel;
	}

	public double getValue(GENE g) {
		double x = BinnaryGentoPhenotypic.convertFromBinaryToDec(g.getGene());
		double y = Math.pow(Math.abs(-1 * Math.pow(x, 4) + 6 * Math.pow(x, 3) - 3 * Math.pow(x, 2) + 3 * x + 1),
				getValueLevel());

		return 1 / (0.5 + y);// luong gia cang cao cang gan loi giai
	}
}
