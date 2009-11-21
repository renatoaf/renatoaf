import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_11577 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());
		for (int c = 1; c <= casos; c++) {
			int[] freq = new int[26];
			String linha = in.readLine();

			for (int i = 0; i < linha.length(); i++) {
				char chr = linha.charAt(i);
				if (Character.isLetter(chr)) {
					freq[Character.toLowerCase(chr) - 'a']++;
				}
			}

			int maisFrequente = -1;
			for (int i = 0; i < freq.length; i++) {
				if (freq[i] > maisFrequente) {
					maisFrequente = freq[i];
				}
			}

			String maisFreq = "";
			for (int i = 0; i < freq.length; i++) {
				if (freq[i] == maisFrequente) {
					maisFreq += ((char) (i + 'a'));
				}
			}
			saida.append(maisFreq + separador);
		}

		System.out.print(saida);
	}
}