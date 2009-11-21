/*
 * Automated Judge Script
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10188 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int x = 1;
		while (true) {
			int N = Integer.parseInt(in.readLine());

			if (N == 0) {
				break;
			}

			String solucao = "";

			for (int i = 0; i < N; i++) {
				solucao += in.readLine() + separador;
			}

			int M = Integer.parseInt(in.readLine());
			String solucaoUsuario = "";

			for (int i = 0; i < M; i++) {
				solucaoUsuario += in.readLine() + separador;
			}

			String solucaoNumerica = "";
			String solucaoNumericaUsuario = "";

			for (int i = 0; i < solucao.length(); i++) {
				char c = solucao.charAt(i);
				if (Character.isDigit(c)) {
					solucaoNumerica += c;
				}
			}

			for (int i = 0; i < solucaoUsuario.length(); i++) {
				char c = solucaoUsuario.charAt(i);
				if (Character.isDigit(c)) {
					solucaoNumericaUsuario += c;
				}
			}

			saida.append("Run #" + x++ + ": ");
			if (solucao.equals(solucaoUsuario)) {
				saida.append("Accepted");
			} else if (solucaoNumerica.equals(solucaoNumericaUsuario)) {
				saida.append("Presentation Error");
			} else {
				saida.append("Wrong Answer");
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}