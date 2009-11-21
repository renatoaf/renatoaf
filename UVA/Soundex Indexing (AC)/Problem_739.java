import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_739 {
	static String formataNum(String n) {
		while (n.length() < 3) {
			n += "0";
		}
		return n.substring(0, 3);
	}

	static String formata(String nome, String codigo) {
		String retorno = "         " + nome;
		while (retorno.length() < 34) {
			retorno += " ";
		}
		return retorno + codigo;
	}

	static int codigoSoundex(char c) {
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

	static String soundexIndexing(String p) {
		String codificada = "";
		int codigoAnterior = codigoSoundex(p.charAt(0));
		int codigoAtual = 0;
		for (int i = 1; i < p.length(); i++) {
			codigoAtual = codigoSoundex(p.charAt(i));
			if (codigoAtual != -1 && codigoAnterior != codigoAtual) {
				codificada += codigoAtual;
			}
			codigoAnterior = codigoAtual;
		}
		return p.charAt(0) + formataNum(codificada);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		saida.append(formata("NAME", "SOUNDEX CODE") + separador);
		while (true) {
			String palavra = in.readLine();

			if (palavra == null) {
				saida.append("                   END OF OUTPUT" + separador);
				break;
			}

			saida
					.append(formata(palavra, soundexIndexing(palavra))
							+ separador);
		}

		System.out.print(saida);
	}
}