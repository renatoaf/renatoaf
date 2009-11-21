/*
 * Dungeon Master
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Problem_532 {
	static int bfs(Node comeco, Node fim, char[][][] grid, int N, int L, int C) {
		boolean[][][] visitado = new boolean[N][L][C];
		Queue<Node> fila = new LinkedList<Node>();
		fila.add(comeco);

		while (!fila.isEmpty()) {
			Node atual = fila.remove();

			int x = atual.x;
			int y = atual.y;
			int z = atual.z;

			if (x < 0 || y < 0 || z < 0 || x >= L || y >= C || z >= N
					|| grid[z][x][y] == '#' || visitado[z][x][y]) {
				continue;
			} else if (atual.equals(fim)) {
				return atual.tempo;
			}

			visitado[z][x][y] = true;
			fila.add(new Node(x, y, z + 1, atual.tempo + 1));
			fila.add(new Node(x, y, z - 1, atual.tempo + 1));
			fila.add(new Node(x, y + 1, z, atual.tempo + 1));
			fila.add(new Node(x, y - 1, z, atual.tempo + 1));
			fila.add(new Node(x + 1, y, z, atual.tempo + 1));
			fila.add(new Node(x - 1, y, z, atual.tempo + 1));
		}

		return -1; // nao chegou
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String[] config = in.readLine().split("\\s+");
			int niveis = Integer.parseInt(config[0]);
			int linhas = Integer.parseInt(config[1]);
			int colunas = Integer.parseInt(config[2]);

			if (niveis == 0 && linhas == 0 && colunas == 0) {
				break;
			}

			Node comeco = null;
			Node fim = null;

			char[][][] grid = new char[niveis][linhas][colunas];
			for (int k = 0; k < niveis; k++) {
				for (int i = 0; i < linhas; i++) {
					grid[k][i] = in.readLine().toCharArray();
					if (comeco == null || fim == null) {
						for (int j = 0; j < colunas; j++) {
							if (grid[k][i][j] == 'S') {
								comeco = new Node(i, j, k, 0);
							} else if (grid[k][i][j] == 'E') {
								fim = new Node(i, j, k, 0);
							}
						}
					}
				}
				in.readLine();
			}

			int res = bfs(comeco, fim, grid, niveis, linhas, colunas);

			out.append(((res == -1) ? ("Trapped!")
					: ("Escaped in " + res + " minute(s)."))
					+ separador);
		}

		System.out.print(out);
	}
}

class Node {
	int x, y, z, tempo;

	public Node(int x, int y, int z, int tempo) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.tempo = tempo;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Node)) {
			return false;
		}
		Node aux = (Node) o;
		return x == aux.x && y == aux.y && z == aux.z;
	}

	public String toString() { // debug
		return "(" + z + "," + x + "," + y + ")";
	}
}