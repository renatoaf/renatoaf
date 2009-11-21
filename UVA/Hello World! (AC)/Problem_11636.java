import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_11636 {
	public static double log2(double num) {
		return (Math.log(num) / Math.log(2)); // log(a,b) = ln b / ln a
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int c = 1;
		while (true) {
			int n = Integer.parseInt(in.readLine());
			if (n < 0) {
				break;
			}

			int pastes;
			if (n == 0) {
				pastes = 0;
			} else {
				double lg = log2(n);
				System.out.println(lg);
				if ((int) lg < lg) {
					lg++;
				}
				pastes = (int) lg;
			}
			saida.append("Case " + c++ + ": " + pastes + separador);
		}

		System.out.print(saida);
	}
}