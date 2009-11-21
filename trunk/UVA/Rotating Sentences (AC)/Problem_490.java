import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_490 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		ArrayList<String> sentencas = new ArrayList<String>();
		while (true) {
			String sentenca = in.readLine();
			if (sentenca == null) {
				break;
			}
			sentencas.add(sentenca);
		}

		int maiorTam = -1;
		for (int i = 0; i < sentencas.size(); i++) {
			String sent = sentencas.get(i);
			if (sent.length() > maiorTam) {
				maiorTam = sent.length();
			}
		}

		for (int i = 0; i < maiorTam; i++) {
			for (int j = sentencas.size() - 1; j >= 0; j--) {
				String sent = sentencas.get(j);
				if (i < sent.length()) {
					saida.append(sent.charAt(i));
				} else {
					saida.append(" ");
				}
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}