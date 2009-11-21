import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_118 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		String[] xy = in.readLine().split("\\s+");

		ArrayList<Ponto> posicoesPerdidas = new ArrayList<Ponto>();
		Ponto limite = new Ponto(Integer.parseInt(xy[0]), Integer
				.parseInt(xy[1]));

		while (true) {
			String linha = in.readLine();
			if (linha == null) {
				break;
			}
			String[] pos = linha.split("\\s+");

			Ponto posicaoInicial = new Ponto(Integer.parseInt(pos[0]), Integer
					.parseInt(pos[1]));
			char ladoInicial = pos[2].charAt(0);

			Robo r = new Robo(posicaoInicial, limite, ladoInicial,
					posicoesPerdidas);

			String instrucoes = in.readLine();
			for (int i = 0; i < instrucoes.length(); i++) {
				char instrucao = instrucoes.charAt(i);
				switch (instrucao) {
				case 'F':
					r.forward();
					break;
				case 'R':
					r.right();
					break;
				case 'L':
					r.left();
					break;
				}
			}
			saida.append(r + separador);
		}

		System.out.print(saida);
	}
}

class Robo {
	private static final char LESTE = 'E';
	private static final char OESTE = 'W';
	private static final char NORTE = 'N';
	private static final char SUL = 'S';

	private ArrayList<Ponto> posicoesPerdidas;
	private Ponto posicao;
	private Ponto limite;
	private boolean lost;
	private char lado;

	public Robo(Ponto posicaoInicial, Ponto limite, char ladoInicial,
			ArrayList<Ponto> posicoesPerdidas) {
		this.posicoesPerdidas = posicoesPerdidas;
		this.posicao = posicaoInicial;
		this.limite = limite;
		this.lado = ladoInicial;
	}

	private boolean estaPerdido() {
		return posicao.x > limite.x || posicao.y > limite.y || posicao.x < 0
				|| posicao.y < 0;
	}

	public void right() {
		if (!lost) {
			switch (lado) {
			case LESTE:
				lado = SUL;
				break;
			case SUL:
				lado = OESTE;
				break;
			case OESTE:
				lado = NORTE;
				break;
			case NORTE:
				lado = LESTE;
				break;
			}
		}
	}

	public void left() {
		if (!lost) {
			switch (lado) {
			case LESTE:
				lado = NORTE;
				break;
			case NORTE:
				lado = OESTE;
				break;
			case OESTE:
				lado = SUL;
				break;
			case SUL:
				lado = LESTE;
				break;
			}
		}
	}

	public void forward() {
		int x = posicao.x;
		int y = posicao.y;

		if (!lost) {
			switch (lado) {
			case LESTE:
				posicao.x++;
				break;
			case OESTE:
				posicao.x--;
				break;
			case NORTE:
				posicao.y++;
				break;
			case SUL:
				posicao.y--;
				break;
			}
		}

		if (estaPerdido()) {
			posicao.x = x;
			posicao.y = y;

			if (!posicoesPerdidas.contains(posicao)) {
				lost = true;
				posicoesPerdidas.add(posicao);
			}
		}
	}

	public String toString() {
		return posicao + " " + lado + (lost ? " LOST" : "");
	}
}

class Ponto {
	int x;
	int y;

	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return x + " " + y;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Ponto)) {
			return false;
		}
		Ponto p = (Ponto) o;
		return p.x == x && p.y == y;
	}
}