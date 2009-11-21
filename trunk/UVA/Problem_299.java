/*
 * Train Swapping
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_299 {
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

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());
		for (int i = 0; i < casos; i++) {
			int n = Integer.parseInt(in.readLine());
			String[] sequencia = in.readLine().split(("\\s+"));

			int[] trens = new int[n];
			for (int k = 0; k < n; k++) {
				trens[k] = Integer.parseInt(sequencia[k]);
			}

			saida.append("Optimal train swapping takes " + bubblesort(trens)
					+ " swaps." + separador);
		}

		System.out.print(saida);
	}
}