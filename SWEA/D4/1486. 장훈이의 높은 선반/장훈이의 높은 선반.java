import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] staff;
	static int staffNumber, needHeight;
	static int MIN_QUALIFIED_HEIGHT;
	
	public static void sumStaffHeight(int idx, int sumHeight) {
		if(sumHeight >= needHeight) {
			MIN_QUALIFIED_HEIGHT = Math.min(MIN_QUALIFIED_HEIGHT, sumHeight);
			return;
		}
		if(idx >= staffNumber) return;
		sumStaffHeight(idx+1, sumHeight);
		sumStaffHeight(idx+1, sumHeight + staff[idx]);
	}

    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for(int testCase = 1; testCase <= T; testCase++) {
        	st = new StringTokenizer(br.readLine());
        	staffNumber = Integer.parseInt(st.nextToken());
        	needHeight = Integer.parseInt(st.nextToken());
        	st = new StringTokenizer(br.readLine());
        	staff = new int[staffNumber];
        	for (int staffIdx = 0; staffIdx < staffNumber; staffIdx++) {
        		staff[staffIdx] = Integer.parseInt(st.nextToken());
        	}
        	MIN_QUALIFIED_HEIGHT = Integer.MAX_VALUE;
        	sumStaffHeight(0,0);
        	System.out.println("#" + String.valueOf(testCase) + " " + String.valueOf(MIN_QUALIFIED_HEIGHT-needHeight));
    	}
    }
}