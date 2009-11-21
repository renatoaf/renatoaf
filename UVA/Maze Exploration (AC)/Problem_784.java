import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Problem_784 {
	static final int NMAX = 31;
	static final int MMAX = 81;
	static int N;
	static int M;

	static int[] len = new int[NMAX];
	static char[][] grid = new char[NMAX][MMAX];
	static boolean[][] visitado;

	static void clear() {
		for (int i = 0; i < NMAX; i++) {
			Arrays.fill(grid[i], 'X');
		}
		visitado = new boolean[NMAX][MMAX];
		len = new int[NMAX];
		N = M = 0;
	}

	static void floodFill(Estado inicial) {
		if (!valido(inicial)) {
			return;
		}
		visitado[inicial.x][inicial.y] = true;
		grid[inicial.x][inicial.y] = '#';
		Estado[] adjacentes = inicial.adjacentes();
		for (Estado e : adjacentes) {
			floodFill(e);
		}
	}

	static boolean valido(Estado e) {
		return (e.x >= 0 && e.y >= 0 && e.x < grid.length
				&& e.y < grid[0].length && !ehParede(grid[e.x][e.y]) && !visitado[e.x][e.y]);
	}

	static boolean ehParede(char c) {
		return c != '*' && c != '_' && c != ' ';
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());
		for (int c = 1; c <= casos; c++) {
			clear();

			String linha = null;
			Estado inicial = null;

			int i = -1;
			do {
				i++;
				linha = in.readLine();
				len[i] = linha.length();
				for (int j = 0; j < len[i]; j++) {
					grid[i][j] = linha.charAt(j);

					if (grid[i][j] == '*') {
						inicial = new Estado(i, j);
					}
				}
				M = Math.max(len[i], M);
			} while (linha.charAt(0) != '_');

			N = i;

			floodFill(inicial);

			for (int k = 0; k < N; k++) {
				for (int m = 0; m < len[k]; m++) {
					out.append(grid[k][m]);
				}
				out.append(separador);
			}
			out.append(linha + separador);
		}

		System.out.print(out);
	}
}

class Estado {
	int x, y;

	public Estado(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Estado[] adjacentes() {
		return new Estado[] { new Estado(x + 1, y), new Estado(x - 1, y),
				new Estado(x, y + 1), new Estado(x, y - 1),
				new Estado(x + 1, y + 1), new Estado(x + 1, y - 1),
				new Estado(x - 1, y + 1), new Estado(x - 1, y - 1) };
	}
}