import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader in;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] maps;
	static int[][] directions = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
	static Set<String> result;
	
	public static void dfs(int x, int y, String number) {
		if (number.length() == 7){
			result.add(number);
			return;
		}
		
		number += String.valueOf(maps[x][y]);  
		for(int[] dir: directions) {
			int nextX, nextY;
			nextX = x + dir[0];
			nextY = y + dir[1];
			if( nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4) continue;
			dfs(nextX, nextY, number);
		}	
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for(int testCase = 1; testCase <= T; ++testCase) {
			maps = new int[4][4];
			
			for(int i = 0; i < 4; ++i) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < 4; ++j) maps[i][j] = Integer.parseInt(st.nextToken());
			}
			
			result = new HashSet<>();
			
			// 임의의 번호에서 시작
			for(int row= 0; row < 4; ++row) {
				for(int col = 0; col < 4; ++col) {
					dfs(row,col,"");
				}
			}
			sb.append('#').append(testCase).append(' ').append(result.size()).append('\n');
		}
		System.out.println(sb);
	}
}
