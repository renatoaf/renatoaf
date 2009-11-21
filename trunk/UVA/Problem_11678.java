/*
 * Card's Exchange
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

class Problem_11678 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();

		while (true) {
			StringTokenizer config = new StringTokenizer(in.readLine());

			int A = Integer.parseInt(config.nextToken());
			int B = Integer.parseInt(config.nextToken());

			if (A == 0 && B == 0) {
				break;
			}

			HashMap<Integer, Boolean> mapa1 = new HashMap<Integer, Boolean>();
			HashMap<Integer, Boolean> mapa2 = new HashMap<Integer, Boolean>();

			config = new StringTokenizer(in.readLine());
			for (int i = 0; i < A; i++) {
				int xi = Integer.parseInt(config.nextToken());
				mapa1.put(xi, true);
			}
			config = new StringTokenizer(in.readLine());
			for (int i = 0; i < B; i++) {
				int yi = Integer.parseInt(config.nextToken());
				mapa2.put(yi, true);
			}

			int qt1 = 0;
			int qt2 = 0;
			Set<Integer> keys1 = mapa1.keySet();
			Set<Integer> keys2 = mapa2.keySet();

			for (Integer i : keys1) {
				if (!mapa2.containsKey(i)) {
					qt1++;
				}
			}
			for (Integer i : keys2) {
				if (!mapa1.containsKey(i)) {
					qt2++;
				}
			}

			saida.append(Math.min(qt1, qt2) + "\n");
		}

		System.out.print(saida);
	}
}