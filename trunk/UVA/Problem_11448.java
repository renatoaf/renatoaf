/*
 * Who Said Crisis
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Problem_11448 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());
		for (int c = 1; c <= casos; c++) {
			String[] config = in.readLine().split("\\s+");
			BigInteger A = new BigInteger(config[0]);
			BigInteger B = new BigInteger(config[1]);
			saida.append(A.subtract(B) + separador);
		}

		System.out.print(saida);
	}
}