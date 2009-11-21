import java.util.StringTokenizer;

class Problem_3756 {
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

		int c = 1;
		while (true) {
			String linha = readln();

			int n = Integer.parseInt(linha);
			if (n == 0) {
				break;
			}

			Tetris t = new Tetris(n);
			for (int i = 0; i < n; i++) {
				StringTokenizer token = new StringTokenizer(readln());
				for (int j = 0; j < n; j++) {
					t.matriz[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			saida.append(c++ + ". " + t.getMaiorSoma() + "\n");
		}
		System.out.print(saida);
	}
}

class Tetris {
	int[][] matriz;
	int n;
	int maiorSoma;

	public Tetris(int n) {
		this.n = n;
		this.matriz = new int[n][n];
	}

	public int getMaiorSoma() {
		maiorSoma = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				checaReta(i, j);
				checaL(i, j);
				checaZ(i, j);
				checaT(i, j);
				checaQuadrado(i, j);
			}
		}
		return maiorSoma;
	}

	public void checaReta(int i, int j) {
		if (i + 3 < n) {
			int soma = matriz[j][i] + matriz[j][i + 1] + matriz[j][i + 2]
					+ matriz[j][i + 3];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (j + 3 < n) {
			int soma = matriz[j][i] + matriz[j + 1][i] + matriz[j + 2][i]
					+ matriz[j + 3][i];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
	}

	public void checaZ(int i, int j) {
		if (i + 2 < n && j + 1 < n) {
			int soma = matriz[j][i] + matriz[j][i + 1] + matriz[j + 1][i + 1]
					+ matriz[j + 1][i + 2];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (j + 2 < n && i - 1 >= 0) {
			int soma = matriz[j][i] + matriz[j + 1][i] + matriz[j + 1][i - 1]
					+ matriz[j + 2][i - 1];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
	}

	public void checaL(int i, int j) {
		if (i + 2 < n && j + 1 < n) {
			int soma = matriz[j][i] + matriz[j][i + 1] + matriz[j][i + 2]
					+ matriz[j + 1][i + 2];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (i + 1 < n && j + 2 < n) {
			int soma = matriz[j][i] + matriz[j][i + 1] + matriz[j + 1][i]
					+ matriz[j + 2][i];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (j + 1 < n && i + 2 < n) {
			int soma = matriz[j][i] + matriz[j + 1][i] + matriz[j + 1][i + 1]
					+ matriz[j + 1][i + 2];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (j + 2 < n && i - 1 >= 0) {
			int soma = matriz[j][i] + matriz[j + 1][i] + matriz[j + 2][i]
					+ matriz[j + 2][i - 1];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
	}

	public void checaT(int i, int j) {
		if (i + 2 < n && j + 1 < n) {
			int soma = matriz[j][i] + matriz[j][i + 1] + matriz[j][i + 2]
					+ matriz[j + 1][i + 1];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (j + 2 < n && i + 1 < n) {
			int soma = matriz[j][i] + matriz[j + 1][i] + matriz[j + 2][i]
					+ matriz[j + 1][i + 1];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (j + 2 < n && i - 1 >= 0) {
			int soma = matriz[j][i] + matriz[j + 1][i] + matriz[j + 2][i]
					+ matriz[j + 1][i - 1];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
		if (i - 1 >= 0 && i + 1 < n && j + 1 < n) {
			int soma = matriz[j][i] + matriz[j + 1][i - 1] + matriz[j + 1][i]
					+ matriz[j + 1][i + 1];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
	}

	public void checaQuadrado(int i, int j) {
		if (i + 1 < n && j + 1 < n) {
			int soma = matriz[j][i] + matriz[j][i + 1] + matriz[j + 1][i]
					+ matriz[j + 1][i + 1];
			if (soma > maiorSoma) {
				maiorSoma = soma;
			}
		}
	}
}