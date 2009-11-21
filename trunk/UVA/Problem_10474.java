/*
 * Where is the Marble
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Problem_10474 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int c = 1;
		while (true) {
			String[] config = in.readLine().split("\\s+");
			int N = Integer.parseInt(config[0]);
			int Q = Integer.parseInt(config[1]);

			if (N == 0 && Q == 0) {
				break;
			}

			ArrayList<Integer> ordem = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				ordem.add(Integer.parseInt(in.readLine()));
			}
			Collections.sort(ordem);

			HashMap<Integer, Integer> posicoes = new HashMap<Integer, Integer>();
			for (int i = 0; i < N; i++) {
				int n = ordem.get(i);
				posicoes.put(n, i);
				while (i + 1 < N && ordem.get(i + 1) == n) {
					i++;
				}
			}

			saida.append("CASE# " + c++ + ":" + separador);
			for (int i = 0; i < Q; i++) {
				int pergunta = Integer.parseInt(in.readLine());
				Integer posicao = posicoes.get(pergunta);
				saida.append(posicao == null ? (pergunta + " not found")
						: (pergunta + " found at " + (posicao + 1)));
				saida.append(separador);
			}
		}

		System.out.print(saida);
	}
}