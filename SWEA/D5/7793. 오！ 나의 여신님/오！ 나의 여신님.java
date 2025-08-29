import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * SWEA7793 오! 나의 여신님
 * 
 * @author 박희범
 *
 * 
 */

public class Solution {
	
	static BufferedReader in;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int height, width;
	static boolean[][] visited;
	static char[][] maps;
	 static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

	public static class Node {
		int x, y, t;
		Node(int x, int y, int t){
			this.x = x; this.y = y; this.t = t;
		}
	}
	
	public static int bfs(Queue<Node> devil, Queue<Node> heebum) {
		while (!heebum.isEmpty()) {
			// 1. 악마 확산
			
			int dSize = devil.size();
			for(int dIdx = 0; dIdx < dSize; ++dIdx) {
				Node cur = devil.poll();
				
				for(int[] dir : dirs) {
					int nx = cur.x + dir[0];
					int ny = cur.y + dir[1];
					if (nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
					if (maps[nx][ny] == 'X' || maps[nx][ny] == 'D' || maps[nx][ny] == '*') continue;
					maps[nx][ny] = '*';
					devil.add(new Node(nx,ny,0));
				}
			}
			
			// 2. 희범 이동
			
			int hSize = heebum.size();
			for(int hIdx = 0; hIdx < hSize; ++hIdx) {	
				Node cur = heebum.poll();
				// 도착 !!
				
				for(int[] dir : dirs) {
					int nx = cur.x + dir[0];
					int ny = cur.y + dir[1];
					if (nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
					if (maps[nx][ny] == 'X' || maps[nx][ny] == '*') continue;
					if(maps[nx][ny] == 'D') return cur.t+1;
					if(maps[nx][ny] == '.') {
						maps[nx][ny] = 'S';
						heebum.add(new Node(nx,ny,cur.t+1));
					}
				}
			}
		}
		return -1;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		// 입력받기
		in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(in.readLine());
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			maps = new char[height][width];
			Queue<Node> devil = new ArrayDeque<>();
			Queue<Node> heebum = new ArrayDeque<>();
			
			for(int row = 0; row<height; ++row) {
				String line = in.readLine();
				for(int col = 0; col<width; ++col) {
					maps[row][col] = line.charAt(col);
					if(maps[row][col] == '*') devil.add(new Node(row,col,0));
					if(maps[row][col] == 'S') heebum.add(new Node(row,col,0));
				}
			}
			
			int result = bfs(devil, heebum);
			
			sb.append('#').append(testCase).append(' ');
			if (result != -1) sb.append(result);
			else sb.append("GAME OVER");
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
