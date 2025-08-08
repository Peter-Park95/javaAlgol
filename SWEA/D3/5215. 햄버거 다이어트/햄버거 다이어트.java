import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Solution
{
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int foodCount, limitCal, totalTasteScore, maxTotalTasteScore;
	
	static int[][] foods;
	static final int tasteScoreIdx = 0;
	static final int calIdx = 1;

	public static void eat(int start, int totalCal) {
		if (totalCal > limitCal) return;
		if (totalTasteScore > maxTotalTasteScore) maxTotalTasteScore = totalTasteScore;
		for(int idx=start; idx<foodCount; idx++) {
			totalTasteScore += foods[idx][tasteScoreIdx];
			eat(idx+1, totalCal + foods[idx][calIdx]);
			totalTasteScore -= foods[idx][tasteScoreIdx];
		}
		
	}
	

	
    public static void main(String args[]) throws Exception{
    	br = new BufferedReader(new InputStreamReader(System.in));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
    	int testCase= Integer.parseInt(br.readLine().trim());
    	for(int tc = 1; tc<= testCase; tc++) {
    		st = new StringTokenizer(br.readLine());
    		foodCount = Integer.parseInt(st.nextToken());
    		limitCal = Integer.parseInt(st.nextToken());
    		foods = new int[20][2];
    		for(int idx =0; idx<foodCount; idx++) {
    			st = new StringTokenizer(br.readLine());
    			foods[idx][tasteScoreIdx] = Integer.parseInt(st.nextToken());
    			foods[idx][calIdx] = Integer.parseInt(st.nextToken());
    		}

    		maxTotalTasteScore = 0;
    		eat(0,0);
    		System.out.println("#" + String.valueOf(tc) + " " + String.valueOf(maxTotalTasteScore));
    		
    	}
    	
    	
    }
     
}