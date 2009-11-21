import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10196 {
	static final int N = 8;
	static final int M = 8;

	static final char LIVRE = '.';

	static final char PEAO_PRETO = 'p';
	static final char TORRE_PRETA = 'r';
	static final char BISPO_PRETO = 'b';
	static final char RAINHA_PRETA = 'q';
	static final char CAVALO_PRETO = 'n';
	static final char REI_PRETO = 'k';

	static final char PEAO_BRANCO = 'P';
	static final char TORRE_BRANCA = 'R';
	static final char BISPO_BRANCO = 'B';
	static final char RAINHA_BRANCA = 'Q';
	static final char CAVALO_BRANCO = 'N';
	static final char REI_BRANCO = 'K';

	public static boolean verificaDiagonaisPreto(char[][] tabuleiro, int x,
			int y) {

		boolean achouSudeste = false;
		boolean achouSudoeste = false;
		boolean achouNordeste = false;
		boolean achouNoroeste = false;

		for (int i = 1; i <= N; i++) {
			if (x + i < M && y + i < N && !achouSudeste) {
				if (i == 1
						&& (tabuleiro[x + i][y + i] == REI_BRANCO || tabuleiro[x
								+ i][y + i] == PEAO_BRANCO)) {
					return true;
				} else if (tabuleiro[x + i][y + i] == BISPO_BRANCO
						|| tabuleiro[x + i][y + i] == RAINHA_BRANCA) {
					return true;
				} else if (tabuleiro[x + i][y + i] != LIVRE) {
					achouSudeste = true;
				}
			}

			if (x + i < M && y - i >= 0 && !achouSudoeste) {
				if (i == 1
						&& (tabuleiro[x + i][y - i] == REI_BRANCO || tabuleiro[x
								+ i][y - i] == PEAO_BRANCO)) {
					return true;
				} else if (tabuleiro[x + i][y - i] == BISPO_BRANCO
						|| tabuleiro[x + i][y - i] == RAINHA_BRANCA) {
					return true;
				} else if (tabuleiro[x + i][y - i] != LIVRE) {
					achouSudoeste = true;
				}
			}

			if (x - i >= 0 && y + i < N && !achouNordeste) {
				if (i == 1 && tabuleiro[x - i][y + i] == REI_BRANCO) {
					return true;
				} else if (tabuleiro[x - i][y + i] == BISPO_BRANCO
						|| tabuleiro[x - i][y + i] == RAINHA_BRANCA) {
					return true;
				} else if (tabuleiro[x - i][y + i] != LIVRE) {
					achouNordeste = true;
				}
			}

			if (x - i >= 0 && y - i >= 0 && !achouNoroeste) {
				if (i == 1 && tabuleiro[x - i][y - i] == REI_BRANCO) {
					return true;
				} else if (tabuleiro[x - i][y - i] == BISPO_BRANCO
						|| tabuleiro[x - i][y - i] == RAINHA_BRANCA) {
					return true;
				} else if (tabuleiro[x - i][y - i] != LIVRE) {
					achouNoroeste = true;
				}
			}
		}

		return false;
	}

	public static boolean verificaRetasPreto(char[][] tabuleiro, int x, int y) {
		boolean achouNorte = false;
		boolean achouSul = false;
		boolean achouLeste = false;
		boolean achouOeste = false;

		for (int i = 1; i <= N; i++) {
			if (x - i >= 0 && !achouNorte) {
				if (i == 1 && tabuleiro[x - i][y] == REI_BRANCO) {
					return true;
				} else if (tabuleiro[x - i][y] == RAINHA_BRANCA
						|| tabuleiro[x - i][y] == TORRE_BRANCA) {
					return true;
				} else if (tabuleiro[x - i][y] != LIVRE) {
					achouNorte = true;
				}
			}

			if (x + i < M && !achouSul) {
				if (i == 1 && tabuleiro[x + i][y] == REI_BRANCO) {
					return true;
				} else if (tabuleiro[x + i][y] == RAINHA_BRANCA
						|| tabuleiro[x + i][y] == TORRE_BRANCA) {
					return true;
				} else if (tabuleiro[x + i][y] != LIVRE) {
					achouSul = true;
				}
			}

			if (y + i < N && !achouLeste) {
				if (i == 1 && tabuleiro[x][y + i] == REI_BRANCO) {
					return true;
				} else if (tabuleiro[x][y + i] == RAINHA_BRANCA
						|| tabuleiro[x][y + i] == TORRE_BRANCA) {
					return true;
				} else if (tabuleiro[x][y + i] != LIVRE) {
					achouLeste = true;
				}
			}

			if (y - i >= 0 && !achouOeste) {
				if (i == 1 && tabuleiro[x][y - i] == REI_BRANCO) {
					return true;
				} else if (tabuleiro[x][y - i] == RAINHA_BRANCA
						|| tabuleiro[x][y - i] == TORRE_BRANCA) {
					return true;
				} else if (tabuleiro[x][y - i] != LIVRE) {
					achouOeste = true;
				}
			}
		}

		return false;
	}

	public static boolean verificaCavaloPreto(char[][] tabuleiro, int x, int y) {
		return (y - 1 >= 0 && x + 2 < M && tabuleiro[x + 2][y - 1] == CAVALO_BRANCO)
				|| (y - 1 >= 0 && x - 2 >= 0 && tabuleiro[x - 2][y - 1] == CAVALO_BRANCO)
				|| (y + 1 < N && x + 2 < M && tabuleiro[x + 2][y + 1] == CAVALO_BRANCO)
				|| (y + 1 < N && x - 2 >= 0 && tabuleiro[x - 2][y + 1] == CAVALO_BRANCO)
				|| (y + 2 < N && x - 1 >= 0 && tabuleiro[x - 1][y + 2] == CAVALO_BRANCO)
				|| (y + 2 < N && x + 1 < M && tabuleiro[x + 1][y + 2] == CAVALO_BRANCO)
				|| (y - 2 >= 0 && x - 1 >= 0 && tabuleiro[x - 1][y - 2] == CAVALO_BRANCO)
				|| (y - 2 >= 0 && x + 1 < M && tabuleiro[x + 1][y - 2] == CAVALO_BRANCO);
	}

	public static boolean verificaDiagonaisBranco(char[][] tabuleiro, int x,
			int y) {

		boolean achouSudeste = false;
		boolean achouSudoeste = false;
		boolean achouNordeste = false;
		boolean achouNoroeste = false;

		for (int i = 1; i <= N; i++) {
			if (x + i < M && y + i < N && !achouSudeste) {
				if (i == 1 && tabuleiro[x + i][y + i] == REI_PRETO) {
					return true;
				} else if (tabuleiro[x + i][y + i] == BISPO_PRETO
						|| tabuleiro[x + i][y + i] == RAINHA_PRETA) {
					return true;
				} else if (tabuleiro[x + i][y + i] != LIVRE) {
					achouSudeste = true;
				}
			}

			if (x + i < M && y - i >= 0 && !achouSudoeste) {
				if (i == 1 && tabuleiro[x + i][y - i] == REI_PRETO) {
					return true;
				} else if (tabuleiro[x + i][y - i] == BISPO_PRETO
						|| tabuleiro[x + i][y - i] == RAINHA_PRETA) {
					return true;
				} else if (tabuleiro[x + i][y - i] != LIVRE) {
					achouSudoeste = true;
				}
			}

			if (x - i >= 0 && y + i < N && !achouNordeste) {
				if (i == 1
						&& (tabuleiro[x - i][y + i] == REI_PRETO || tabuleiro[x
								- i][y + i] == PEAO_PRETO)) {
					return true;
				} else if (tabuleiro[x - i][y + i] == BISPO_PRETO
						|| tabuleiro[x - i][y + i] == RAINHA_PRETA) {
					return true;
				} else if (tabuleiro[x - i][y + i] != LIVRE) {
					achouNordeste = true;
				}
			}

			if (x - i >= 0 && y - i >= 0 && !achouNoroeste) {
				if (i == 1
						&& (tabuleiro[x - i][y - i] == REI_PRETO || tabuleiro[x
								- i][y - i] == PEAO_PRETO)) {
					return true;
				} else if (tabuleiro[x - i][y - i] == BISPO_PRETO
						|| tabuleiro[x - i][y - i] == RAINHA_PRETA) {
					return true;
				} else if (tabuleiro[x - i][y - i] != LIVRE) {
					achouNoroeste = true;
				}
			}
		}

		return false;
	}

	public static boolean verificaRetasBranco(char[][] tabuleiro, int x, int y) {
		boolean achouNorte = false;
		boolean achouSul = false;
		boolean achouLeste = false;
		boolean achouOeste = false;

		for (int i = 1; i <= N; i++) {
			if (x - i >= 0 && !achouNorte) {
				if (i == 1 && tabuleiro[x - i][y] == REI_PRETO) {
					return true;
				} else if (tabuleiro[x - i][y] == RAINHA_PRETA
						|| tabuleiro[x - i][y] == TORRE_PRETA) {
					return true;
				} else if (tabuleiro[x - i][y] != LIVRE) {
					achouNorte = true;
				}
			}

			if (x + i < M && !achouSul) {
				if (i == 1 && tabuleiro[x + i][y] == REI_PRETO) {
					return true;
				} else if (tabuleiro[x + i][y] == RAINHA_PRETA
						|| tabuleiro[x + i][y] == TORRE_PRETA) {
					return true;
				} else if (tabuleiro[x + i][y] != LIVRE) {
					achouSul = true;
				}
			}

			if (y + i < N && !achouLeste) {
				if (i == 1 && tabuleiro[x][y + i] == REI_PRETO) {
					return true;
				} else if (tabuleiro[x][y + i] == RAINHA_PRETA
						|| tabuleiro[x][y + i] == TORRE_PRETA) {
					return true;
				} else if (tabuleiro[x][y + i] != LIVRE) {
					achouLeste = true;
				}
			}

			if (y - i >= 0 && !achouOeste) {
				if (i == 1 && tabuleiro[x][y - i] == REI_PRETO) {
					return true;
				} else if (tabuleiro[x][y - i] == RAINHA_PRETA
						|| tabuleiro[x][y - i] == TORRE_PRETA) {
					return true;
				} else if (tabuleiro[x][y - i] != LIVRE) {
					achouOeste = true;
				}
			}
		}

		return false;
	}

	public static boolean verificaCavaloBranco(char[][] tabuleiro, int x, int y) {
		return (y - 1 >= 0 && x + 2 < M && tabuleiro[x + 2][y - 1] == CAVALO_PRETO)
				|| (y - 1 >= 0 && x - 2 >= 0 && tabuleiro[x - 2][y - 1] == CAVALO_PRETO)
				|| (y + 1 < N && x + 2 < M && tabuleiro[x + 2][y + 1] == CAVALO_PRETO)
				|| (y + 1 < N && x - 2 >= 0 && tabuleiro[x - 2][y + 1] == CAVALO_PRETO)
				|| (y + 2 < N && x - 1 >= 0 && tabuleiro[x - 1][y + 2] == CAVALO_PRETO)
				|| (y + 2 < N && x + 1 < M && tabuleiro[x + 1][y + 2] == CAVALO_PRETO)
				|| (y - 2 >= 0 && x - 1 >= 0 && tabuleiro[x - 1][y - 2] == CAVALO_PRETO)
				|| (y - 2 >= 0 && x + 1 < M && tabuleiro[x + 1][y - 2] == CAVALO_PRETO);
	}

	public static boolean reiBrancoEmCheque(char[][] tabuleiro, int x, int y) {
		return verificaCavaloBranco(tabuleiro, x, y)
				|| verificaDiagonaisBranco(tabuleiro, x, y)
				|| verificaRetasBranco(tabuleiro, x, y);
	}

	public static boolean reiPretoEmCheque(char[][] tabuleiro, int x, int y) {
		return verificaCavaloPreto(tabuleiro, x, y)
				|| verificaDiagonaisPreto(tabuleiro, x, y)
				|| verificaRetasPreto(tabuleiro, x, y);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int caso = 1;
		while (true) {
			if (caso != 1) {
				in.readLine();
			}

			boolean acabou = true;

			char[][] tabuleiro = new char[N][M];

			int iReiPreto = 0;
			int jReiPreto = 0;
			int iReiBranco = 0;
			int jReiBranco = 0;

			for (int i = 0; i < M; i++) {
				String linha = in.readLine();
				for (int j = 0; j < N; j++) {
					char peca = linha.charAt(j);
					if (peca != LIVRE) {
						acabou = false;

						if (peca == REI_PRETO) {
							iReiPreto = i;
							jReiPreto = j;
						} else if (peca == REI_BRANCO) {
							iReiBranco = i;
							jReiBranco = j;
						}
					}
					tabuleiro[i][j] = peca;
				}
			}

			if (acabou) {
				break;
			}

			if (reiPretoEmCheque(tabuleiro, iReiPreto, jReiPreto)) {
				saida.append("Game #" + caso + ": black king is in check.");
			} else if (reiBrancoEmCheque(tabuleiro, iReiBranco, jReiBranco)) {
				saida.append("Game #" + caso + ": white king is in check.");
			} else {
				saida.append("Game #" + caso + ": no king is in check.");
			}

			saida.append(separador);
			caso++;
		}

		System.out.print(saida);
	}
}