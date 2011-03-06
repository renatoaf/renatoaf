import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		while (true) {
			StringTokenizer linha = new StringTokenizer(in.readLine());

			int N = Integer.parseInt(linha.nextToken());

			if (N == 0)
				break;

			for (int i = 0; i < N; i++) {
				char resposta = '*';

				linha = new StringTokenizer(in.readLine());
				for (int j = 0; j < 5; j++) {
					int val = Integer.parseInt(linha.nextToken());
					if (val <= 127) {
						if (resposta != '*') { // tinha outra resposta
							resposta = '*';
							break;
						} else { // nao tinha outra resposta
							resposta = (char) ('A' + j);
						}
					}
				}

				out.append(resposta + "\n");
			}
		}

		System.out.print(out);
	}
}
