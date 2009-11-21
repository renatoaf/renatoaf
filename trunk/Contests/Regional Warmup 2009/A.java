import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			int colunas = in.nextInt();
			int linhas = in.nextInt();
			int K = in.nextInt();

			if (colunas == 0 && linhas == 0 && K == 0) {
				break;
			}

			int qt = 0;
			int[] temK = new int[colunas];
			for (int i = 0; i < linhas; i++) {
				for (int j = 0; j < colunas; j++) {
					int lido = in.nextInt();
					if (lido == 1) {
						temK[j]++;
					} else {
						if (temK[j] >= K) {
							qt++;
						}
						temK[j] = 0;
					}
				}
			}

			for (int j = 0; j < colunas; j++) {
				if (temK[j] >= K)
					qt++;
			}
			System.out.println(qt);
		}
	}
}