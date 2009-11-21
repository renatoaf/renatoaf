/*
 * Where's Waldorf
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10010 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());

		for (int c = 1; c <= casos; c++) {
			String linha = in.readLine();
			while (linha.equals("")) {
				linha = in.readLine();
			}

			String[] config = linha.split("\\s+");
			int m = Integer.parseInt(config[0]);
			int n = Integer.parseInt(config[1]);

			String palavrasConcatenadas = "";
			for (int i = 0; i < m; i++) {
				palavrasConcatenadas += in.readLine();
			}

			Grid grid = new Grid(m, n, palavrasConcatenadas);

			int nPalavras = Integer.parseInt(in.readLine());
			for (int i = 0; i < nPalavras; i++) {
				grid.procura(in.readLine());
				saida.append(grid + separador);
			}

			if (c != casos) {
				saida.append(separador);
			}
		}

		System.out.print(saida);
	}
}

class Grid {
	private char[][] matriz;
	private int m;
	private int n;
	private int xEncontrado;
	private int yEncontrado;

	public Grid(int m, int n, String palavrasConcatenadas) {
		this.m = m;
		this.n = n;
		this.matriz = new char[m][n];

		int d = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = palavrasConcatenadas.charAt(d++);
			}
		}
	}

	public boolean procura(String palavra) {
		xEncontrado = yEncontrado = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (verificaNorte(palavra, i, j) || verificaSul(palavra, i, j)
						|| verificaLeste(palavra, i, j)
						|| verificaOeste(palavra, i, j)
						|| verificaNordeste(palavra, i, j)
						|| verificaNoroeste(palavra, i, j)
						|| verificaSudeste(palavra, i, j)
						|| verificaSudoeste(palavra, i, j)) {
					xEncontrado = i;
					yEncontrado = j;
					return true;
				}
			}
		}
		return false;
	}

	public boolean verificaNorte(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (x < 0
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			x--;
		}
		return true;
	}

	public boolean verificaLeste(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (y >= n
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			y++;
		}
		return true;
	}

	public boolean verificaOeste(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (y < 0
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			y--;
		}
		return true;
	}

	public boolean verificaSul(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (x >= m
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			x++;
		}
		return true;
	}

	public boolean verificaNoroeste(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (x < 0
					|| y < 0
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			y--;
			x--;
		}
		return true;
	}

	public boolean verificaNordeste(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (y >= n
					|| x < 0
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			x--;
			y++;
		}
		return true;
	}

	public boolean verificaSudeste(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (y >= n
					|| x >= m
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			x++;
			y++;
		}
		return true;
	}

	public boolean verificaSudoeste(String palavra, int x, int y) {
		for (int i = 0; i < palavra.length(); i++) {
			if (y < 0
					|| x >= m
					|| Character.toUpperCase(matriz[x][y]) != Character
							.toUpperCase(palavra.charAt(i))) {
				return false;
			}
			x++;
			y--;
		}
		return true;
	}

	public String toString() {
		return (xEncontrado + 1) + " " + (yEncontrado + 1);
	}
}