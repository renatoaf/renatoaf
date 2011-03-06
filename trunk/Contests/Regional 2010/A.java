import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		while (true) {
			StringTokenizer linha = new StringTokenizer(in.readLine());

			String d = linha.nextToken();
			String v = linha.nextToken();

			if (d.equals("0") && v.equals("0"))
				break;

			String s = v.replace(d, "");
			if (s.length() == 0)
				s = "0";
			out.append(new BigInteger(s) + "\n");
		}
		
		System.out.print(out);
	}
}
