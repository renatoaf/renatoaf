import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * Memory Game.
 */
public class URI1928 {
    protected static void dfs(int source, int[] level, int[] parent, Collection<Integer>[] lines) {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(source);

		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
		for (int i = 0; i < level.length; i++) {
			level[i] = ( source == i ) ? 0 : -1;
		}
    	
    	while (!stack.isEmpty()) {
    		int current = stack.pop();
    		
    		for (int neighbor : lines[current]) {
    			if (level[neighbor] == -1) {
    				parent[neighbor] = current;

    				level[neighbor] = level[current] + 1;
            	
    				stack.push(neighbor);
    			}
    		}
    	}
    }
    
    protected static long distance(int source, int destination, int[] level, int[] parent) {
    	int lca = lca(source, destination, level, parent);
    	long distanceSourceLCA = level[source] - level[lca];
    	long distanceDestinationLCA = level[destination] - level[lca];
    	return distanceSourceLCA + distanceDestinationLCA;
    }
    
    // can be improved (dynamic programming)
    protected static int lca(int x, int y, int[] level, int[] parent) {
    	while (x != y) {
    		if (level[x] > level[y]) { // x is below
    			x = parent[x];
    		} else {
    			y = parent[y];
    		}
    	}
    	return x;
    }
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		Integer numberOfCards = Integer.parseInt(reader.readLine());
		
		List<Integer>[] positions = new List[numberOfCards / 2];
		List<Integer>[] lines = new List[numberOfCards];
		
		int parent[] = new int[numberOfCards];
		int level[] = new int[numberOfCards];
		
		for (int i = 0; i < numberOfCards / 2; i++) {
			positions[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < numberOfCards; i++) {
			lines[i] = new LinkedList<Integer>();
		}

		String[] positionsConfig = reader.readLine().split("\\s+");
		
		for (int i = 0; i < numberOfCards; i++) {
			int letter = Integer.parseInt(positionsConfig[i]) - 1;
			
			positions[letter].add(i);
		}
		
		for (int i = 0; i < numberOfCards - 1; i++) {
			String[] lineConfig = reader.readLine().split("\\s+");
			
			int letter1 = Integer.parseInt(lineConfig[0]) - 1;
			int letter2 = Integer.parseInt(lineConfig[1]) - 1;
			
			lines[letter1].add(letter2);
			lines[letter2].add(letter1);
		}
		
		dfs(0, level, parent, lines);
		
		long points = 0;
		
		for (int i = 0; i < ( numberOfCards / 2 ); i++) {
			int from = positions[i].get(0);
			int to = positions[i].get(1);
			
			points += distance(from, to, level, parent);
		}
		
		System.out.println(points);
	}
}
