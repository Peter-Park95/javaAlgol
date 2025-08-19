import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		int T = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			long N = Long.parseLong(st.nextToken());
			long count = 0;
			long root;
			while (N != 2) {
				root = (long) Math.sqrt(N);
				
				if(root * root == N) {
					N = root;
					count++;
				}else {
					long diff = (root+1)*(root+1) - N;
					N += diff;
					count += diff;
				}
			}
			sb = new StringBuilder();
			sb.append("#").append(testCase).append(" ").append(count);
			System.out.println(sb);
			
		}
		
	}

}


