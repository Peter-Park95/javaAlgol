import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int R, C;
	static int[][] map;
	static int count;
	static boolean arrived;
	static boolean conditionA, conditionB, conditionC;
	
	public static boolean findingPath(int x, int y) {
		
		map[x][y] = 1;
		if (y == C-1) return true;
		int[][] direction = {{-1,1}, {0,1}, {1,1}};
		
		for (int idx = 0; idx<3; idx++) {
			int nextX = x+direction[idx][0];
			int nextY = y+direction[idx][1];
			if (nextX < 0 || nextX >= R || map[nextX][nextY] == 1) continue;
			if (findingPath(nextX, nextY)) return true;
		}
		return false;
	}
	
    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	map = new int[R][C];
    	for(int row = 0; row<R; row++) {
    		String location = br.readLine();
    		for(int col = 0; col<C; col++) {
    			if (location.charAt(col) == '.') map[row][col] = 0;
    			else if (location.charAt(col) == 'x') map[row][col] = 1;
    		}
    	}   	
    	count = 0;
    	 for(int startX = 0; startX < R; startX++) if(findingPath(startX,0)) count++;
    	System.out.println(count);
    }
}