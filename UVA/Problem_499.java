/*
 * What's the Frequency, Kenneth
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_499 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();

			if (linha == null) {
				break;
			}

			int[] freqMin = new int[26];
			int[] freqMaiusc = new int[26];

			for (int i = 0; i < linha.length(); i++) {
				char chr = linha.charAt(i);
				if (Character.isLetter(chr)) {
					if (Character.isLowerCase(chr)) {
						freqMin[chr - 'a']++;
					} else {
						freqMaiusc[chr - 'A']++;
					}
				}
			}

			int maisFrequente = -1;
			for (int i = 0; i < freqMin.length; i++) {
				if (freqMin[i] > maisFrequente) {
					maisFrequente = freqMin[i];
				}
				if (freqMaiusc[i] > maisFrequente) {
					maisFrequente = freqMaiusc[i];
				}
			}

			String maisFreqMaiusc = "";
			String maisFreqMin = "";
			for (int i = 0; i < freqMin.length; i++) {
				if (freqMin[i] == maisFrequente) {
					maisFreqMin += ((char) (i + 'a'));
				}
				if (freqMaiusc[i] == maisFrequente) {
					maisFreqMaiusc += ((char) (i + 'A'));
				}
			}
			saida.append(maisFreqMaiusc + maisFreqMin + " " + maisFrequente
					+ separador);
		}

		System.out.print(saida);
	}
}