import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

/*
 * Come and Go.
 */
public class URI1128 {
    private static final int TWO_WAY = 2;

	protected static int dfs(int source, Collection<Integer>[] streets) {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(source);
    	
    	boolean[] visited = new boolean[streets.length];
		visited[source] = true;
    	
    	int count = 1;
    	while (!stack.isEmpty()) {
    		int current = stack.pop();
    		
    		for (int neighbor : streets[current]) {
    			if (!visited[neighbor]) {
    				stack.push(neighbor);
    				
    				count++;
    				
    				visited[neighbor] = true;
    			}
    		}
    	}
    	return count;
    }
    
	// kosojaru: connected if an arbitrary vertex can reach each node and each node can reach this vertex 
    protected static boolean connected(Collection<Integer>[] streets) {
    	return dfs(0, streets) == streets.length && dfs(0, reversed(streets)) == streets.length;
    }
    
	private static Collection<Integer>[] reversed(Collection<Integer>[] streets) {
		Collection<Integer>[] reversedStreets = createStreets(streets.length);
		for (int origin = 0; origin < streets.length; origin++) {
			for (Integer destination : streets[origin]) {
				reversedStreets[destination].add(origin);
			}
		}
		return reversedStreets;
	}

	@SuppressWarnings("unchecked")
	private static Collection<Integer>[] createStreets(int numberOfIntersections) {
		Collection<Integer>[] streets = new Collection[numberOfIntersections];
		for (int i = 0; i < numberOfIntersections; i++) {
			streets[i] = new LinkedList<Integer>();
		}
		return streets;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder output = new StringBuilder();
		
		while (true) {
			String[] config = reader.readLine().split("\\s+");
			
			int numberOfIntersections = Integer.parseInt(config[0]);
			int numberOfStreets = Integer.parseInt(config[1]);
			
			if (numberOfIntersections == 0 && numberOfStreets == 0) {
				break;
			}
			
			Collection<Integer>[] streets = createStreets(numberOfIntersections);
			
			for (int i = 0; i < numberOfStreets; i++) {
				String[] roadConfig = reader.readLine().split("\\s+");
				
				int origin = Integer.parseInt(roadConfig[0]) - 1;
				int destination = Integer.parseInt(roadConfig[1]) - 1;
				int direction = Integer.parseInt(roadConfig[2]);

				streets[origin].add(destination);
				
				if (direction == TWO_WAY) {
					streets[destination].add(origin);
				}
			}
			
			output.append(( connected(streets) ? 1 : 0 ) + "\n");
		}
		
		System.out.print(output);
	}
}
