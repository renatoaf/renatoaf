import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10340 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();

			if (linha == null) {
				break;
			}

			String[] sequencias = linha.split("\\s+");
			String sequencia1 = sequencias[0];
			String sequencia2 = sequencias[1];

			int i = 0;
			int j = 0;

			String subSeq = "";
			if (sequencia1.length() > sequencia2.length()) {
				saida.append("No" + separador);
			} else {
				while (i < sequencia1.length()) {
					char c1 = sequencia1.charAt(i);
					while (j < sequencia2.length()) {
						char c2 = sequencia2.charAt(j);
						j++;
						if (c2 == c1) {
							subSeq += c2;
							break;
						}
					}
					i++;
				}
				saida.append((subSeq.equals(sequencia1) ? "Yes" : "No")
						+ separador);
			}
		}

		System.out.print(saida);
	}
}