import java.math.BigInteger;
import java.util.Scanner;

class Problem_424 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BigInteger soma = new BigInteger("0");
		BigInteger zero = new BigInteger("0");
		BigInteger n;
		while (!(n = in.nextBigInteger()).equals(zero)) {
			soma = soma.add(n);
		}
		System.out.println(soma);
	}
}
