import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 4014  활주로 건설!!!! 날아올라라 제발!!!!
 * 
 * @author 박희범
 *
 *
 * 왼쪽 오른쪽 각각 체크 했을 때 OK
 *
 *
 */

public class Solution {
	static BufferedReader in;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] maps;
	static int N, X;
	static int possibleCount;
	static boolean[][] used;
	public static boolean checkLeftToRight(int row) {
		int idx = 1;
		int stack = 1;
		while (idx < N){
			int current = maps[row][idx];
			int prev = maps[row][idx-1];
			if(current > prev) {
				if(stack / X  < current - prev)  return false;
				// 경사로를 놓을 자리에 비어있는지 확인
				for (int i = 1; i <= X; i++) if (used[row][idx-i]) return false;
				//경사로를 설치
				for(int i  = 1; i <= X; i++) used[row][idx-i] = true;
				stack = 1;
			} else {
				stack++;
			}
			
			idx++;
		}
		return true;
	}
	
	public static boolean checkRightToLeft(int row) {
		int idx = N-2;
		int stack = 1;
		while (idx >= 0){
			int current = maps[row][idx];
			int prev = maps[row][idx+1];
			if(current > prev) {
				if(stack / X  < current - prev)  return false;
				// 경사로를 놓을 자리에 비어있는지 확인
				for (int i = 1; i <= X; i++) if (used[row][idx+i]) return false;
				//경사로를 설치
				for(int i  = 1; i <= X; i++) used[row][idx+i] = true;
				stack = 1;
			}else {
				stack++;
			}
			idx--;
		}
		return true;
	}
	public static boolean upToDown(int col) {
		int idx = 1;
		int stack = 1;
		while (idx < N){
			int current = maps[idx][col];
			int prev = maps[idx-1][col];
			if(current > prev) {
				if(stack / X  < current - prev)  return false;
				// 경사로를 놓을 자리에 비어있는지 확인
				for (int i = 1; i <= X; i++) if (used[idx-i][col]) return false;
				//경사로를 설치
				for(int i  = 1; i <= X; i++) used[idx-i][col] = true;
				stack = 1;
			} else {
				stack++;
			}
			idx++;
		}
		return true;
	}
	public static boolean downToUp(int col) {
		int idx = N-2;
		int stack = 1;
		while (idx >= 0){
			int current = maps[idx][col];
			int prev = maps[idx+1][col];
			if(current > prev) {
				if(stack / X  < current - prev)  return false;
				// 경사로를 놓을 자리에 비어있는지 확인
				for (int i = 1; i <= X; i++) if (used[idx+i][col]) return false;
				//경사로를 설치
				for(int i  = 1; i <= X; i++) used[idx+i][col] = true;
				stack = 1;
			}else {
				stack++;
			}
			idx--;
		}
		return true;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			maps = new int[N][N];
			for(int x = 0; x < N; ++x) {
				st = new StringTokenizer(in.readLine());
				for(int y = 0; y < N; ++y) {
					maps[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			
			possibleCount = 0;
			// 구현 시작
			
			for(int i = 0; i < N; ++i) {
				used = new boolean[N][N];
				
				// i번째 행 가능한지 체크
				if(checkLeftToRight(i) && checkRightToLeft(i)) {
					possibleCount++;
				}
				
				
				used = new boolean[N][N];
				// i번째 열  가능한지 체크
				if(upToDown(i) && downToUp(i)){
					possibleCount++;
				}
				
			}
			sb.append('#').append(testCase).append(' ').append(possibleCount).append('\n');
		}
		System.out.println(sb);
	}

}
