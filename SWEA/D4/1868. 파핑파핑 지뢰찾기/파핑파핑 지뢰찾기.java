import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SWEA1868. 파핑파핑 지뢰찾기
 * 
 * @author 박희범
 *
 * 1. map을 순회하면서 0을 찾고, 0이면서 8방향 중 0이 있는 것 부터 터트린다. (count 가 하나로 측정)
 * 2. 남은 0들을 터트린다 (count++)
 * 3. Testcase 마다 count를 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static char[][] maps;
	static int N;
	static int[][] directions = {{-1,-1},{-1,0},{-1,1},
								{0,-1},      {0,1},
								{1,-1},{1,0},{1,1}};
	public static boolean checkSurround(int x, int y) {
		int nextX, nextY;
		if(maps[x][y] != '.') return false;
		for(int[] dir: directions) {
			nextX = x+dir[0];
			nextY = y+dir[1];
			if(nextX>=0 && nextX<N && nextY>=0 && nextY<N && maps[nextX][nextY] == '*') return false;
		}
		return true;
	}
	
	public static void popPop(int x, int y) {
		int nextX, nextY;
		for(int[] dir: directions) {
			nextX = x+dir[0];
			nextY = y+dir[1];
			if(nextX>=0 && nextX<N && nextY>=0 && nextY<N){
				if(checkSurround(nextX,nextY)) {
					maps[nextX][nextY] = '0';
					popPop(nextX, nextY);
				} else {
					maps[nextX][nextY] = 'X';
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase =1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			maps = new char[N][N];
			for(int row=0; row<N; row++) {
				String currentRow = br.readLine();
				for(int col=0; col<N; col++) {
					maps[row][col] = currentRow.charAt(col);	
				}
			}
			

			int count=0;
			
			// 0인 지형부터 탐색
			for(int row=0; row<N; row++) {
				for(int col=0; col<N; col++) {
					if(checkSurround(row,col)) {
						maps[row][col] = '0';
						popPop(row,col);
						count++;
					} 
				}
			}

			// 나머지 주위에 지뢰가 있는 지형들만 count
			for(int row=0; row<N; row++) {
				for(int col=0; col<N; col++) {
					if(maps[row][col] == '.') {
						count++;
						maps[row][col] = 'X';
					}
				}
			}
			sb.append('#').append(testCase).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
}
