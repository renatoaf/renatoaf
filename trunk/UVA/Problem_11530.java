/*
 * SMS Typing
 */

import java.util.HashMap;
import java.util.Scanner;

class Problem_11530 {
	private static final char[] UMA_VEZ = { 'a', 'd', 'g', 'j', 'm', 'p', 't',
			'w', ' ' };
	private static final char[] DUAS_VEZES = { 'b', 'e', 'h', 'k', 'n', 'q',
			'u', 'x' };
	private static final char[] TREZ_VEZES = { 'c', 'f', 'i', 'l', 'o', 'r',
			'v', 'y' };
	private static final char[] QUATRO_VEZES = { 's', 'z' };

	public static void main(String[] args) {
		HashMap<Character, Integer> mapa = new HashMap<Character, Integer>();
		for (int i = 0; i < UMA_VEZ.length; i++) {
			mapa.put(UMA_VEZ[i], 1);
		}
		for (int i = 0; i < DUAS_VEZES.length; i++) {
			mapa.put(DUAS_VEZES[i], 2);
		}
		for (int i = 0; i < TREZ_VEZES.length; i++) {
			mapa.put(TREZ_VEZES[i], 3);
		}
		for (int i = 0; i < QUATRO_VEZES.length; i++) {
			mapa.put(QUATRO_VEZES[i], 4);
		}

		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");
		int n = Integer.parseInt(in.nextLine());
		for (int i = 0; i < n; i++) {
			String linha = in.nextLine();
			int teclas = 0;
			for (int j = 0; j < linha.length(); j++) {
				teclas += mapa.get(linha.charAt(j));
			}
			saida.append("Case #" + (i + 1) + ": " + teclas + separador);
		}
		System.out.print(saida);
	}
}