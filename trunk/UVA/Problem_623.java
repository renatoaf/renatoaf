/*
 * 500!
 */

import java.math.BigInteger;
import java.util.Scanner;

class Problem_623 {
	private static BigInteger[] fatoriais = new BigInteger[1001];

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
		while (in.hasNext()) {
			int n = in.nextInt();
			saida.append(n + "!" + separador + fatorial(n) + separador);
		}
		System.out.print(saida);
	}
}