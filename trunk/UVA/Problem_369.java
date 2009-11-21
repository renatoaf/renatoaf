/*
 * Combinations
 */

import java.math.BigInteger;
import java.util.Scanner;

class Problem_369 {
	private static BigInteger[] fatoriais = new BigInteger[101];

	public static BigInteger fatorial(int n) {
		if (fatoriais[n] == null) {
			BigInteger num = new BigInteger(n + "");
			BigInteger fat = BigInteger.ONE;

			while (num.compareTo(BigInteger.ONE) > 0) {
				if (fatoriais[num.intValue()] == null) {
					fat = fat.multiply(num);
					num = num.subtract(BigInteger.ONE);
				} else {
					fat = fat.multiply(fatoriais[num.intValue()]);
					break;
				}
			}

			fatoriais[n] = fat;
		}
		return fatoriais[n];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		while (true) {
			int n = in.nextInt();
			int m = in.nextInt();

			if (n == 0 && m == 0) {
				break;
			}

			BigInteger numerador = fatorial(n);
			BigInteger den1 = fatorial(n - m);
			BigInteger den2 = fatorial(m);
			BigInteger denominador = den1.multiply(den2);
			BigInteger c = numerador.divide(denominador);

			saida.append(n + " things taken " + m + " at a time is " + c
					+ " exactly." + separador);
		}

		System.out.print(saida);
	}
}