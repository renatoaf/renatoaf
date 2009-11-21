import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_401 {
	static char mirror(char c) {
		switch (c) {
		case 'A':
			return 'A';
		case 'E':
			return '3';
		case 'H':
			return 'H';
		case 'I':
			return 'I';
		case 'J':
			return 'L';
		case 'L':
			return 'J';
		case 'M':
			return 'M';
		case 'O':
			return 'O';
		case 'S':
			return '2';
		case 'T':
			return 'T';
		case 'U':
			return 'U';
		case 'V':
			return 'V';
		case 'W':
			return 'W';
		case 'X':
			return 'X';
		case 'Y':
			return 'Y';
		case 'Z':
			return '5';
		case '1':
			return '1';
		case '2':
			return 'S';
		case '3':
			return 'E';
		case '5':
			return 'S';
		case '8':
			return '8';
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();

			if (linha == null) {
				break;
			}

			String palavra = linha.trim().toUpperCase();

			boolean espelhada = true;
			boolean palindromo = true;

			int i = 0;
			int j = palavra.length() - 1;

			while (i != j && (palindromo || espelhada)) {
				char a = palavra.charAt(i);
				char b = palavra.charAt(j);

				if (palindromo) {
					if (a != b) {
						palindromo = false;
					}
				}
				if (espelhada) {
					char esp = mirror(a);
					if (esp != b) {
						espelhada = false;
					}
				}

				if (i + 1 == j) {
					break;
				}

				i++;
				j--;
			}

			if (i == j && espelhada) {
				espelhada = mirror(palavra.charAt(i)) == palavra.charAt(j);
			}

			saida.append(linha + " -- ");
			if (!palindromo && !espelhada) {
				saida.append("is not a palindrome");
			} else if (palindromo && !espelhada) {
				saida.append("is a regular palindrome");
			} else if (!palindromo && espelhada) {
				saida.append("is a mirrored string");
			} else {
				saida.append("is a mirrored palindrome");
			}

			saida.append("." + separador + separador);
		}

		System.out.print(saida);
	}
}