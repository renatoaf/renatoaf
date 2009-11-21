/*
 * Peter's Smokes
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10346 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();
			if (linha == null) {
				break;
			}
			String[] config = linha.trim().split("\\s+");
			int n = Integer.parseInt(config[0]);
			int k = Integer.parseInt(config[1]);

			int fumados = n;
			while (n >= k) {
				int sobras = n % k; // bitucas que nao viraram cigarro
				n /= k; // bitucas que viraram cigarro
				fumados += n; // cigarros adicionados
				n += sobras; // nova rodada
			}
			saida.append(fumados + separador);
		}

		System.out.print(saida);
	}
}