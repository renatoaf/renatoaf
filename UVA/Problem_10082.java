/*
 * WERTYU
 */

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

class Problem_10082 {
	public static Character wertyu(List<Character> linha, char c) {
		ListIterator<Character> it = linha.listIterator();
		while (it.hasNext()) {
			if (it.next() == Character.toUpperCase(c)) {
				it.previous();
				return it.previousIndex() != -1 ? it.previous() : c;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		List<Character> l1 = Arrays.asList(new Character[] { '`', '1', '2',
				'3', '4', '5', '6', '7', '8', '9', '0', '-', '=' });
		List<Character> l2 = Arrays.asList(new Character[] { 'Q', 'W', 'E',
				'R', 'T', 'Y', 'U', 'I', 'O', 'P', '[', ']', '\\' });
		List<Character> l3 = Arrays.asList(new Character[] { 'A', 'S', 'D',
				'F', 'G', 'H', 'J', 'K', 'L', ';', '\'' });
		List<Character> l4 = Arrays.asList(new Character[] { 'Z', 'X', 'C',
				'V', 'B', 'N', 'M', ',', '.', '/' });

		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		while (in.hasNextLine()) {
			String entrada = in.nextLine();
			for (int i = 0; i < entrada.length(); i++) {
				char atual = entrada.charAt(i);
				Character novo = null;
				if (l1.contains(atual)) {
					novo = wertyu(l1, atual);
				} else if (l2.contains(atual)) {
					novo = wertyu(l2, atual);
				} else if (l3.contains(atual)) {
					novo = wertyu(l3, atual);
				} else if (l4.contains(atual)) {
					novo = wertyu(l4, atual);
				}
				atual = (novo != null) ? novo.charValue() : atual;
				saida.append(atual);
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}