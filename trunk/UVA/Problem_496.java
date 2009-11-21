/*
 * Simply Subsets
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Problem_496 {
	public static void main(String[] args) throws IOException {
		BufferedReader entradas = new BufferedReader(new InputStreamReader(
				System.in));
		StringBuilder saida = new StringBuilder();

		while (true) {
			String linhaA = entradas.readLine();

			if (linhaA == null) {
				break;
			}

			String linhaB = entradas.readLine();

			if (linhaB == null) {
				break;
			}

			String[] numerosA = linhaA.trim().split("\\s+");
			String[] numerosB = linhaB.trim().split("\\s+");

			ArrayList<Integer> A = new ArrayList<Integer>();

			for (int i = 0; i < numerosA.length; i++) {
				A.add(Integer.parseInt(numerosA[i]));
			}

			ArrayList<Integer> B = new ArrayList<Integer>();

			for (int i = 0; i < numerosB.length; i++) {
				B.add(Integer.parseInt(numerosB[i]));
			}

			Collections.sort(A);
			Collections.sort(B);

			if (A.size() == B.size()) {
				boolean saoIguais = true;
				boolean existeUmIgual = false;

				for (int i = 0; i < B.size(); i++) {
					if (!A.contains(B.get(i))) {
						saoIguais = false;
					} else {
						existeUmIgual = true;
					}
				}

				if (saoIguais) {
					saida.append("A equals B\n");
				} else {
					if (existeUmIgual) {
						saida.append("I'm confused!\n");
					} else {
						saida.append("A and B are disjoint\n");
					}
				}
			} else {
				int maiorTamanho = A.size();

				if (B.size() > maiorTamanho) {
					maiorTamanho = B.size();
					boolean existeUmIgual = false;
					boolean estahContido = true;

					for (int i = 0; i < A.size(); i++) {
						if (B.contains(A.get(i))) {
							existeUmIgual = true;
						} else {
							estahContido = false;
						}
					}

					if (existeUmIgual && !estahContido) {
						saida.append("I'm confused!\n");
					} else if (estahContido) {
						saida.append("A is a proper subset of B\n");
					} else {
						saida.append("A and B are disjoint\n");
					}

				} else {
					boolean existeUmIgual = false;
					boolean estahContido = true;

					for (int i = 0; i < B.size(); i++) {
						if (A.contains(B.get(i))) {
							existeUmIgual = true;
						} else {
							estahContido = false;
						}
					}

					if (existeUmIgual && !estahContido) {
						saida.append("I'm confused!\n");
					} else if (estahContido) {
						saida.append("B is a proper subset of A\n");
					} else {
						saida.append("A and B are disjoint\n");
					}
				}
			}
		}
		System.out.print(saida);
	}
}