/*
 * An Arithmetic Permutation
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_11129 {
	static ArrayList<Elemento> gera(ArrayList<Elemento> permutacao) {
		int n = permutacao.size();
		if (n <= 2)
			return permutacao;

		ArrayList<Elemento> pares = new ArrayList<Elemento>();
		ArrayList<Elemento> impares = new ArrayList<Elemento>();
		for (Elemento i : permutacao) { // dividir
			if (i.relativo % 2 == 0)
				pares.add(i);
			else
				impares.add(i);
			i.relativo /= 2; // mantem como permutacao
		}

		ArrayList<Elemento> solEsq = gera(pares); // conquistar
		ArrayList<Elemento> solDir = gera(impares); // conquistar
		solEsq.addAll(solDir); // combinar
		return solEsq;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			int n = in.nextInt();

			if (n == 0)
				break;

			ArrayList<Elemento> permutacao = new ArrayList<Elemento>();
			for (int i = 0; i < n; i++)
				permutacao.add(new Elemento(i));
			ArrayList<Elemento> solucao = gera(permutacao);
			System.out.print(n + ":");
			for (int i = 0; i < n; i++)
				System.out.print(" " + solucao.get(i));
			System.out.println();
		}
	}
}

class Elemento {
	int real;
	int relativo;

	public Elemento(int n) {
		real = relativo = n;
	}

	public String toString() {
		return real + "";
	}
}