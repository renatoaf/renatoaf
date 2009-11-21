import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_11417 {
	static int mdc(int a, int b) {
		if (a < 0) {
			a = -a;
		}
		if (b < 0) {
			b = -b;
		}

		if (b == 0) {
			return a;
		}
		return mdc(b, a % b);
	}

	static int g(int n) {
		int g = 0;
		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				g += mdc(i, j);
			}
		}
		return g;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			int n = Integer.parseInt(in.readLine());

			if (n == 0) {
				break;
			}

			saida.append(g(n) + separador);
		}

		System.out.print(saida);
	}
}