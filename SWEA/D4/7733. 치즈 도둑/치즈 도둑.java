import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW7733. 공항 도둑
 * 
 * @author 박희범
 * 
 *  Day 0 - 100일 까지 매일 모든 행열에 대해서 makingBlock 을 실행 !
 *  실행이 된다는것은 새로운 덩어리를 만들 수 있다는 거라 dayBlockCount++
 *  다 실행되었을때  dayBlockCount 값이 해당 날짜에 덩어리 수이고 이것들의 0~100일째 max 값이 최종 답

 */

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int cheeseLen;
	static boolean[][] isCheese; 
	static boolean[][] blockUsed; 
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void makingBlock(int row, int col) {
		
		int nextX, nextY;
		blockUsed[row][col] = true;
		for (int[] dir : direction) {
			nextX = row + dir[0];
			nextY = col + dir[1];
			
			if(nextX < 0 || nextY < 0 || nextX >= cheeseLen || nextY >= cheeseLen) continue;
			if(blockUsed[nextX][nextY]) continue;
			blockUsed[nextX][nextY] = true;
			makingBlock(nextX, nextY);
		}

	}
	
	
	public static void main(String[] args) throws IOException {
		
		// 입력
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <= T; testCase++) {
			cheeseLen = Integer.parseInt(br.readLine().trim());
			int[][] cheese = new int[cheeseLen][cheeseLen];
			for(int row = 0; row < cheeseLen; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < cheeseLen; col++) cheese[row][col] = Integer.parseInt(st.nextToken());
			}
			
			// 구현 시작 !	
			// Day 1 ~ Day 100 까지 했을 때 Max Block count
			int totalMaxBlockCount = 0;
			
			isCheese = new boolean[cheeseLen][cheeseLen];
			for(int i = 0; i<cheeseLen; i++) for(int j = 0; j<cheeseLen; j++) isCheese[i][j] = true;
			
			blockUsed = new boolean[cheeseLen][cheeseLen];
		
			
			for (int day = 0; day <= 100; day++) {
				// 치즈가 맞는지 체크하는 배열

				
				for(int row=0;row<cheeseLen;row++) {
					for(int col=0;col<cheeseLen;col++) if(cheese[row][col] == day) isCheese[row][col] = false;
				}
				for(int row=0;row<cheeseLen;row++) {
					for(int col=0;col<cheeseLen;col++) blockUsed[row][col] = !isCheese[row][col];
				}
				
				
				int dayBlockCount = 0;
				// 치즈를 순회하며 덩어리 count
				for(int row=0;row<cheeseLen;row++) {
					for(int col=0;col<cheeseLen;col++) {
						if(blockUsed[row][col]) continue;  // 사용된거면 패스 !
						makingBlock(row, col);
						dayBlockCount++;
					}
				}
				totalMaxBlockCount = Math.max(totalMaxBlockCount, dayBlockCount);

			}
			sb.append("#").append(testCase).append(" ").append(totalMaxBlockCount).append("\n");
		}
		System.out.println(sb);
	}
}
