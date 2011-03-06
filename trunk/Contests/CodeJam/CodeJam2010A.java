import java.util.Scanner;

class Main {
	static boolean lampadaAcesa(int n, int k) {
		int bitsSeguidosValor1 = 0;
		while (bitsSeguidosValor1 < n && k > 0) {
			int bit = k % 2;
			if (bit == 1)
				bitsSeguidosValor1++;
			else
				return false;
			k /= 2;
		}
		return bitsSeguidosValor1 == n;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int c = 1; c <= T; c++) {
			System.out
					.println("Case #"
							+ c
							+ ": "
							+ (lampadaAcesa(in.nextInt(), in.nextInt()) ? "ON"
									: "OFF"));
		}
	}
}
