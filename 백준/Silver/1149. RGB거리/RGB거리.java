import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static BufferedReader in;
    static StringTokenizer st;
    static final int R_IDX = 0;
    static final int G_IDX = 1;
    static final int B_IDX = 2;


    public static void main(String[] args) throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        int houseAmount = Integer.parseInt(in.readLine().trim());

        int[][] houseInfo = new int[houseAmount + 1][3];
        for (int idx = 1; idx <= houseAmount; ++idx) {
            st = new StringTokenizer(in.readLine());
            houseInfo[idx][R_IDX] = Integer.parseInt(st.nextToken());
            houseInfo[idx][G_IDX] = Integer.parseInt(st.nextToken());
            houseInfo[idx][B_IDX] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[houseAmount + 1][3];
        dp[1][0] = houseInfo[1][0];
        dp[1][1] = houseInfo[1][1];
        dp[1][2] = houseInfo[1][2];

        for (int idx = 2; idx <= houseAmount; ++idx) {
            for (int colorIdx = 0; colorIdx <= 2; ++colorIdx) {
                if (colorIdx == 0)
                    dp[idx][colorIdx] = Math.min(dp[idx - 1][1] + houseInfo[idx][colorIdx], dp[idx - 1][2] + houseInfo[idx][colorIdx]);
                else if (colorIdx == 1)
                    dp[idx][colorIdx] = Math.min(dp[idx - 1][0] + houseInfo[idx][colorIdx], dp[idx - 1][2] + houseInfo[idx][colorIdx]);
                else if (colorIdx == 2)
                    dp[idx][colorIdx] = Math.min(dp[idx - 1][0] + houseInfo[idx][colorIdx], dp[idx - 1][1] + houseInfo[idx][colorIdx]);
            }
        }
        int minCost = Integer.MAX_VALUE;
        for(int colorIdx = 0; colorIdx <= 2; ++colorIdx){
            minCost = Math.min(dp[houseAmount][colorIdx], minCost);
        }
        System.out.println(minCost);

    }
}