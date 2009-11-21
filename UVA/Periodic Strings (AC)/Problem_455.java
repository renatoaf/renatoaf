import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_455 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int N = Integer.parseInt(in.readLine());
		for (int c = 0; c < N; c++) {
			in.readLine();
			String palavra = in.readLine();

			int k;
			if (palavra.length() > 0) {
				k = 1;

				while (true) {
					if (palavra.length() % k == 0) {
						String prefixo = palavra.substring(0, k);
						String novaPalavra = "";

						while (novaPalavra.length() < palavra.length()) {
							novaPalavra += prefixo;
						}

						if (palavra.equals(novaPalavra)) {
							break;
						}
					}

					k++;
				}
			} else {
				k = 0;
			}

			saida.append(k + separador);
			if (c != N - 1) {
				saida.append(separador);
			}
		}

		System.out.print(saida);
	}
}