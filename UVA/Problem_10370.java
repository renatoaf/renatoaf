/*
 * Above Average
 */

import java.text.DecimalFormat;
import java.util.Scanner;

class Problem_10370 {
	public static double media(int[] inteiros) {
		if (inteiros.length == 0) {
			return 0;
		}
		double soma = 0;
		for (int i = 0; i < inteiros.length; i++) {
			soma += inteiros[i];
		}
		return soma / inteiros.length;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		int c = in.nextInt();
		for (int i = 0; i < c; i++) {
			int n = in.nextInt();
			int[] salas = new int[n];
			for (int j = 0; j < n; j++) {
				salas[j] = in.nextInt();
			}
			double media = media(salas);
			int acimaDaMedia = 0;
			for (int k = 0; k < n; k++) {
				if (salas[k] > media) {
					acimaDaMedia++;
				}
			}
			double porcentagem = (acimaDaMedia * 100) / (double) n;
			DecimalFormat formata = new DecimalFormat("0.000");
			saida.append(formata.format(porcentagem).replace(',', '.') + "%"
					+ separador);
		}

		System.out.print(saida);
	}
}