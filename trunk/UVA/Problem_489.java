/*
 * Hangman Judge
 */

import java.util.Scanner;

class Problem_489 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int round = Integer.parseInt(sc.nextLine());

			if (round == -1) {
				break;
			}

			String palavra = sc.nextLine().trim();
			String sequencia = sc.nextLine().trim();

			int[] resposta = new int[26];
			for (int i = 0; i < palavra.length(); i++) {
				resposta[palavra.charAt(i) - 'a']++;
			}

			System.out.println("Round " + round);

			int erros = 0;
			boolean acabou = false;

			for (int i = 0; i < sequencia.length(); i++) {
				char c = sequencia.charAt(i);
				if (resposta[c - 'a'] == 0) {
					erros++;
				} else {
					resposta[c - 'a'] = 0;
				}

				if (erros == 7) {
					System.out.println("You lose.");
					acabou = true;
					break;
				}

				boolean ganhou = true;
				for (int j = 0; j < resposta.length; j++) {
					if (resposta[j] != 0) {
						ganhou = false;
					}
				}
				if (ganhou) {
					System.out.println("You win.");
					acabou = true;
					break;
				}
			}

			if (!acabou) {
				System.out.println("You chickened out.");
			}
		}
	}
}