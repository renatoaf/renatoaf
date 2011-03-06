import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int casos = Integer.parseInt(in.readLine());
		for (int c = 0; c < casos; c++) {
			if (c > 0)
				in.readLine();

			int P = Integer.parseInt(in.readLine());

			String melhores = "";
			int melhor = Integer.MAX_VALUE;

			for (int i = 1; i <= P; i++) {
				int count = new StringTokenizer(in.readLine()).countTokens();
				if (count < melhor) {
					melhor = count;
					melhores = i + "";
				} else if (count == melhor)
					melhores += " " + i;
			}

			out.append(melhores + "\n");
		}

		System.out.print(out);
	}
}
