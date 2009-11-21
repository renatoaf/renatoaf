/*
 * Parity
 */

import java.util.Scanner;

class Problem_10931 {
	private String representacao;
	private int paridade;
	private int n;

	public Problem_10931(int n) {
		representacao = "";
		paridade = 0;
		this.n = n;
		calculaParidade();
	}

	public void calculaParidade() {
		while (n >= 1) {
			int p = n % 2;
			representacao = p + representacao;
			n = n / 2;

			if (p == 1) {
				paridade++;
			}
		}
	}

	public int getParidade() {
		return paridade;
	}

	public String getRepresentacao() {
		return representacao;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int i;
		while ((i = scan.nextInt()) != 0) {
			Problem_10931 bit = new Problem_10931(i);
			System.out.println("The parity of " + bit.getRepresentacao()
					+ " is " + bit.getParidade() + " (mod 2).");
		}
	}
}
