import java.util.Scanner;

class Problem_10055 {
	public static void main(String[] args) {
		String separador = "";
		StringBuffer saida = new StringBuffer();
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			saida.append(separador);
			separador = System.getProperty("line.separator");
			long primeiro = scan.nextLong();
			long segundo = scan.nextLong();
			saida.append(Math.abs(primeiro - segundo));
		}
		System.out.println(saida);
	}
}