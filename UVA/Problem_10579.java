/*
 * Fibonacci Numbers
 */

import java.math.BigInteger;
import java.util.Scanner;

class Problem_10579 {
	// numero de fibonacci com mil digitos
	private static BigInteger[] fibonacci = new BigInteger[4786];

	private static BigInteger fibProgDinamica(int termo) {
		if (termo <= 1) {
			return new BigInteger(termo + "");
		} else if (fibonacci[termo] != null) {
			return fibonacci[termo];
		}
		fibonacci[termo] = (fibProgDinamica(termo - 1))
				.add(fibProgDinamica(termo - 2));
		return fibonacci[termo];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		while (in.hasNext()) {
			saida.append(fibProgDinamica(in.nextInt()) + separador);
		}

		System.out.print(saida);
	}
}