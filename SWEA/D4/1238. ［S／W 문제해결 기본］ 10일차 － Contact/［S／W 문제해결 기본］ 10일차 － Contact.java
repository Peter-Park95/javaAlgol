import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int from, to;
	static boolean[] contacted;
	static int tempMaxNum;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		for (int testCase = 1; testCase <= 10; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			int dataLen = Integer.parseInt(st.nextToken());
			int startNum = Integer.parseInt(st.nextToken());
			HashSet<Integer>[] network = new HashSet[101];
			for (int i = 0; i < 101; i++) {
			    network[i] = new HashSet<>();
			}
			contacted = new boolean[101];
			st = new StringTokenizer(br.readLine().trim());
			for(int i = 0; i < dataLen/2; i++) {
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				network[from].add(to);	
			}
			

			Queue<Integer> target = new LinkedList<>();
			target.add(startNum);
			int maxPeopleNum = 0;
			
			while (true) { // 연락의 단위
				int peopleNum = 0;
				maxPeopleNum = 0;
				Queue<Integer> nextTarget = new LinkedList<>();
				while(!target.isEmpty()) {
					peopleNum = target.poll();
					contacted[peopleNum] = true;
					maxPeopleNum = Math.max(maxPeopleNum, peopleNum);
					
					for(int nextPeopleNum : network[peopleNum]) {
						if(contacted[nextPeopleNum] || nextTarget.contains(nextPeopleNum) ) continue;
						nextTarget.add(nextPeopleNum);
					}
				}
				
				
				if(nextTarget.isEmpty()) break;
				target = nextTarget;
			}
			sb.append('#').append(testCase).append(' ').append(maxPeopleNum).append('\n');
		}
		System.out.println(sb);
	}
}


