import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Problem_353 {
	static boolean ehPalindromo(String s) {
		if (s.length() != 0) {
			int i = 0;
			int j = s.length() - 1;

			while (i <= s.length() && j >= 0 && i != j) {
				char ci = s.charAt(i);
				char cj = s.charAt(j);
				if (!Character.isLetter(ci)) {
					i++;
				} else if (!Character.isLetter(cj)) {
					j--;
				} else {
					if (Character.toLowerCase(ci) != Character.toLowerCase(cj)) {
						return false;
					}
					i++;
					j--;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String palavra = in.readLine();

			while (palavra != null && palavra.equals("")) {
				palavra = in.readLine();
			}

			if (palavra == null) {
				break;
			}

			palavra = palavra.trim();
			TreeSet<String> unicosPalindromos = new TreeSet<String>();

			for (int i = 0; i < palavra.length(); i++) {
				for (int j = i; j < palavra.length(); j++) {
					String substring = palavra.substring(i, j + 1);
					if (ehPalindromo(substring)) {
						unicosPalindromos.add(substring);
					}
				}
			}

			saida.append("The string '" + palavra + "' contains "
					+ unicosPalindromos.size() + " palindromes." + separador);
		}

		System.out.print(saida);
	}
}