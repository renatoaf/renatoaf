/*
 * LC-Display
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_706 {
	static boolean[][] digitos;

	static String formata(String num, int s) {
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int col = s + 2;
		int lin = s;

		for (int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			boolean tem = digitos[c - '0'][0];
			for (int j = 0; j < col; j++) {
				if (j == 0 || j == col - 1) {
					saida.append(" ");
				} else if (tem) {
					saida.append("-");
				} else {
					saida.append(" ");
				}
			}

			if (i != num.length() - 1) {
				saida.append(" ");
			}
		}
		saida.append(separador);

		for (int k = 0; k < lin; k++) {
			for (int i = 0; i < num.length(); i++) {
				char c = num.charAt(i);
				boolean tem1 = digitos[c - '0'][1];
				boolean tem2 = digitos[c - '0'][2];
				for (int j = 0; j < col; j++) {
					if (j == 0 && tem1) {
						saida.append("|");
					} else if (j == col - 1 && tem2) {
						saida.append("|");
					} else {
						saida.append(" ");
					}
				}

				if (i != num.length() - 1) {
					saida.append(" ");
				}
			}
			saida.append(separador);
		}

		for (int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			boolean tem = digitos[c - '0'][3];
			for (int j = 0; j < col; j++) {
				if (j == 0 || j == col - 1) {
					saida.append(" ");
				} else if (tem) {
					saida.append("-");
				} else {
					saida.append(" ");
				}
			}

			if (i != num.length() - 1) {
				saida.append(" ");
			}
		}
		saida.append(separador);

		for (int k = 0; k < lin; k++) {
			for (int i = 0; i < num.length(); i++) {
				char c = num.charAt(i);
				boolean tem1 = digitos[c - '0'][4];
				boolean tem2 = digitos[c - '0'][5];
				for (int j = 0; j < col; j++) {
					if (j == 0 && tem1) {
						saida.append("|");
					} else if (j == col - 1 && tem2) {
						saida.append("|");
					} else {
						saida.append(" ");
					}
				}

				if (i != num.length() - 1) {
					saida.append(" ");
				}
			}
			saida.append(separador);
		}

		for (int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			boolean tem = digitos[c - '0'][6];
			for (int j = 0; j < col; j++) {
				if (j == 0 || j == col - 1) {
					saida.append(" ");
				} else if (tem) {
					saida.append("-");
				} else {
					saida.append(" ");
				}
			}

			if (i != num.length() - 1) {
				saida.append(" ");
			}
		}
		return saida.toString();
	}

	public static void main(String[] args) throws IOException {
		digitos = new boolean[10][7];
		digitos[0] = new boolean[] { true, true, true, false, true, true, true };
		digitos[1] = new boolean[] { false, false, true, false, false, true,
				false };
		digitos[2] = new boolean[] { true, false, true, true, true, false, true };
		digitos[3] = new boolean[] { true, false, true, true, false, true, true };
		digitos[4] = new boolean[] { false, true, true, true, false, true,
				false };
		digitos[5] = new boolean[] { true, true, false, true, false, true, true };
		digitos[6] = new boolean[] { true, true, false, true, true, true, true };
		digitos[7] = new boolean[] { true, false, true, false, false, true,
				false };
		digitos[8] = new boolean[] { true, true, true, true, true, true, true };
		digitos[9] = new boolean[] { true, true, true, true, false, true, true };

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String[] config = in.readLine().trim().split("\\s+");

			if (config[0].equals("0") && config[1].equals("0")) {
				break;
			}

			int s = Integer.parseInt(config[0]);
			saida.append(formata(config[1], s) + separador + separador);
		}

		System.out.print(saida);
	}
}