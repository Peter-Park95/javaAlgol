import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int[][] map;
	static int N;
	static List<int[]> core;
	static int stickedCore;
	static int[][] directions = {{1,0}, {-1,0}, {0,-1}, {0,1}};
	static int maxCoreCount;
	static int minLength;
	
	public static void connector(int coreIdx, int connectCount, int length) {
		if (coreIdx == core.size()) {
			if (connectCount > maxCoreCount) {
				maxCoreCount = connectCount;
				minLength = length;
			}
			if (connectCount == maxCoreCount) {
				minLength = Math.min(minLength, length);
			}
			return;
		}
		
		int coreX = core.get(coreIdx)[0];
		int coreY = core.get(coreIdx)[1];
		
		for(int[] dir : directions) {
			int nextX = coreX;
			int nextY = coreY;
			boolean possible = false;
			List<int[]> path = new ArrayList<>();
			while (true) {
				nextX += dir[0];
				nextY += dir[1];

				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					possible = true;
					break;
				}
				if (map[nextX][nextY] == 1) break; // 불가능
				path.add(new int[] {nextX, nextY});
			}
			if (possible) {
				for(int[] p : path) map[p[0]][p[1]] = 1;
				connector(coreIdx+1, connectCount+1, length+path.size());
				for(int[] p : path) map[p[0]][p[1]] = 0;
			}
		}
		connector(coreIdx+1, connectCount, length);
	}
	
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		int T = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			core = new ArrayList<>();
			stickedCore = 0;
			for(int row=0; row<N; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col=0; col<N; col++) {
					int area = Integer.parseInt(st.nextToken());
					map[row][col] = area;
					if (area == 1) {
						if(row == 0 || row == N-1 || col == 0 ||col == N-1 ) stickedCore++;
						else core.add(new int[] {row, col});
					}
				}
			}
			maxCoreCount = 0;
			minLength = Integer.MAX_VALUE;
			connector(0,0,0);
			sb = new StringBuilder();
			sb.append("#").append(testCase).append(" ").append(minLength);
			System.out.println(sb);
		}
	}
}


