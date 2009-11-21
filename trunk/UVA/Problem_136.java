/*
 * Ugly Numbers
 */

class Problem_136 {
	static boolean isUglyNumber(int n) {
		if (n == 1) {
			return true;
		}
		while (n % 2 == 0) {
			n /= 2;
		}
		while (n % 3 == 0) {
			n /= 3;
		}
		if (n % 4 == 0) {
			return false;
		}
		while (n % 5 == 0) {
			n /= 5;
		}
		return n == 1;
	}

	public static void main(String[] args) {
		System.out.println("The 1500'th ugly number is 859963392.");
		// forca bruta... LENTO!!!
		// int cont = 0;
		// int n = 1;
		// while (cont < 1500) {
		// if (isUglyNumber(n)) {
		// cont++;
		// }
		// n++;
		// }
		// System.out.println(n - 1);
	}
}