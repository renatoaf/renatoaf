import java.util.Scanner;

class Problem_10260 {
	private static int codigoSoundex(char c) {
		if (c == 'B' || c == 'F' || c == 'P' || c == 'V') {
			return 1;
		} else if (c == 'C' || c == 'G' || c == 'J' || c == 'K' || c == 'Q'
				|| c == 'S' || c == 'X' || c == 'Z') {
			return 2;
		} else if (c == 'D' || c == 'T') {
			return 3;
		} else if (c == 'L') {
			return 4;
		} else if (c == 'M' || c == 'N') {
			return 5;
		} else if (c == 'R') {
			return 6;
		}
		return -1;
	}

	private static String soundex(String p) {
		String codificada = "";
		int codigoAnterior = 0;
		int codigoAtual = 0;
		for (int i = 0; i < p.length(); i++) {
			codigoAtual = codigoSoundex(p.charAt(i));
			if (codigoAtual != -1 && (i == 0 || codigoAnterior != codigoAtual)) {
				codificada += codigoAtual;
			}
			codigoAnterior = codigoAtual;
		}
		return codificada;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		while (in.hasNext()) {
			saida.append(soundex(in.nextLine()) + separador);
		}

		System.out.print(saida);
	}
}