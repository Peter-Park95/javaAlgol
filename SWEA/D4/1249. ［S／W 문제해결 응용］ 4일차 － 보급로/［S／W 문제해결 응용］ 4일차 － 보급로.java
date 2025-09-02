import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader in;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int length;
	static int[][] maps;
	static Deque<CurrentInfo> move;
	static int[][] minTimeInfo;
	static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
	static int minTime;
	
	static class CurrentInfo {
		int x, y, time;
		CurrentInfo(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	
	public static void bfs() {
		while (!move.isEmpty()) {
			CurrentInfo current = move.poll();
			// 도착
			if(current.x == length-1 && current.y == length-1) {
				minTime = Math.min(minTime, current.time);
				continue;
			}
			for(int[] dir : directions) {
				int nextX, nextY, nextTime;
				nextX = current.x + dir[0];
				nextY = current.y + dir[1];			
				if(nextX < 0 || nextX >= length || nextY < 0 || nextY >= length) continue;
				nextTime = current.time+maps[nextX][nextY];
				if(nextTime < minTimeInfo[nextX][nextY]) {
					minTimeInfo[nextX][nextY] = nextTime;
					move.add(new CurrentInfo(nextX, nextY, nextTime));
				}
			}		
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for(int testCase = 1; testCase <= T; ++testCase) {
			length = Integer.parseInt(in.readLine().trim());
			maps = new int[length][length];
			
			for(int row = 0; row < length; ++row) {
				String line = in.readLine();
				for(int col = 0; col < length; ++col) {
					maps[row][col] = line.charAt(col) - '0';
				}
			}
			
			minTimeInfo = new int[length][length];
			move = new ArrayDeque<>();
			move.add(new CurrentInfo(0,0,0));
			for(int i = 0; i < length; i++) for(int j = 0; j < length; j++) minTimeInfo[i][j] = Integer.MAX_VALUE;
			minTime = Integer.MAX_VALUE;
			bfs();
			
			sb.append('#').append(testCase).append(' ').append(minTime).append('\n');
		}
		System.out.println(sb);

	}

}
