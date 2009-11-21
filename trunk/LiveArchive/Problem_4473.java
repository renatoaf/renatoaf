/*
 * Brothers
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class B {
	static int[][] adj = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		while (true) {
			String[] config = in.readLine().split("\\s+");
			int N = Integer.parseInt(config[0]);
			int R = Integer.parseInt(config[1]);
			int C = Integer.parseInt(config[2]);
			int K = Integer.parseInt(config[3]);

			if (N == 0 && R == 0 && C == 0 && K == 0) {
				break;
			}

			int[][] campo = new int[R][C];
			for (int i = 0; i < R; i++) {
				config = in.readLine().split("\\s+");
				for (int j = 0; j < C; j++) {
					campo[i][j] = Integer.parseInt(config[j]);
				}
			}

			for (int k = 0; k < K; k++) {
				int[][] novoCampo = new int[R][C];
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						novoCampo[i][j] = campo[i][j];
					}
				}

				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						for (int d = 0; d < 4; d++) {
							int dx = i + adj[d][0];
							int dy = j + adj[d][1];
							if (dx < R && dy < C && dx >= 0 && dy >= 0) {
								int adversario = (campo[i][j] + 1) % N;
								if (adversario == campo[dx][dy]) {
									novoCampo[dx][dy] = campo[i][j];
								}
							}
						}
					}
				}

				campo = novoCampo;
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					out.append(campo[i][j]);
					if (j != C - 1) {
						out.append(" ");
					}
				}
				out.append("\n");
			}
		}

		System.out.print(out);
	}
}