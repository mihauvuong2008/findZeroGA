package genetoPhenotypic;

public class BinnaryGentoPhenotypic {
	public int miniDecimal_to_binary(double i) {
		return (int) (i + 0.5);
	}

	public int binary_to_Decimal(int[] array) {
		int decimal = 0;
		int size = array.length;
		for (int i = 0; i < size; i++) {
			decimal = decimal << 1 | array[i];
			// System.out.println("decimal: " + decimal);
		}
		return decimal;
	}

	public int binary_to_Decimal(double[] array) {
		int decimal = 0;
		int size = array.length;
		for (int i = 0; i < size; i++) {
			decimal = decimal << 1 | (array[i] >= 0.5 ? 1 : 0);
		}
		return decimal;
	}

	public static int printBinaryform(int number) {
		int remainder;

		if (number <= 1) {
			System.out.print(number);
			return number; // KICK OUT OF THE RECURSION
		}
		
		remainder = number % 2;
		printBinaryform(number >> 1);
		return remainder;
	}

	static public int[] toBin(int num) {
		int[] ret = new int[8];
		for (int i = 7, p = 0; i >= 0; i--, p++) {
			ret[i] = (num / 2 * p) % 2;
		}
		return ret;
	}

	public static double convertFromBinaryToDec(double[] output) {
		double j = 0;
		for (int i = 0; i < output.length; i++) {
			if (output[i] >= 0.5) {
				j = j + Math.pow(2, output.length - 1 - i);
			}
		}
		return j / 100000000;
	}

	public static double[] DecToBinary(int no, int LenOfGen) {
		int i = 0, temp[] = new int[LenOfGen];
		double binary[];
		while (no > 0) {
			temp[i++] = (int) (no % 2);
			no /= 2;
		}
		binary = new double[i];
		int k = 0;
		for (int j = i - 1; j >= 0; j--) {
			binary[k++] = temp[j];
		}
		return binary;
	}

}