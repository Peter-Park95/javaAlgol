import java.util.Scanner;

public class Main {
	static int N, M;
	static boolean[] visited;
	static int[] result;
	
	public static void search(int idx) {
		if(idx == M) {
			for(int i = 0; i<M; i++) {
				System.out.print(result[i]+ " ");
			}
		System.out.println();
		return;
		} 
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				result[idx] = i;
				visited[i] = true;
				search(idx+1);
				visited[i] = false;
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		visited = new boolean[N+1];
		result = new int[M];
		
		search(0);
		
		

	}

}
