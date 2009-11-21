/*
 * P-Networks
 */

import java.util.StringTokenizer;

class Problem_3475 {
	static String readln() {
		String newLine = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		int car = -1;
		try {
			car = System.in.read();
			while ((car > 0) && (car != newLine.charAt(0))) {
				buffer.append((char) car);
				car = System.in.read();
			}
			if (car == newLine.charAt(0))
				System.in.skip(newLine.length() - 1);
		} catch (java.io.IOException e) {
			return (null);
		}
		if ((car < 0) && (buffer.length() == 0))
			return (null); /* eof */
		if (buffer.length() == 0)
			return ""; // string vazia
		return (buffer.toString()).trim();
	}

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();

		while (true) {
			StringTokenizer config = new StringTokenizer(readln().trim());

			int N = Integer.parseInt(config.nextToken());
			if (N == 0) {
				break;
			}

			int[] transformacoes = new int[N + 1];
			int[] transfAux = new int[N + 1];
			int[] ligacoes = new int[(4 * N * N) + 1];

			for (int i = 1; i <= N; i++) {
				transformacoes[i] = Integer.parseInt(config.nextToken());
				transfAux[i] = i; // inicialmente ha transf de i pra i
			}

			int numLigacoes = 0;
			boolean erro = false;

			for (int i = 1; i <= N; i++) {
				// procura transformacao parcial que leve i a transformacoes[i]
				int j = i;
				while (j <= N && transfAux[j] != transformacoes[i]) {
					j++;
				}

				if (j > N) { // nao achou sem solucao
					erro = true;
					break;
				}

				while (j > i) {
					// encaixa fios de j ate i e atualiza transfs auxiliares
					transfAux[j] = transfAux[j - 1];
					ligacoes[numLigacoes++] = (j - 1);
					j--;
				}
			}

			if (erro) {
				saida.append("No solution\n");
			} else {
				saida.append(numLigacoes);
				for (int i = numLigacoes - 1; i >= 0; i--) {
					saida.append(" " + ligacoes[i]);
				}
				saida.append("\n");
			}
		}

		System.out.print(saida);
	}
}