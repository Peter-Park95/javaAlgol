import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader in;
	static StringTokenizer st;
	static int availableHoreseMove, width, height;
	static int[][] maps;
	static int[][][] moveInfo;
	static int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static int[][] horseMove = {{-1,-2},{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2}};
	static boolean[][][] visited;
	
	
	public static class Monkey{
		int x,y,horseMove,moveCount;
		public Monkey(int x, int y, int horseMove, int moveCount) {
			this.x = x;
			this.y = y;
			this.horseMove = horseMove;
			this.moveCount = moveCount;
		}
	}
	
	public static int bfs() {
		Deque<Monkey> monkeyLocation = new ArrayDeque<>();
		monkeyLocation.add(new Monkey(0,0,0,0));
		
		while (!monkeyLocation.isEmpty()) {
			Monkey currentMonkey = monkeyLocation.poll();
			
			// 도착했을 경우
			if(currentMonkey.x == height-1 && currentMonkey.y == width-1) return currentMonkey.moveCount;
			
			
			int nextX, nextY;
			for (int[] dir: direction) {
				nextX = currentMonkey.x + dir[0];
				nextY = currentMonkey.y + dir[1];
				
				// 갈 수 없는 곳 제외
				if(nextX < 0 || nextY < 0 || nextX >= height || nextY >= width) continue;
				if(maps[nextX][nextY] == 1) continue;

				if(visited[nextX][nextY][currentMonkey.horseMove]) continue;
				visited[nextX][nextY][currentMonkey.horseMove] = true;
				monkeyLocation.add(new Monkey(nextX, nextY, currentMonkey.horseMove, currentMonkey.moveCount+1));
				
			}
			
			// 말의 움직음을 다 쓴 경우,
			if(currentMonkey.horseMove + 1 > availableHoreseMove) continue;
			
			for(int[] move : horseMove) {
				nextX = currentMonkey.x + move[0];
				nextY = currentMonkey.y + move[1];
				
				// 갈 수 없는 곳 제외
				if(nextX < 0 || nextY < 0 || nextX >= height || nextY >= width) continue;
				if(maps[nextX][nextY] == 1) continue;
				
				if(visited[nextX][nextY][currentMonkey.horseMove+1]) continue;
				visited[nextX][nextY][currentMonkey.horseMove+1] = true;
				monkeyLocation.add(new Monkey(nextX, nextY, currentMonkey.horseMove+1, currentMonkey.moveCount+1));
			}
		}
		return -1;

	}
	
	
	
	
	public static void main(String[] args) throws Exception{
		
		in = new BufferedReader(new InputStreamReader(System.in));

		availableHoreseMove = Integer.parseInt(in.readLine().trim());
		
		st = new StringTokenizer(in.readLine());
		width =  Integer.parseInt(st.nextToken());
		height =  Integer.parseInt(st.nextToken());
		
		maps = new int[height][width];
		for(int row = 0; row < height; ++row) {
			st = new StringTokenizer(in.readLine());
			for(int col = 0; col < width; ++ col) {
				maps[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		int totalMinMove = 0;

		visited = new boolean[height][width][availableHoreseMove+1];
		totalMinMove = bfs();

		System.out.println(totalMinMove);

	}

}
