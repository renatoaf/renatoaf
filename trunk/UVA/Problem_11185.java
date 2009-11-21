/*
 * Ternary
 */

import java.util.Scanner;

class Problem_11185 {
	public static String converteParaTernario(int n) {
		if (n == 0) {
			return "0";
		}
		String ternario = "";
		while (n > 0) {
			ternario = (n % 3) + ternario;
			n /= 3;
		}
		return ternario;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		int n;
		while ((n = in.nextInt()) >= 0) {
			saida.append(converteParaTernario(n) + separador);
		}

		System.out.print(saida);
	}
}
