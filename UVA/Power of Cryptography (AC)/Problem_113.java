import java.util.Scanner;

class Problem_113 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		while (in.hasNext()) {
			double n = in.nextDouble();
			double p = in.nextDouble();
			saida.append(Math.round(Math.pow(p, (1 / n))) + separador);
		}

		System.out.print(saida);
	}
}