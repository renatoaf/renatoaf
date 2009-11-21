/*
 * Counting Stars
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_11244 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String[] config = in.readLine().split("\\s+");
			int m = Integer.parseInt(config[0]);
			int n = Integer.parseInt(config[1]);

			if (m == 0 && n == 0) {
				break;
			}

			String concatenada = "";
			for (int i = 0; i < m; i++) {
				concatenada += in.readLine();
			}

			Ceu ceu = new Ceu(m, n, concatenada);
			saida.append(ceu.contaEstrelas() + separador);
		}

		System.out.print(saida);
	}
}

class Ceu {
	private char[][] matriz;
	private int m;
	private int n;

	public Ceu(int m, int n, String linhasConcatenadas) {
		this.m = m;
		this.n = n;
		this.matriz = new char[m][n];

		int d = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = linhasConcatenadas.charAt(d++);
			}
		}
	}

	public boolean temAdjacenteEstrela(int x, int y) {
		return (x - 1 >= 0 && matriz[x - 1][y] == '*')
				|| (y - 1 >= 0 && matriz[x][y - 1] == '*')
				|| (x + 1 < m && matriz[x + 1][y] == '*')
				|| (y + 1 < n && matriz[x][y + 1] == '*')
				|| (x - 1 >= 0 && y - 1 >= 0 && matriz[x - 1][y - 1] == '*')
				|| (x - 1 >= 0 && y + 1 < n && matriz[x - 1][y + 1] == '*')
				|| (x + 1 < m && y - 1 >= 0 && matriz[x + 1][y - 1] == '*')
				|| (x + 1 < m && y + 1 < n && matriz[x + 1][y + 1] == '*');
	}

	public int contaEstrelas() {
		int cont = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matriz[i][j] == '*' && !temAdjacenteEstrela(i, j)) {
					cont++;
				}
			}
		}
		return cont;
	}
}