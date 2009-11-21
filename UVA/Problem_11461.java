/*
 * Square Numbers
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_11461 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String[] config = in.readLine().split("\\s+");

			int a = Integer.parseInt(config[0]);
			int b = Integer.parseInt(config[1]);

			if (a == 0 && b == 0) {
				break;
			}

			int cont = 0;
			for (int i = a; i <= b; i++) {
				double raiz = Math.sqrt(i);
				if ((int) raiz == raiz) {
					cont++;
				}
			}
			saida.append(cont + separador);
		}

		System.out.print(saida);
	}
}