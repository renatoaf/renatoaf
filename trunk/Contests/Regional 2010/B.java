import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		while (true) {
			int ROWS = 0, COLS = 0, S;

			StringTokenizer linha = new StringTokenizer(in.readLine());
			ROWS = Integer.parseInt(linha.nextToken());
			COLS = Integer.parseInt(linha.nextToken());
			S = Integer.parseInt(linha.nextToken());

			if (ROWS == 0 && COLS == 0 && S == 0)
				break;

			char[][] grid = new char[ROWS][COLS];

			char orientacao = 0;
			int x = 0, y = 0;
			for (int i = 0; i < ROWS; i++) {
				grid[i] = in.readLine().toCharArray();
				for (int j = 0; j < COLS; j++) {
					if (grid[i][j] != '.' && grid[i][j] != '*'
							&& grid[i][j] != '#') {
						x = i;
						y = j;
						orientacao = grid[i][j];
					}
				}
			}

			Robo r = new Robo(ROWS, COLS, x, y, orientacao, grid);

			String instrucoes = in.readLine();
			for (int i = 0; i < S; i++) {
				char instrucao = instrucoes.charAt(i);
				r.processaInstrucao(instrucao);
			}

			out.append(r.getContagem() + "\n");
		}

		System.out.print(out);
	}
}

class Robo {
	enum Orientacao {
		NORTE, SUL, LESTE, OESTE;
	}

	Orientacao orientacao;
	int rows, cols, x, y;
	int contagem;
	char grid[][];

	public Robo(int rows, int cols, int x, int y, char o, char[][] grid) {
		this.x = x;
		this.y = y;
		this.orientacao = getOrientacao(o);
		this.rows = rows;
		this.cols = cols;
		this.grid = grid;
	}

	public void processaInstrucao(char instrucao) {
		switch (instrucao) {
		case 'D':
			rotateRight();
			break;
		case 'E':
			rotateLeft();
			break;
		case 'F':
			forward();
			break;
		}
	}

	public int getContagem() {
		return contagem;
	}

	public boolean checaLimites(int x, int y) {
		return x >= 0 && y >= 0 && x < rows && y < cols;
	}

	public void forward() {
		switch (orientacao) {
		case NORTE:
			if (checaLimites(x - 1, y) && grid[x - 1][y] != '#') {
				x--;
			}
			break;
		case LESTE:
			if (checaLimites(x, y + 1) && grid[x][y + 1] != '#') {
				y++;
			}
			break;
		case SUL:
			if (checaLimites(x + 1, y) && grid[x + 1][y] != '#') {
				x++;
			}
			break;
		case OESTE:
			if (checaLimites(x, y - 1) && grid[x][y - 1] != '#') {
				y--;
			}
			break;
		}

		if (grid[x][y] == '*') {
			contagem++;
			grid[x][y] = '.';
		}
	}

	public void rotateRight() {
		switch (orientacao) {
		case NORTE:
			orientacao = Orientacao.LESTE;
			break;
		case LESTE:
			orientacao = Orientacao.SUL;
			break;
		case SUL:
			orientacao = Orientacao.OESTE;
			break;
		case OESTE:
			orientacao = Orientacao.NORTE;
			break;
		}
	}

	public void rotateLeft() {
		switch (orientacao) {
		case NORTE:
			orientacao = Orientacao.OESTE;
			break;
		case OESTE:
			orientacao = Orientacao.SUL;
			break;
		case SUL:
			orientacao = Orientacao.LESTE;
			break;
		case LESTE:
			orientacao = Orientacao.NORTE;
			break;
		}
	}

	private Orientacao getOrientacao(char o) {
		switch (o) {
		case 'N':
			return Orientacao.NORTE;
		case 'S':
			return Orientacao.SUL;
		case 'L':
			return Orientacao.LESTE;
		default:
			return Orientacao.OESTE;
		}
	}
}