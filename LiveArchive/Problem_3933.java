/*
 * Galou is Back!
 */

import java.util.StringTokenizer;

class Problem_4217 {
	static char MOVE = 'I';
	static char N_MOVE = '*';
	static char N_TEM = '.';
	static char HORARIO = '(';
	static char A_HORARIO = ')';
	static char LIVRE = 'F';
	static char BLOQUEADA = 'B';

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

	// adjacentes da engrenagem
	static int[][] adjacentes(int x, int y) {
		int[][] adj = new int[6][2];
		adj[0][0] = x - 1;
		adj[0][1] = y;
		adj[1][0] = x - 1;
		adj[1][1] = y + 1;
		adj[2][0] = x;
		adj[2][1] = y - 1;
		adj[3][0] = x;
		adj[3][1] = y + 1;
		adj[4][0] = x + 1;
		adj[4][1] = y;
		adj[5][0] = x + 1;
		adj[5][1] = y - 1;
		return adj;
	}

	static boolean podeVisitar(int i, int j, char[][] estados, int lin, int col) {
		return (i >= 0 && j >= 0 && j < col && i < lin && estados[i][j] != N_TEM);
	}

	static void floodBloqueando(int i, int j, char[][] estados, int lin, int col) {
		estados[i][j] = BLOQUEADA;
		int[][] adj = adjacentes(i, j);
		// bloqueando enquanto houver adjacente nao-bloqueado
		for (int k = 0; k < adj.length; k++) {
			int x = adj[k][0];
			int y = adj[k][1];
			if (podeVisitar(x, y, estados, lin, col)
					&& estados[x][y] != BLOQUEADA) {
				floodBloqueando(x, y, estados, lin, col);
			}
		}
	}

	// retorna true se bloqueou, false caso contrario
	static boolean flood(int i, int j, char[][] estados, int lin, int col) {
		int[][] adj = adjacentes(i, j);
		for (int k = 0; k < adj.length; k++) {
			int x = adj[k][0];
			int y = adj[k][1];

			if (podeVisitar(x, y, estados, lin, col)) {
				if (estados[i][j] == estados[x][y]) {
					// Uma engrenagem est� bloqueada quando ela
					// est� tentando girar em ambos sentidos ao mesmo tempo.
					floodBloqueando(i, j, estados, lin, col);
					return true;

				} else if (estados[x][y] == LIVRE) {
					// Quando uma engrenagem tenta girar em um sentido, todas
					// as outras adjacentes tentam girar no sentido oposto.
					if (estados[i][j] == HORARIO) {
						estados[x][y] = A_HORARIO;
					} else if (estados[i][j] == A_HORARIO) {
						estados[x][y] = HORARIO;
					}

					if (flood(x, y, estados, lin, col)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();

		while (true) {
			StringTokenizer config = new StringTokenizer(readln());
			int linhas = Integer.parseInt(config.nextToken());
			int colunas = Integer.parseInt(config.nextToken());

			if (linhas == 0 && colunas == 0) {
				break;
			}

			char[][] estados = new char[linhas][colunas];
			char[][] motor = new char[linhas][colunas];

			for (int i = 0; i < linhas; i++) {
				motor[i] = readln().toCharArray();
				for (int j = 0; j < colunas; j++) {
					if (motor[i][j] == MOVE) {
						// S�o inicialmente ativadas e tentam giram em sentido
						// hor�rio
						estados[i][j] = HORARIO;
					} else if (motor[i][j] == N_MOVE) {
						// Uma engrenagem est� livre quando ela n�o � ativada
						estados[i][j] = LIVRE;
					} else {
						estados[i][j] = N_TEM;
					}
				}
			}

			for (int i = 0; i < linhas; i++) {
				for (int j = 0; j < colunas; j++) {
					if (motor[i][j] == MOVE) {
						flood(i, j, estados, linhas, colunas);
					}
				}
			}

			saida.append("\n");
			for (int i = 0; i < linhas; i++) {
				for (int j = 0; j < colunas; j++) {
					saida.append(estados[i][j]);
				}
				saida.append("\n");
			}
		}

		System.out.print(saida);
	}
}