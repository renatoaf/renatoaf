import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Problem_11344 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());
		for (int c = 1; c <= casos; c++) {
			BigInteger M = new BigInteger(in.readLine());
			String[] S = in.readLine().split("\\s+");

			boolean wonderful = true;

			for (int i = 1; i < S.length; i++) {
				if (!M.mod(new BigInteger(S[i])).equals(BigInteger.ZERO)) {
					wonderful = false;
					break;
				}
			}

			saida.append(M + " - ");
			saida.append(wonderful ? "Wonderful." : "Simple.");
			saida.append(separador);
		}

		System.out.print(saida);
	}
}