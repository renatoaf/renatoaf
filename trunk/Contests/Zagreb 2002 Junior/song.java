import java.io.BufferedReader;
import java.io.InputStreamReader;

class song {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine().trim());
		for (int verso = 0; verso < n; verso++) {
			String[] terminacoes = new String[4];

			for (int i = 0; i < 4; i++) {
				terminacoes[i] = "";
				String linha = in.readLine();

				int j = linha.length() - 1;
				while (j >= 0) {
					char c = linha.charAt(j);

					if (!Character.isLetter(c))
						break;

					c = Character.toLowerCase(c);
					terminacoes[i] = c + terminacoes[i];
					j--;

					if (c == 'a' || c == 'e' || c == 'i' || c == 'o'
							|| c == 'u')
						break;
				}
			}

			if (terminacoes[0].equals(terminacoes[1])
					&& terminacoes[1].equals(terminacoes[2])
					&& terminacoes[2].equals(terminacoes[3])) {
				System.out.println("perfect");
			} else if (terminacoes[0].equals(terminacoes[1])
					&& terminacoes[2].equals(terminacoes[3])) {
				System.out.println("even");
			} else if (terminacoes[0].equals(terminacoes[2])
					&& terminacoes[1].equals(terminacoes[3])) {
				System.out.println("cross");
			} else if (terminacoes[0].equals(terminacoes[3])
					&& terminacoes[1].equals(terminacoes[2])) {
				System.out.println("shell");
			} else {
				System.out.println("free");
			}
		}
	}
}