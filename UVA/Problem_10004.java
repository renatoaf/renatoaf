/*
 * Bicoloring
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;

class Problem_10004 {
	static int[] col;
	static int[] grau;
	static Vector<Integer>[] adj;

	static boolean bicolorivel(int no, int color) {
		col[no] = (color + 1) % 2;
		for (int i = 0; i < grau[no]; i++) {
			if (col[adj[no].get(i)] == -1) {
				if (!bicolorivel(adj[no].get(i), col[no])) {
					return false;
				}
			} else if (col[adj[no].get(i)] == col[no]) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			int n = Integer.parseInt(in.readLine());

			if (n == 0) {
				break;
			}

			grau = new int[n];
			col = new int[n];
			adj = new Vector[n];

			Arrays.fill(col, -1);

			int m = Integer.parseInt(in.readLine());
			for (int i = 0; i < m; i++) {
				String[] config = in.readLine().split("\\s+");
				int u = Integer.parseInt(config[0]);
				int v = Integer.parseInt(config[1]);
				if (adj[u] == null) {
					adj[u] = new Vector<Integer>();
				}
				if (adj[v] == null) {
					adj[v] = new Vector<Integer>();
				}
				adj[u].add(v);
				adj[v].add(u);
				grau[u]++;
				grau[v]++;
			}

			saida.append(bicolorivel(0, 0) ? "BICOLORABLE."
					: "NOT BICOLORABLE.");
			saida.append(separador);
		}

		System.out.print(saida);
	}
}