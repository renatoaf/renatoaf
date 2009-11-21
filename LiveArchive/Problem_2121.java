/*
 * Maze Checking and Visualization 	
 */

import java.util.StringTokenizer;

class Problem_2121 {
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
			StringTokenizer prox = new StringTokenizer(linha);

			int m = Integer.parseInt(prox.nextToken());
			int n = Integer.parseInt(prox.nextToken());

			if (m == 0 && n == 0) {
				break;
			}

			Prisao pris = new Prisao(m, n);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					pris.maze[i][j] = new Cela(prox.nextToken());
				}
			}

			saida.append("Maze " + c++ + ":" + "\n\n" + pris + "\n\n");
		}

		System.out.print(saida);
	}
}

class Prisao {
	int m;
	int n;
	Cela[][] maze;

	public Prisao(int m, int n) {
		this.m = m;
		this.n = n;
		this.maze = new Cela[m][n];
	}

	public Cela get(int i, int j) {
		if ((i >= m || i < 0) || (j >= n || j < 0)) {
			return null;
		}
		return maze[i][j];
	}

	public String toString() {
		String saida = "";
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				Cela cAtual = get(i, j);
				Cela cima = get(i - 1, j);
				saida += cAtual.topo(cima);
			}
			saida += "+\n";
			for (int j = 0; j < n; j++) {
				Cela cAtual = get(i, j);
				Cela esq = get(i, j - 1);
				saida += cAtual.esq(esq);
				if (j == n - 1) {
					saida += cAtual.dir();
				}
			}
			saida += "\n";
		}

		for (int j = 0; j < n; j++) {
			saida += get(m - 1, j).baixo();
		}
		saida += "+";
		return saida;
	}
}

class Cela {
	boolean cima;
	boolean baixo;
	boolean direita;
	boolean esquerda;

	public Cela(String hex) {
		int n = Integer.parseInt(hex, 16);
		String bin = Integer.toBinaryString(n);
		while (bin.length() < 4) {
			bin = "0" + bin;
		}
		cima = valor(bin.charAt(3));
		direita = valor(bin.charAt(2));
		baixo = valor(bin.charAt(1));
		esquerda = valor(bin.charAt(0));
	}

	public boolean valor(char c) {
		return c == '1' ? true : false;
	}

	public String topo(Cela adjacente) {
		if (adjacente == null && cima) {
			return "+   ";
		} else if (adjacente == null && !cima) {
			return "+---";
		} else if (adjacente != null) {
			if ((adjacente.baixo && !cima) || (!adjacente.baixo && cima)) {
				return "+xxx";
			} else if (!adjacente.baixo && !cima) {
				return "+---";
			}
		}
		return "+   ";
	}

	public String baixo() {
		if (baixo) {
			return "+   ";
		}
		return "+---";
	}

	public String esq(Cela adjacente) {
		if (adjacente == null && esquerda) {
			return "    ";
		} else if (adjacente == null && !esquerda) {
			return "|   ";
		} else if (adjacente != null) {
			if ((adjacente.direita && !esquerda)
					|| (!adjacente.direita && esquerda)) {
				return "X   ";
			} else if (!adjacente.direita && !esquerda) {
				return "|   ";
			}
		}
		return "    ";
	}

	public String dir() {
		if (direita) {
			return " ";
		}
		return "|";
	}
}