import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class BORABORA {
	static int prox(int atual, boolean horario, int P) {
		if (horario) {
			atual++;
		} else {
			atual--;
		}
		if (atual == -1) {
			return P - 1;
		} else if (atual == P) {
			return 0;
		}
		return atual;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer saida = new StringBuffer();

		while (true) {
			StringTokenizer config = new StringTokenizer(in.readLine());

			int P = Integer.parseInt(config.nextToken());
			int M = Integer.parseInt(config.nextToken());
			int N = Integer.parseInt(config.nextToken());

			if (P == 0 && M == 0 && N == 0) {
				break;
			}

			Stack<Carta> aux = new Stack<Carta>();
			for (int i = 0; i < N; i++) {
				config = new StringTokenizer(in.readLine());
				aux.push(new Carta(Integer.parseInt(config.nextToken()), config
						.nextToken().charAt(0)));
			}

			Stack<Carta> saque = new Stack<Carta>();
			while (!aux.isEmpty()) {
				saque.push(aux.pop());
			}

			Jogador[] players = new Jogador[P];
			for (int i = 0; i < P; i++) {
				players[i] = new Jogador(i + 1);
				for (int j = 0; j < M; j++) {
					players[i].mao.add(saque.pop());
				}
			}

			Carta descartada = saque.pop();
			boolean acabou = false;
			boolean horario = !descartada.damas();
			boolean sete = descartada.sete();
			boolean valete = descartada.valete();
			boolean as = descartada.as();
			Jogador vencedor = null;

			int atual = 0;
			while (!acabou) {
				if (sete) {
					players[atual].mao.add(saque.pop());
					players[atual].mao.add(saque.pop());
					sete = false;
				} else if (valete) {
					valete = false;
				} else if (as) {
					players[atual].mao.add(saque.pop());
					as = false;
				} else {
					Carta descartou = players[atual]
							.escolherDescarte(descartada);

					boolean descartouNova = false;
					if (descartou == null) {
						Carta compra = saque.pop();
						if (compra.naipe == descartada.naipe
								|| compra.valor == descartada.valor) {
							descartada = compra;
							descartouNova = true;
						} else {
							players[atual].mao.add(compra);
						}
					} else {
						descartada = descartou;
						descartouNova = true;
					}

					if (descartouNova) {
						if (descartada.damas()) {
							horario = !horario;
						}
						sete = descartada.sete();
						valete = descartada.valete();
						as = descartada.as();
					}
				}

				if (players[atual].venceu()) {
					acabou = true;
					vencedor = players[atual];
				}
				atual = prox(atual, horario, P);
			}

			saida.append(vencedor.id + "\n");
		}

		System.out.print(saida);
	}
}

class Carta implements Comparable<Carta> {
	static final int PAUS = 1;
	static final int OURO = 2;
	static final int COPAS = 3;
	static final int ESPADAS = 4;
	int valor;
	int naipe;

	public Carta(int valor, char naipe) {
		switch (naipe) {
		case 'C':
			this.naipe = PAUS;
			break;
		case 'D':
			this.naipe = OURO;
			break;
		case 'H':
			this.naipe = COPAS;
			break;
		case 'S':
			this.naipe = ESPADAS;
			break;
		}
		this.valor = valor;
	}

	public boolean damas() {
		return valor == 12;
	}

	public boolean sete() {
		return valor == 7;
	}

	public boolean as() {
		return valor == 1;
	}

	public boolean valete() {
		return valor == 11;
	}

	public int compareTo(Carta c) {
		if (c.valor < valor) {
			return 1;
		} else if (c.valor > valor) {
			return -1;
		} else {
			if (c.naipe < naipe) {
				return 1;
			} else if (c.naipe > naipe) {
				return -1;
			}
		}
		return 0;
	}
}

class Jogador {
	int id;
	LinkedList<Carta> mao;

	public Jogador(int id) {
		this.id = id;
		this.mao = new LinkedList<Carta>();
	}

	public boolean venceu() {
		return mao.isEmpty();
	}

	public Carta escolherDescarte(Carta descartada) {
		Carta descartar = null;
		for (Carta c : mao) {
			if (c.naipe == descartada.naipe || c.valor == descartada.valor) {
				if (descartar == null) {
					descartar = c;
				} else if (c.compareTo(descartar) > 0) {
					descartar = c;
				}
			}
		}
		if (descartar != null) {
			mao.remove(descartar);
		}
		return descartar;
	}
}
