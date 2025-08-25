import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SWEA1868. 창용 마을 무리의 개수
 * 
 * @author 박희범
 *
 * 1. 1번사람부터 merge function (DFS)
 * 2. 이미 합쳐진 사람은 merged 배열에서 true가 되고, pass!
 * 3. peopleCount가 끝났을 때, count값이 무리의 개수!
 *
 *
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int peopleCount;
	static int relationCount;
	static boolean[] grouped;
	static ArrayList<Integer>[] graph;
	
	
	public static void grouping(int peopleNum) {
		if(peopleNum > peopleCount) return;
		grouped[peopleNum] = true;
		for(int nextPeopleNum : graph[peopleNum]) {
			if(!grouped[nextPeopleNum]) grouping(nextPeopleNum);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			peopleCount = Integer.parseInt(st.nextToken());
			relationCount = Integer.parseInt(st.nextToken());
			grouped = new boolean[peopleCount+1];
			graph = new ArrayList[peopleCount+1];
			for(int idx=0; idx<graph.length; idx++) graph[idx] = new ArrayList<>();
			
			// graph 배열에 인간관계 삽입
			for(int relationIdx=0; relationIdx<relationCount; relationIdx++){
				st = new StringTokenizer(br.readLine());
				int people1 = Integer.parseInt(st.nextToken());
				int people2 = Integer.parseInt(st.nextToken());
				graph[people1].add(people2);
				graph[people2].add(people1);
			}
			
			int groupCount = 0;
			for(int peopleNum=1; peopleNum<= peopleCount; peopleNum++) {
				if(!grouped[peopleNum]) {
					grouping(peopleNum);
					groupCount++;
				}
			}
			sb.append('#').append(testCase).append(' ').append(groupCount).append('\n');
		}
		System.out.println(sb);
	}

}
