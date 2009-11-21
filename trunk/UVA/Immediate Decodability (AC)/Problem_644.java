import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_644 {
	static boolean imediatamenteDecodificavel(ArrayList<String> codigos) {
		for (int i = 0; i < codigos.size(); i++) {
			for (int j = 0; j < codigos.size(); j++) {
				if (i != j && codigos.get(i).startsWith(codigos.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int caso = 1;
		while (true) {
			ArrayList<String> codigos = new ArrayList<String>();

			boolean acabou = false;
			while (true) {
				String codigo = in.readLine();
				if (codigo == null) {
					acabou = true;
					break;
				} else if (codigo.equals("9")) {
					break;
				}
				codigos.add(codigo);
			}

			if (acabou) {
				break;
			}

			saida
					.append(imediatamenteDecodificavel(codigos) ? ("Set "
							+ caso + " is immediately decodable") : ("Set "
							+ caso + " is not immediately decodable"));
			saida.append(separador);
			caso++;
		}

		System.out.print(saida);
	}
}