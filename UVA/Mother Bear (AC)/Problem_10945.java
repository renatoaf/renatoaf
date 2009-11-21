import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10945 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String sentenca = in.readLine();
			if (sentenca.equals("DONE")) {
				break;
			}

			boolean ehPalindromo = true;
			if (sentenca.length() != 0) {
				int i = 0;
				int j = sentenca.length() - 1;

				while (i <= sentenca.length() && j >= 0 && i != j) {
					char ci = sentenca.charAt(i);
					char cj = sentenca.charAt(j);
					if (!Character.isLetter(ci)) {
						i++;
					} else if (!Character.isLetter(cj)) {
						j--;
					} else {
						if (Character.toLowerCase(ci) != Character
								.toLowerCase(cj)) {
							ehPalindromo = false;
							break;
						}
						i++;
						j--;
					}
				}
			}

			saida.append((ehPalindromo ? "You won't be eaten!" : "Uh oh..")
					+ separador);
		}

		System.out.print(saida);
	}
}
