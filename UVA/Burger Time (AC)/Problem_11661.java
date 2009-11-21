import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_11661 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();

		while (true) {
			int L = Integer.parseInt(in.readLine());

			if (L == 0) {
				break;
			}

			ArrayList<Integer> posRes = new ArrayList<Integer>();
			ArrayList<Integer> posFarm = new ArrayList<Integer>();
			String linha = in.readLine();

			int min = Integer.MAX_VALUE;
			boolean acabou = false;
			for (int i = 0; i < L; i++) {
				char c = linha.charAt(i);

				if (c == 'Z') {
					acabou = true;
					min = 0;
					break;
				} else if (c == 'R') {
					posRes.add(i);
				} else if (c == 'D') {
					posFarm.add(i);
				}
			}

			if (!acabou) {
				for (int i = 0; i < posFarm.size(); i++) {
					int pos = posFarm.get(i);
					for (int j = 0; j < posRes.size(); j++) {
						int pos2 = posRes.get(j);
						int dif = Math.abs(pos - pos2);
						if (dif < min) {
							min = dif;
						}

						if (min == 1) {
							acabou = true;
							break;
						}
					}

					if (acabou) {
						break;
					}
				}
			}

			saida.append(min + "\n");
		}

		System.out.print(saida);
	}
}