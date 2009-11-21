import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_572 {
	static final char BARRIL = '@';
	static final char PINTADO = 'X';
	static final char NADA = '*';
	static boolean flood;

	static int[][] adj(int x, int y) {
		return new int[][] { { x, y - 1 }, { x, y + 1 }, { x - 1, y - 1 },
				{ x - 1, y + 1 }, { x + 1, y - 1 }, { x + 1, y + 1 },
				{ x - 1, y }, { x + 1, y } };
	}

	static boolean pode(int i, int j, int lin, int col, char[][] deposito) {
		return i >= 0 && j >= 0 && i < lin && j < col && deposito[i][j] != NADA
				&& deposito[i][j] != PINTADO;
	}

	static void flood(int i, int j, char[][] deposito, int linhas, int colunas) {
		flood = true;
		deposito[i][j] = PINTADO;

		int[][] adjacentes = adj(i, j);

		for (int k = 0; k < adjacentes.length; k++) {
			int x = adjacentes[k][0];
			int y = adjacentes[k][1];
			if (pode(x, y, linhas, colunas, deposito)) {
				flood(x, y, deposito, linhas, colunas);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String[] config = in.readLine().split("\\s+");
			int linhas = Integer.parseInt(config[0]);
			int colunas = Integer.parseInt(config[1]);

			if (linhas == 0 && colunas == 0) {
				break;
			}

			char[][] deposito = new char[linhas][colunas];
			for (int i = 0; i < linhas; i++) {
				deposito[i] = in.readLine().toCharArray();
			}

			int qt = 0;
			for (int i = 0; i < linhas; i++) {
				for (int j = 0; j < colunas; j++) {
					if (deposito[i][j] == BARRIL) {
						flood = false;
						flood(i, j, deposito, linhas, colunas);
						if (flood) {
							qt++;
						}
					}
				}
			}

			saida.append(qt + separador);
		}

		System.out.print(saida);
	}
}