import java.util.Arrays;
import java.util.Scanner;

class IMPEDIDO {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder saida = new StringBuilder();

		while (true) {
			int A = in.nextInt();
			int D = in.nextInt();

			if (A == 0 && D == 0) {
				break;
			}

			int[] atacantes = new int[A];
			int[] defensores = new int[D];
			for (int i = 0; i < A; i++) {
				atacantes[i] = in.nextInt();
			}
			for (int j = 0; j < D; j++) {
				defensores[j] = in.nextInt();
			}
			Arrays.sort(atacantes);
			Arrays.sort(defensores);
			saida.append(defensores[1] > atacantes[0] ? "Y\n" : "N\n");
		}

		System.out.print(saida);
	}
}
