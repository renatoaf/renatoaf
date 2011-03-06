import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// F
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		while (true) {
			String linha = in.readLine();

			if (linha.equals("*"))
				break;

			StringTokenizer palavras = new StringTokenizer(linha);

			char primeiraLetra = Character.toUpperCase(palavras.nextToken()
					.charAt(0));

			boolean resposta = true;
			while (palavras.hasMoreTokens()) {
				if (Character.toUpperCase(palavras.nextToken().charAt(0)) != primeiraLetra) {
					resposta = false;
				}
			}

			out.append(resposta ? "Y\n" : "N\n");
		}

		System.out.print(out);
	}
}
