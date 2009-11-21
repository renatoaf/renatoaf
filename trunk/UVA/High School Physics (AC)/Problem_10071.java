import java.util.Scanner;

class Problem_10071 {
	public static int deslocamento(int v, int t) {
		return v * t;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextLine()) {
			String[] linha = scan.nextLine().split(" ");
			int v = Integer.parseInt(linha[0]);
			int t = Integer.parseInt(linha[1]);
			System.out.println(deslocamento(v, 2 * t));
		}
	}
}