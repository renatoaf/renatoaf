import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_389 {
	static char[] digito = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F' };

	static String formata(String s) {
		while (s.length() < 7) {
			s = " " + s;
		}
		return s;
	}

	static String toString(int n, int base) {
		if (n == 0) {
			return "0";
		}

		String res = "";
		while (n > 0) {
			res = digito[(n % base)] + res;
			n /= base;
		}
		return res;
	}

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
			String num = config[0];
			int b1 = Integer.parseInt(config[1]);
			int b2 = Integer.parseInt(config[2]);

			int decimal = Integer.parseInt(num, b1);
			String res = "";
			if (b2 != 10) {
				res = toString(decimal, b2);
			} else {
				res = decimal + "";
			}

			if (res.length() > 7) {
				saida.append(formata("ERROR") + separador);
			} else {
				saida.append(formata(res) + separador);
			}
		}

		System.out.print(saida);
	}
}