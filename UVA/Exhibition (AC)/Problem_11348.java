import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
import java.util.Scanner;

class Problem_11348 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				System.in)));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		DecimalFormat df = new DecimalFormat("0.000000");
		DecimalFormatSymbols p = new DecimalFormatSymbols();
		p.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(p);

		int casos = in.nextInt();
		for (int c = 1; c <= casos; c++) {
			int amigos = in.nextInt();

			int[] stamps = new int[10001];
			LinkedList<Integer>[] lista = new LinkedList[amigos];

			for (int i = 0; i < amigos; i++) {
				LinkedList<Integer> listaAm = new LinkedList<Integer>();

				int qt = in.nextInt();
				for (int j = 0; j < qt; j++) {
					int st = in.nextInt();
					if (!listaAm.contains(st)) {
						listaAm.add(st);
						stamps[st]++;
					}
				}

				lista[i] = listaAm;
			}

			double totalUnicos = 0;
			double[] unic = new double[amigos];
			for (int i = 0; i < amigos; i++) {
				LinkedList<Integer> l = lista[i];
				int unicos = 0;
				for (int j = 0; j < l.size(); j++) {
					int st = l.get(j);
					if (stamps[st] == 1) {
						unicos++;
					}
				}
				unic[i] = unicos;
				totalUnicos += unicos;
			}

			saida.append("Case " + c + ":");
			for (int i = 0; i < amigos; i++) {
				double res = unic[i] / totalUnicos;
				saida.append(" " + df.format(res * 100) + "%");
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}