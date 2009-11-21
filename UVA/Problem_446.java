/*
 * Kibbles n Bits n Bits n Bits
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_446 {
	public static String completaZeros(String s) {
		while (s.length() < 13) {
			s = "0" + s;
		}
		return s;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			String[] expressao = in.readLine().split("\\s+");

			long num1 = Long.parseLong(expressao[0], 16);
			long num2 = Long.parseLong(expressao[2], 16);

			expressao[0] = completaZeros(Long.toBinaryString(num1));
			expressao[2] = completaZeros(Long.toBinaryString(num2));

			if (expressao[1].equals("+")) {
				saida.append(expressao[0] + " " + expressao[1] + " "
						+ expressao[2] + " = " + (num1 + num2));
			} else {
				saida.append(expressao[0] + " " + expressao[1] + " "
						+ expressao[2] + " = " + (num1 - num2));
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}