import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_305 {
	static boolean valido(int m, int k) {
		ArrayList<Integer> play = new ArrayList<Integer>();
		for (int i = 1; i <= 2 * k; i++) {
			play.add(i);
		}

		int i = 0;
		while (play.size() > 1) {
			i = (i + m - 1) % play.size();
			int removido = play.remove(i);

			if (removido <= k) {
				return false;
			} else if (play.size() == k) {
				return true;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int[] resps = { 2, 7, 5, 30, 169, 441, 1872, 7632, 1740, 93313, 459901,
				1358657, 2504881 };

		while (true) {
			int k = Integer.parseInt(in.readLine());
			if (k == 0) {
				break;
			}
			saida.append(resps[k - 1] + separador);
		}

		System.out.print(saida);

		// pre-processamento
		// for (int k = 1; k <= 13; k++) {
		// int m = 1;
		// while (!valido(m, k)) {
		// m++;
		// }
		// System.out.println(m);
		// }
	}
}