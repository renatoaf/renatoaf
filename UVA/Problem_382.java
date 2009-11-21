/*
 * Perfection
 */

import java.util.Scanner;

class Problem_382 {
	public static int somaDivisores(int n) {
		int somaDosDivisores = 0;
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0) {
				somaDosDivisores += i;
			}
		}
		return somaDosDivisores;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer("PERFECTION OUTPUT" + separador);
		int n;

		while ((n = in.nextInt()) != 0) {
			int somaDosDivisores = somaDivisores(n);
			String tipo = "";
			if (somaDosDivisores == n) {
				tipo = "PERFECT";
			} else if (somaDosDivisores < n) {
				tipo = "DEFICIENT";
			} else {
				tipo = "ABUNDANT";
			}

			String num = String.valueOf(n);
			while (num.length() != 5) {
				num = " " + num;
			}

			saida.append(num + "  " + tipo + separador);
		}

		saida.append("END OF OUTPUT");
		System.out.println(saida);
	}
}