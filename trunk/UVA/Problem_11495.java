/*
 * Bubbles and Buckets
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Problem_11495 {
	static int inv;

	static int contaInversoes(int[] numeros) {
		inv = 0;
		mergesort(numeros, 0, numeros.length - 1);
		return inv;
	}

	static void mergesort(int[] vetor, int min, int max) {
		if (min >= max) {
			return;
		}
		int meio = (min + max) / 2;
		mergesort(vetor, min, meio);
		mergesort(vetor, meio + 1, max);
		mescla(vetor, min, meio, max);
	}

	static void mescla(int[] vetor, int min, int meio, int max) {
		int[] aux = new int[max - min + 1];
		for (int i = 0; i < aux.length; i++) {
			aux[i] = vetor[min + i];
		}

		int i = 0;
		int iEsq = 0;
		int maxEsq = meio - min;
		int iDir = maxEsq + 1;
		int maxDir = aux.length - 1;

		while (iEsq <= maxEsq && iDir <= maxDir) {
			if (aux[iEsq] < aux[iDir]) {
				vetor[min + i] = aux[iEsq++];
			} else {
				inv += (maxEsq - iEsq + 1);
				vetor[min + i] = aux[iDir++];
			}
			i++;
		}

		while (iDir <= maxDir) {
			vetor[min + i++] = aux[iDir++];
		}
		while (iEsq <= maxEsq) {
			vetor[min + i++] = aux[iEsq++];
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				System.in)));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			int N = in.nextInt();

			if (N == 0) {
				break;
			}

			int[] numeros = new int[N];
			for (int i = 0; i < N; i++) {
				numeros[i] = in.nextInt();
			}

			saida.append((contaInversoes(numeros) % 2 == 0 ? "Carlos"
					: "Marcelo")
					+ separador);
		}

		System.out.print(saida);
	}
}