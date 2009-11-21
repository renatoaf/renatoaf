import java.io.IOException;

class Problem_458 {
	private static final int CIFRA = 7;

	public static void main(String[] args) throws IOException {
		int read;
		while ((read = System.in.read()) != -1) {
			if (read != 10 && read != 13) {
				System.out.write(read - CIFRA);
			} else {
				System.out.write(read);
			}
		}
	}
}