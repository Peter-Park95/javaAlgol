import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] maps;
	static int[][] directions = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
	static int height, width;
	static Deque<int[]> virus;
	static int maxRemain;
	
	public static int bfs() {
		int[][] bfsMap = new int[height][width];
		// map을 bfsMap에 복사
		for(int row=0; row<height; ++row) {
			for(int col=0; col<width; ++ col) {	
				bfsMap[row][col] = maps[row][col];
			}
		}
		
		// 바이러스를  저장
		virus = new ArrayDeque<>();
		for(int row=0; row<height; ++row) {
			for(int col=0; col<width; ++ col) {
				if(bfsMap[row][col] == 2) virus.add(new int[] {row,col});
			}
		}
		
		while(!virus.isEmpty()) {
			int[] v = virus.poll();
			for(int[] dir : directions) {
				int nextX, nextY;
				nextX = v[0]+ dir[0];
				nextY = v[1]+ dir[1];
				if(nextX < 0 || nextX >= height || nextY < 0 || nextY >= width) continue;
				if(bfsMap[nextX][nextY] == 0) {
					bfsMap[nextX][nextY] = 2;
					virus.add(new int[] {nextX, nextY});
				}
			}
		}
		int count = 0;
		// 남은 칸 count
		for(int row=0; row<height; ++row) {
			for(int col=0; col<width; ++ col) {	if(bfsMap[row][col] == 0) ++count;
			}
		}
		return count;
	}
	
	
	public static void dfs(int wallCnt) {
		if( wallCnt == 3) {
			maxRemain = Math.max(maxRemain,  bfs());
			return;
		}

		for (int row=0; row<height; ++row) {
			for(int col=0; col<width; ++col ) {
				if (maps[row][col] == 0) {
					maps[row][col] = 1;
					dfs(wallCnt+1); 
					maps[row][col] = 0;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(in.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		maps = new int[height][width];
		
		for (int row=0; row < height; row++) {
			st = new StringTokenizer(in.readLine());
			for(int col= 0; col< width; col++) {
				maps[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// start 
		maxRemain = 0;
		dfs(0);
		System.out.println(maxRemain);
		
		
	}
}
