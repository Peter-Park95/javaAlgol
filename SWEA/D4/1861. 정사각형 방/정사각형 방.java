import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N;
	static int[][] room;
	static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int maxMove;
	static int roomValue;
	
	public static int moveRoom(int x, int y) {
		int move = 1;
		int nextX, nextY;
		for(int[] dir: directions) {
			nextX = x+dir[0];
			nextY = y+dir[1];
			if (nextX<0 || nextX >=N || nextY<0 || nextY >=N) continue;
			if (room[x][y]+1 != room[nextX][nextY]) continue;
			move += moveRoom(nextX, nextY);
		}
		if(maxMove < move) {
			maxMove = move;
			roomValue = room[x][y];
		}
		if(maxMove == move) roomValue = Math.min(roomValue, room[x][y]);
		
		return move;
		
	}

	

    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for(int testCase = 1; testCase <= T; testCase++) {
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		room = new int[N][N];
    		maxMove = Integer.MIN_VALUE;
    		roomValue = N;
    		for(int row = 0; row < N; row++) {
    			st = new StringTokenizer(br.readLine());
    			for(int col = 0; col < N; col++) {
    				room[row][col] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		for(int x = 0; x < N; x++) {
    			for(int y = 0; y < N; y++) {
    				moveRoom(x,y);
    			}
    		}
    		
    		sb = new StringBuilder();
    		sb.append("#").append(testCase).append(" ").append(roomValue).append(" ").append(maxMove);
    		System.out.println(sb);
    	}
    	
    }
    
   
}