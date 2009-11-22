import java.util.Scanner;

class PARIDADE {
	static String representacao;
	static int paridade;

	static void calculaParidade(int n) {
		representacao = "";
		paridade = 0;
		while (n >= 1) {
			int p = n % 2;
			representacao = p + representacao;
			n = n / 2;

			if (p == 1) {
				paridade++;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int i;
		while ((i = scan.nextInt()) != 0) {
			calculaParidade(i);
			System.out.println("The parity of " + representacao + " is "
					+ paridade + " (mod 2).");
		}
	}
}