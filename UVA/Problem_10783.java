/*
 * Odd Sum
 */

import java.util.Scanner;

class Problem_10783 {
	public static int somaImpares(int minimo, int maximo) {
		int soma = 0;
		if (minimo % 2 == 0) {
			minimo++;
		}
		for (int i = minimo; i <= maximo; i += 2) {
			soma += i;
		}
		return soma;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		int testes = in.nextInt();
		for (int i = 0; i < testes; i++) {
			int minimo = in.nextInt();
			int maximo = in.nextInt();
			saida.append("Case " + (i + 1) + ": " + somaImpares(minimo, maximo)
					+ separador);
		}

		System.out.print(saida);
	}
}