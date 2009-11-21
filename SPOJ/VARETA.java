import java.util.Scanner;

class VARETA {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}

			int qtdDePares = 0;
			for (int i = 0; i < n; i++) {
				in.nextInt();
				qtdDePares += (in.nextInt() / 2);
			}

			System.out.println(qtdDePares / 2);
		}
	}
}