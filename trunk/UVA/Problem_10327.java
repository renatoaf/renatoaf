/*
 * Flip Sort
 */

import java.util.Scanner;

class Problem_10327 {
	private static int bubblesort(int[] vetor) {
		int swaps = 0;
		for (int i = 0; i < vetor.length - 1; i++) {
			for (int j = 0; j < vetor.length - 1 - i; j++) {
				if (vetor[j] > vetor[j + 1]) {
					swap(vetor, j, j + 1);
					swaps++;
				}
			}
		}
		return swaps;
	}

	private static void swap(int[] vetor, int i, int j) {
		int temp = vetor[i];
		vetor[i] = vetor[j];
		vetor[j] = temp;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (in.hasNext()) {
			int num = Math.abs(in.nextInt());

			int[] numeros = new int[num];
			for (int i = 0; i < num; i++) {
				numeros[i] = in.nextInt();
			}

			saida.append("Minimum exchange operations : " + bubblesort(numeros)
					+ separador);
		}

		System.out.print(saida);
	}
}