import java.util.Scanner;

class Problem_10110 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		long n;
		while ((n = in.nextLong()) != 0) {
			// numero impar de div = ligada
			// numero impar de div = quadrado perfeito
			double raiz = Math.sqrt(n);
			if (raiz == (long) raiz) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}
}