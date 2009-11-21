/*
 * Walk Like an Egyptian
 */

class Problem_3758 {
	static String readln() {
		String newLine = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		int car = -1;
		try {
			car = System.in.read();
			while ((car > 0) && (car != newLine.charAt(0))) {
				buffer.append((char) car);
				car = System.in.read();
			}
			if (car == newLine.charAt(0))
				System.in.skip(newLine.length() - 1);
		} catch (java.io.IOException e) {
			return (null);
		}
		if ((car < 0) && (buffer.length() == 0))
			return (null); /* eof */
		if (buffer.length() == 0)
			return ""; // string vazia
		return (buffer.toString()).trim();
	}

	public static void main(String[] args) {
		int[] array = new int[1000];
		array[1] = 1;
		array[0] = 1;
		int atual = 1;
		int n = new Integer(readln()).intValue();
		while (n != 0) {
			if (array[n] != 0) {
				System.out.println(n + " => " + array[n]);
			} else {
				for (int i = atual; i <= n; i++) {
					array[i] = array[i - 1] + (2 * (i - 1));
				}
				atual = n;
				System.out.println(n + " => " + array[n]);
			}
			n = new Integer(readln()).intValue();
		}
	}
}