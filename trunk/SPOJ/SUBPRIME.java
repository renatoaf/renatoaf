import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();

		while (true) {
			StringTokenizer config = new StringTokenizer(in.readLine());

			int B = Integer.parseInt(config.nextToken());
			int N = Integer.parseInt(config.nextToken());

			if (B == 0 && N == 0) {
				break;
			}

			int[] reserva = new int[B];
			config = new StringTokenizer(in.readLine());
			for (int i = 0; i < B; i++) {
				reserva[i] = Integer.parseInt(config.nextToken());
			}

			for (int i = 0; i < N; i++) {
				config = new StringTokenizer(in.readLine());
				int D = Integer.parseInt(config.nextToken());
				int C = Integer.parseInt(config.nextToken());
				int V = Integer.parseInt(config.nextToken());

				reserva[D - 1] -= V;
				reserva[C - 1] += V;
			}

			boolean pode = true;
			for (int i = 0; i < B; i++) {
				if (reserva[i] < 0) {
					pode = false;
					break;
				}
			}

			saida.append((pode ? "S" : "N") + "\n");
		}

		System.out.print(saida);
	}
}