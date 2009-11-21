import java.util.Scanner;
import java.util.StringTokenizer;

class COPA {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			StringTokenizer config = new StringTokenizer(in.nextLine());

			int T = Integer.parseInt(config.nextToken());
			int N = Integer.parseInt(config.nextToken());

			if (T == 0 && N == 0) {
				break;
			}

			int qt = 0;
			for (int i = 0; i < T; i++) {
				config = new StringTokenizer(in.nextLine());
				config.nextToken();
				qt += (Integer.parseInt(config.nextToken()));
			}

			int totalPontos = N * 3;
			int empates = totalPontos - qt;
			System.out.println(empates);
		}
	}
}