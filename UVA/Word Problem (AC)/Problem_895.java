import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_895 {
	static boolean podeFormar(String palavra, String puzzle) {
		char[] letras = puzzle.replace(" ", "").toCharArray();

		for (int i = 0; i < palavra.length(); i++) {
			boolean achou = false;

			for (int j = 0; j < letras.length; j++) {
				if (palavra.charAt(i) == letras[j]) {
					letras[j] = ' ';
					achou = true;
					break;
				}
			}

			if (!achou) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		ArrayList<String> dicionario = new ArrayList<String>();
		while (true) {
			String palavra = in.readLine();

			if (palavra.equals("#")) {
				break;
			}

			dicionario.add(palavra);
		}

		while (true) {
			String puzzle = in.readLine();

			if (puzzle.equals("#")) {
				break;
			}

			int contador = 0;
			for (String p : dicionario) {
				if (podeFormar(p, puzzle)) {
					contador++;
				}
			}

			saida.append(contador + separador);
		}

		System.out.print(saida);
	}
}