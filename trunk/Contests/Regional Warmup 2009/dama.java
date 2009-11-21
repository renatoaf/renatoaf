import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class dama {
	static int nextInt(StringTokenizer config) {
		return Integer.parseInt(config.nextToken());
	}

	public static void main(String[] args) throws IOException {
		StringBuilder saida = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer config = new StringTokenizer(in.readLine());
			int X1 = nextInt(config);
			int Y1 = nextInt(config);
			int X2 = nextInt(config);
			int Y2 = nextInt(config);

			if (X1 == 0 && X2 == 0 && Y1 == 0 && Y2 == 0) {
				break;
			}

			if (X1 == X2 && Y1 == Y2) {
				saida.append("0");
			} else if (X1 == X2 || Y1 == Y2
					|| (Math.abs(X1 - X2) == Math.abs(Y1 - Y2))) {
				saida.append("1");
			} else {
				saida.append("2");
			}
			saida.append("\n");
		}

		System.out.print(saida);
	}
}