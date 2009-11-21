/*
 * Simple Base Conversion
 */

import java.util.Scanner;

class Problem_10473 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuilder saida = new StringBuilder();

		while (true) {
			String n = in.nextLine();

			if (n.startsWith("0x")) {
				saida.append(Integer.parseInt(n.substring(2), 16) + separador);
			} else {
				int num = Integer.parseInt(n);

				if (num < 0) {
					break;
				}

				saida.append("0x" + Integer.toHexString(num).toUpperCase()
						+ separador);
			}
		}

		System.out.print(saida);
	}
}