/*
 * Informants
 */

import java.util.Arrays;
import java.util.Scanner;

class Problem_11659 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			int i = in.nextInt();
			int a = in.nextInt();

			if (i == 0 && a == 0) {
				break;
			}

			boolean[] conf = new boolean[i];
			Arrays.fill(conf, true);

			for (int j = 0; j < a; j++) {
				in.nextInt();
				int y = in.nextInt();
				int posY = Math.abs(y) - 1;
				if (y < 0)
					conf[posY] = false;
			}

			int cont = 0;
			for (int j = 0; j < i; j++) {
				if (conf[j]) {
					cont++;
				}
			}
			System.out.println(cont);
		}
	}
}