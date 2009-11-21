import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

class Problem_10041 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				System.in)));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = in.nextInt();
		for (int c = 1; c <= casos; c++) {
			int qt = in.nextInt();
			int[] distancias = new int[qt];

			for (int i = 0; i < qt; i++) {
				distancias[i] = in.nextInt();
			}
			Arrays.sort(distancias);

			double mediana;
			if (distancias.length % 2 != 0) {
				mediana = (double) distancias[distancias.length / 2];
			} else {
				int n1 = distancias[distancias.length / 2];
				int n2 = distancias[(distancias.length - 1) / 2];
				mediana = ((double) (n1 + n2)) / 2.0;
			}

			int maisPertoDaMediana = -1;
			double distMaisProxima = Double.MAX_VALUE;
			for (int i = 0; i < distancias.length; i++) {
				double dif = mediana - distancias[i];
				if (dif < 0) {
					dif = -dif;
				}
				if (dif < distMaisProxima) {
					maisPertoDaMediana = i;
					distMaisProxima = dif;
				}
			}

			int somaDasDistancias = 0;
			for (int i = 0; i < distancias.length; i++) {
				somaDasDistancias += Math.abs(distancias[maisPertoDaMediana]
						- distancias[i]);
			}

			saida.append(somaDasDistancias + separador);
		}

		System.out.print(saida);
	}
}