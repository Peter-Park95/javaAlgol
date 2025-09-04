import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader in;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int C;
	static int[][] maps;
	static int[] honey;
	static boolean[] picked;
	static int totalPowMaxHoney; 

	
	
	public static void dfs(int honeyIdx) {

		if(honeyIdx == M) {
			int total = 0;
			for(int idx =0; idx<M; ++idx) {
				if(picked[idx]) total += honey[idx];
			}
			if(total <= C) {
				int powHoney = 0;
				for(int idx = 0; idx < M; ++idx) {
					if(picked[idx]) {
						powHoney += (honey[idx] * honey[idx]);
					}
				}
				totalPowMaxHoney = Math.max(powHoney, totalPowMaxHoney);
			}
			return;
		}
		
		picked[honeyIdx] = true;
		dfs(honeyIdx+1);
		picked[honeyIdx] = false;
		dfs(honeyIdx+1);
		
	}
	
	
	public static void main(String[] args) throws Exception{
		in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for(int testCase = 1; testCase <= T; ++testCase) {
			
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C= Integer.parseInt(st.nextToken());
			
			// maps 입력받기
			int[][] maps = new int[N][N];
			for(int row = 0; row < N; ++row) {
				st = new StringTokenizer(in.readLine());
				for(int col = 0; col < N; ++col) {
					maps[row][col] = Integer.parseInt(st.nextToken());
				}
				
			}
			int maxTotal = 0;
			for(int row1 = 0; row1 < N; ++row1) {
				for(int col1 = 0; col1 < N-(M-1); ++col1) {
					int employee1 = 0;
					int employee2 = 0;
					honey = new int[M];
					int honeyIdx = 0;
					for(int idx = col1; idx < col1+M; ++idx) {
						honey[honeyIdx++] = maps[row1][idx];
					}
					
					
					picked = new boolean[M];
					totalPowMaxHoney = 0;
					dfs(0);
					employee1 = totalPowMaxHoney;
					
					
					for(int row2 = row1; row2 < N; ++row2) {
						int magicNum = 0;
						if (row2 == row1) magicNum = col1 + M;
						for(int col2 = magicNum; col2 < N; ++col2) {
							if (col2+M > N) continue;

							honey = new int[M];
							honeyIdx = 0;
							for(int idx = col2; idx < col2+M; ++idx) {
								
								honey[honeyIdx++] = maps[row2][idx];
							}
							
							picked = new boolean[M];
							totalPowMaxHoney = 0;
							dfs(0);
							employee2 = totalPowMaxHoney;
							maxTotal = Math.max(maxTotal, employee1+employee2);
						}
					}

					
				}
			}
			sb.append('#').append(testCase).append(' ').append(maxTotal).append('\n');
		}
		System.out.println(sb);
		

	}

}
