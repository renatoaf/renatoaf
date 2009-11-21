/*
 * Fibonacci Freeze
 */

import java.math.BigInteger;
import java.util.Scanner;

class Problem_495 {
	private static BigInteger[] fibonacci = new BigInteger[5001];

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
			int termo = in.nextInt();
			saida.append("The Fibonacci number for " + termo + " is "
					+ fibProgDinamica(termo) + separador);
		}

		System.out.print(saida);
	}
}