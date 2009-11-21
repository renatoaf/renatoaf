import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_151 {
	static int getM(int n) {
		int m = 1;
		while (true) {
			ArrayList<Integer> play = new ArrayList<Integer>();
			for (int i = 1; i <= n; i++) {
				play.add(i);
			}

			int i = 0;
			while (play.size() > 1) {
				play.remove(i);
				i = (i + m - 1) % play.size();
			}

			if (play.get(0) == 13) {
				return m;
			}

			m++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			int N = Integer.parseInt(in.readLine());

			if (N == 0) {
				break;
			}

			saida.append(getM(N) + separador);
		}

		System.out.print(saida);
	}
}