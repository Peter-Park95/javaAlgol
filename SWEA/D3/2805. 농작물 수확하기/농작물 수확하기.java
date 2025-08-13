import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] farm;

    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine().trim());
    	int T = Integer.valueOf(st.nextToken());
    	for(int testCase=1; testCase<=T; testCase++) {
    		st = new StringTokenizer(br.readLine().trim());
    		int length = Integer.valueOf(st.nextToken());
    		farm = new int[length][length];
    		for(int farmRow=0; farmRow<length; farmRow++) {
    			String stringNumbers = br.readLine();
    			for(int farmCol=0; farmCol<length; farmCol++) farm[farmRow][farmCol] = stringNumbers.charAt(farmCol)-'0';
    		}
    		int farmMiddleIdx = length / 2;
    		int profit = 0;
    		


    		for(int idx1=0; idx1<length; idx1++) {
    			if (idx1 <= farmMiddleIdx) {
    				for(int idx2 = (farmMiddleIdx-idx1); idx2 <= (farmMiddleIdx+idx1); idx2++) profit += farm[idx1][idx2];
    				
    			} else {
    				for(int idx2 = idx1-farmMiddleIdx; idx2 <= (3*farmMiddleIdx-idx1); idx2++) profit += farm[idx1][idx2];
    				
    			}
    		}
    		System.out.println("#"+ String.valueOf(testCase)+ " " + String.valueOf(profit));
    	}
    }
}