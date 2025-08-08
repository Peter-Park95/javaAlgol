import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int T = Integer.parseInt(br.readLine().trim());
    	int N, M;
    	for (int tc=1; tc<=T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());
    		
    		int[][] area = new int[N][N];
    		for(int row=0; row<N; row++) {
    			st = new StringTokenizer(br.readLine());
    			for(int col=0; col<N; col++) {
    				area[row][col] = Integer.parseInt(st.nextToken());
    			}
    		}
    		int maxKill = 0 ;
    		for(int rowStart = 0; rowStart+M <= N; rowStart++) {
    			for(int colStart = 0; colStart+M <= N; colStart++) {
    				int kill = 0;
    				for(int row = rowStart; row < rowStart+M; row++) for(int col = colStart; col < colStart+M; col++) kill += area[row][col] ;
    				if (kill > maxKill) maxKill = kill;
    			}
    		}
    		System.out.println("#" + String.valueOf(tc) + " " + String.valueOf(maxKill));
    	}
    	
    }
}