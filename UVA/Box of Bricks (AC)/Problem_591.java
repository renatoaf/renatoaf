import java.util.Scanner;

class Problem_591 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		int n;
		int caso = 1;
		while ((n = in.nextInt()) != 0) {
			int[] torres = new int[n];
			int soma = 0;
			for (int i = 0; i < n; i++) {
				torres[i] = in.nextInt();
				soma += torres[i];
			}
			int numDeTijolos = soma / n;
			int numDeMovimentos = 0;
			for (int i = 0; i < n; i++) {
				numDeMovimentos += (numDeTijolos > torres[i]) ? numDeTijolos
						- torres[i] : 0;
			}
			saida.append("Set #" + caso + separador
					+ "The minimum number of moves is " + numDeMovimentos + "."
					+ separador + separador);
			caso++;
		}
		System.out.print(saida);
	}
}