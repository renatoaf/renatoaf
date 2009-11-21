/*
 * f91
 */

import java.util.Scanner;

class Problem_10696 {
	private static int f91(int n) {
		if (n <= 100) {
			n = f91(f91(n + 11));
		} else if (n >= 101) {
			n = n - 10;
		}
		return n;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		int n;
		while ((n = in.nextInt()) != 0) {
			saida.append("f91(" + n + ") = " + f91(n) + separador);
		}

		System.out.print(saida);
	}
}
