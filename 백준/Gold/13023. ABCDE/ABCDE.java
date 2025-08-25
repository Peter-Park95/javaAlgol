import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ13023. ABCDE
 * 
 * @author 박희범
 *
 * 1. DFS로 depth를 체크
 * 2. depth가 5가 되는 순간 possible == true로 변경
 * 3. 안되면 다시 visited 를 false로 돌림 (백트래킹)
 * 
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static ArrayList<Integer>[] relationInfo;
	static boolean[] visited;
	static boolean possible;
	
	public static void tracking(int peopleNum, int depth) {
		if (visited[peopleNum] || possible) return;
		if(depth == 5) {
			possible = true;
			return;
		}
		for(int people : relationInfo[peopleNum]) {
			visited[peopleNum] = true;
			tracking(people, depth+1);
			visited[peopleNum] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int peopleCount = Integer.parseInt(st.nextToken());
		int relationCount = Integer.parseInt(st.nextToken());
		visited = new boolean[peopleCount];
		relationInfo = new ArrayList[peopleCount];
		for(int i=0; i<peopleCount; i++) relationInfo[i] = new ArrayList<>();
		for(int relationIdx=0; relationIdx < relationCount; relationIdx++) {
			st = new StringTokenizer(br.readLine());
			int people1 = Integer.parseInt(st.nextToken());
			int people2 = Integer.parseInt(st.nextToken());
			relationInfo[people1].add(people2);
			relationInfo[people2].add(people1);
		}
		possible = false;
		for(int peopleNum=0; peopleNum<peopleCount; peopleNum++) {
			tracking(peopleNum, 1);
			if(possible) break;
		}
		if(possible) System.out.println(1);
		else System.out.println(0);
		
	}

}
