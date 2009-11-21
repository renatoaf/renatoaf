/*
 * Minesweeper
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10189 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int c = 1;
		while (true) {
			String[] config = in.readLine().split("\\s+");
			int m = Integer.parseInt(config[0]);
			int n = Integer.parseInt(config[1]);

			if (m == 0 && n == 0) {
				break;
			}

			if (c != 1) {
				saida.append(separador);
			}

			String concatenada = "";
			for (int i = 0; i < m; i++) {
				concatenada += in.readLine();
			}

			Campo campo = new Campo(m, n, concatenada);
			campo.finaliza();
			saida.append("Field #" + c + ":" + separador + campo);
			c++;
		}

		System.out.print(saida);
	}
}

class Campo {
	private String[][] matriz;
	private int m;
	private int n;

	public Campo(int m, int n, String palavrasConcatenadas) {
		this.m = m;
		this.n = n;
		this.matriz = new String[m][n];

		int d = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = palavrasConcatenadas.charAt(d++) + "";
			}
		}
	}

	public int contaBombasAdjacentes(int x, int y) {
		int cont = 0;

		if (x - 1 >= 0 && matriz[x - 1][y].equals("*")) {
			cont++;
		}
		if (y - 1 >= 0 && matriz[x][y - 1].equals("*")) {
			cont++;
		}
		if (x + 1 < m && matriz[x + 1][y].equals("*")) {
			cont++;
		}
		if (y + 1 < n && matriz[x][y + 1].equals("*")) {
			cont++;
		}
		if (x - 1 >= 0 && y - 1 >= 0 && matriz[x - 1][y - 1].equals("*")) {
			cont++;
		}
		if (x - 1 >= 0 && y + 1 < n && matriz[x - 1][y + 1].equals("*")) {
			cont++;
		}
		if (x + 1 < m && y - 1 >= 0 && matriz[x + 1][y - 1].equals("*")) {
			cont++;
		}
		if (x + 1 < m && y + 1 < n && matriz[x + 1][y + 1].equals("*")) {
			cont++;
		}

		return cont;
	}

	public void finaliza() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!matriz[i][j].equals("*")) {
					matriz[i][j] = (contaBombasAdjacentes(i, j) + "");
				}
			}
		}
	}

	public String toString() {
		String saida = "";
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				saida += matriz[i][j];
			}
			saida += System.getProperty("line.separator");
		}
		return saida;
	}
}