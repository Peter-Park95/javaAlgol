import java.util.*;
import java.io.*;
 
class Solution
{
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
 
    static int foodCount; 
    static int badMixCount;
 
    static int ans;
    static int food1; 
    static int food2;
    static boolean[][] conflict;
    static boolean[] picked;
    static HashSet<Integer> visited;
    
    public static void dfs(int idx) {

    	if(idx > foodCount) {
    		ans++;
    		return;
    	}
    	
    	// idx 선택 X
    	dfs(idx+1);
    	
    	
    	// idx 선택 O
    	for(int j = 1; j < idx; j++) {
    		if(picked[j] && conflict[j][idx]) return;
    	}
    	picked[idx] = true;
    	dfs(idx+1);
    	picked[idx] = false;
    	
    }
 
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            foodCount = Integer.parseInt(st.nextToken());
            badMixCount = Integer.parseInt(st.nextToken());
            conflict = new boolean[foodCount+1][foodCount+1];
            picked = new boolean[foodCount+1];
       
            for(int index=0; index<badMixCount; index++) {
                st = new StringTokenizer(br.readLine());
                food1 = Integer.parseInt(st.nextToken());
                food2 = Integer.parseInt(st.nextToken());
 
                conflict[food1][food2] = true;
                conflict[food2][food1] = true;
            }
            ans = 0;
            dfs(1);
            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);    
    }
 
}
