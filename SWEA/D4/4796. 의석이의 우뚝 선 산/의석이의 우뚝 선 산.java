import java.io.*;
import java.util.*;

public class Solution {
	static Scanner sc;
	static StringBuilder sb;
	static int N;
	static int[] mountain;
	static int count;
	static boolean isUp;
	public static int countingGreatMountain(int idx) {
		if (mountain[idx-1] < mountain[idx] && mountain[idx] > mountain[idx+1]) {
			int areaCount = 0;
			int leftIdx = idx-1;
			int rightIdx = idx+1;
			int leftCount =1, rightCount = 1;
			while(leftIdx-1>=0 && mountain[leftIdx] > mountain[leftIdx-1]) {
				leftCount++;
				leftIdx--;
			}
			while (rightIdx+1 < N && mountain[rightIdx] > mountain[rightIdx+1]) {
				rightCount++;
				rightIdx++;
			}
			sb= new StringBuilder();
			sb.append(idx).append(" ").append(leftCount * rightCount);
			return leftCount * rightCount;
		} else return 0;
	}
	
    public static void main(String[] args) throws Exception {
    	sc = new Scanner(System.in);
    	int T = sc.nextInt();
    	sc.nextLine();
    	for(int testCase = 1; testCase <= T; testCase++) {
    		N = sc.nextInt();
    		sc.nextLine();
    		mountain = new int[N];
    		for(int mountainIdx = 0; mountainIdx < N; mountainIdx++) mountain[mountainIdx] = sc.nextInt();
    		count = 0;
    		for(int mountainIdx=1; mountainIdx<= N-2; mountainIdx++) count += countingGreatMountain(mountainIdx);
    		
    		sb= new StringBuilder();
    		sb.append("#").append(testCase).append(" ").append(count);
    		System.out.println(sb);
    	}
    	
    	
    	
    }
    
}