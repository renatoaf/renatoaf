import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10784 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int caso = 1;
		while (true) {
			long P = Long.parseLong(in.readLine());

			if (P == 0) {
				break;
			}

			// nDiagonais = (n^2 - 3n) / 2
			// n^2 - 3n - 2P (nDiagonais) = 0
			double raizDelta = Math.sqrt(9.0 + (8.0 * P));
			double n = (3 + raizDelta) / 2.0; // - raizDelta da negativo

			if ((int) n < n) {
				n++;
			}
			saida.append("Case " + caso++ + ": " + ((int) n) + separador);
		}

		System.out.print(saida);
	}
}