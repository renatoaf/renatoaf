import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_492 {
	static boolean ehVogal(char c) {
		c = Character.toLowerCase(c);
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	static String pigLatin(String palavra) {
		if (ehVogal(palavra.charAt(0))) {
			return palavra + "ay";
		}
		return palavra.substring(1) + palavra.charAt(0) + "ay";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();

		String palavra = "";
		while (true) {
			int read = in.read();

			if (read == -1) {
				break;
			}

			char c = (char) read;
			if (!Character.isLetter(c)) {
				if (palavra.length() > 0) {
					saida.append(pigLatin(palavra));
				}
				saida.append(c);
				palavra = "";
			} else {
				palavra += c;
			}
		}

		System.out.print(saida);
	}
}