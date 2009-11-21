/*
 * You Can Say 11
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Problem_10929 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		BigInteger onze = new BigInteger("11");
		while (true) {
			String linha = in.readLine();

			BigInteger numero = new BigInteger(linha.trim());
			if (numero.equals(BigInteger.ZERO)) {
				break;
			}

			saida
					.append(numero.mod(onze).equals(BigInteger.ZERO) ? (linha + " is a multiple of 11.")
							: (linha + " is not a multiple of 11."));
			saida.append(separador);
		}

		System.out.print(saida);
	}
}