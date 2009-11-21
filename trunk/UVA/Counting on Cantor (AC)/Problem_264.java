import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Problem_264 {
	private static int i;
	private static int j;

	public static boolean ehPar(int num) {
		return num % 2 == 0;
	}

	public static boolean ehImpar(int num) {
		return !ehPar(num);
	}

	public static void direita() {
		j++;
	}

	public static void esquerda() {
		j--;
	}

	public static void baixo() {
		i++;
	}

	public static void cima() {
		i--;
	}

	public static void diagonalBaixoEsquerda() {
		baixo();
		esquerda();
	}

	public static void diagonalCimaDireita() {
		cima();
		direita();
	}

	public static String getTermo(int termo) {
		i = j = 1;

		int k = 1;
		while (k < termo) {
			if (i == 1) {
				if (ehImpar(j)) {
					direita();
					while (i != 1 && j != 1 && k + 1 < termo) {
						direita();
						k++;
					}
				} else {
					diagonalBaixoEsquerda();
					while (i != 1 && j != 1 && k + 1 < termo) {
						diagonalBaixoEsquerda();
						k++;
					}
				}
			}

			else if (j == 1) {
				if (ehPar(i)) {
					baixo();
					while (i != 1 && j != 1 && k + 1 < termo) {
						baixo();
						k++;
					}
				} else {
					diagonalCimaDireita();
					while (i != 1 && j != 1 && k + 1 < termo) {
						diagonalCimaDireita();
						k++;
					}
				}
			}

			k++;
		}
		return i + "/" + j;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				(System.in))));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (in.hasNext()) {
			int termo = in.nextInt();
			saida
					.append("TERM " + termo + " IS " + getTermo(termo)
							+ separador);
		}

		System.out.print(saida);
	}
}