import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA1868. 최적 경로
 * 
 * @author 박희범
 *
 * 1. 회사 -> 고객 -> 집 으로 가는 모든 경로의 수를 찾는다. ( 순열, 최대 clientAmount = 10 )
 * 2. 각 경로의 이동거리를 구하고, minDistance와 비교하여 Math.min을 적용한다.
 * 
 */

public class Solution {
	static BufferedReader in;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int clientAmount;
	static int[][] location;
	static boolean[] visited;
	static int minTotalDistance;
	static int companyX, companyY;
	static int homeX, homeY;
	
	public static void visiting(int clientIdx, int distance, int visitCount) {
		if (visitCount == clientAmount) {
			int totalDistance = distance + Math.abs(homeX - location[clientIdx][0]) + Math.abs(homeY - location[clientIdx][1]);
			minTotalDistance = Math.min(minTotalDistance, totalDistance);
		}
		for(int nextIdx=1; nextIdx <= clientAmount; nextIdx++) {
			if(visited[nextIdx]) continue;
			int nextDistance = Math.abs(location[clientIdx][0] - location[nextIdx][0]) + Math.abs(location[clientIdx][1] - location[nextIdx][1]);
			visited[nextIdx] = true;
			visiting(nextIdx, distance + nextDistance, visitCount+1);
			visited[nextIdx] = false;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int testCase = 1; testCase <= T; ++testCase) {
			clientAmount = Integer.parseInt(in.readLine());
			location = new int[clientAmount+1][2];
			st = new StringTokenizer(in.readLine());
			
			companyX = Integer.parseInt(st.nextToken()); 
			companyY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken()); 
			homeY = Integer.parseInt(st.nextToken());

			// client 들의 좌표를 location[1] ... location[clientAmount]까지 넣는다
			for(int idx=1; idx <= clientAmount; idx++) {
				int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
				location[idx] = new int[] {x,y};
			}


			
			minTotalDistance = Integer.MAX_VALUE; 
			// 1번 client ~ clientAmount번 client 까지 방문여부 체크
			visited = new boolean[clientAmount+1];
			
			// company 에서부터 client1 부터 ~ clientAmount까지 처음 스타트
			for(int idx=1; idx<=clientAmount; idx++) {
				int distance = Math.abs(companyX - location[idx][0]) + Math.abs(companyY - location[idx][1]);
				visited[idx] = true;
				visiting(idx, distance, 1);
				visited[idx] = false;
			}
			sb.append('#').append(testCase).append(' ').append(minTotalDistance).append('\n');
		}
        System.out.println(sb);
		
	}
}
