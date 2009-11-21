import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem_11677 {
	static int toMinutes(int h, int m) {
		return (h * 60) + m;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();

		int minUmDia = toMinutes(24, 0);

		while (true) {
			StringTokenizer config = new StringTokenizer(in.readLine());

			int H1 = Integer.parseInt(config.nextToken());
			int M1 = Integer.parseInt(config.nextToken());
			int H2 = Integer.parseInt(config.nextToken());
			int M2 = Integer.parseInt(config.nextToken());

			if (H1 == 0 && M1 == 0 && H2 == 0 && M2 == 0) {
				break;
			}

			int primeiroTempo = toMinutes(H1, M1);
			int segundoTempo = toMinutes(H2, M2);

			int tempo = 0;
			if (segundoTempo > primeiroTempo) {
				tempo = segundoTempo - primeiroTempo;
			} else {
				tempo = (minUmDia - primeiroTempo) + segundoTempo;
			}
			saida.append(tempo + "\n");
		}

		System.out.print(saida);
	}
}