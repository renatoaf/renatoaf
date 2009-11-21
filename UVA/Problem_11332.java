/*
 * Summing Digits
 */

import java.util.Scanner;

class Problem_11332 {
	public static int f(int n) {
		int soma = 0;
		while (n > 0) {
			soma += (n % 10);
			n /= 10;
		}

		if ((soma + "").length() == 1) {
			return soma;
		} else {
			return f(soma);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		int n;
		while ((n = in.nextInt()) != 0) {
			saida.append(f(n) + separador);
		}

		System.out.print(saida);
	}
}
