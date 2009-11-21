import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

class HIST {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();

		while (true) {
			int n = Integer.parseInt(in.readLine());

			if (n == 0) {
				break;
			}

			HashMap<Integer, Integer> mapa = new HashMap<Integer, Integer>();
			ArrayList<Integer> nums = new ArrayList<Integer>();

			StringTokenizer linha = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				int r = Integer.parseInt(linha.nextToken());
				nums.add(r);
				mapa.put(r, r);
			}

			int qt = 0;
			for (int i = 0; i < n; i++) {
				int num = nums.get(i);
				qt += mapa.get(num);
				mapa.put(num, 1);

				for (Iterator<Integer> it = mapa.keySet().iterator(); it
						.hasNext();) {
					int aux = it.next();
					if (aux != num) {
						mapa.put(aux, mapa.get(aux) + 1);
					}
				}
			}

			saida.append(qt + "\n");
		}

		System.out.print(saida);
	}
}