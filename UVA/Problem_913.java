/*
 * Joana and odd numbers
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_913 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();
			if (linha == null) {
				break;
			}
			long n = Long.parseLong(linha);
			long i = (n + 1) / 2; // i-esima linha eh a linha com n impares

			// formula simplificada:
			// numero de numeros por linha (2i - 1)
			// em n linhas: (somatorio, serie finita, pa), k = i^2
			// k-esimo elemento impar: 2k-1 -> 2i^2 - 1
			long ultimo = (2 * (i * i)) - 1;
			saida.append(((3 * ultimo) - 6) + separador); // ultimo + (ultimo-2)
			// + (ultimo-4)
		}

		System.out.print(saida);
	}
}