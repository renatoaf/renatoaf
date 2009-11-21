/*
 * Funny Encryption Method
 */

import java.util.Scanner;

class Problem_10019 {
	public static int contaUns(String binario) {
		int uns = 0;
		for (int i = 0; i < binario.length(); i++) {
			if (binario.charAt(i) == '1') {
				uns++;
			}
		}
		return uns;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int m = in.nextInt();
			int b1 = contaUns(Integer.toBinaryString(m));
			int m2 = Integer.parseInt((m + ""), 16);
			int b2 = contaUns(Integer.toBinaryString(m2));
			saida.append(b1 + " " + b2 + separador);
		}
		System.out.print(saida);
	}

}
