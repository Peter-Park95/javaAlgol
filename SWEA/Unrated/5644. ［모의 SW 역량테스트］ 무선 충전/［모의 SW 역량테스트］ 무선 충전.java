import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int[][] batteryInfo;
	static boolean[] using;
	static int[][] direction = new int[][] {{0,0}, {0,-1}, {1,0}, {0,1}, {-1,0}};
	
	// 현재 위치에서 충전 가능한 배터리 number들을 리스트로 반환해주는 함수
	public static List<Integer> findAvaialbeBatteryNum(int currentX, int currentY){
		List<Integer> avaiableBatteryNun = new ArrayList<>(); 
		for(int batteryIdx = 1; batteryIdx < batteryInfo.length; batteryIdx++) {
			if (Math.abs(currentX-batteryInfo[batteryIdx][0]) + Math.abs(currentY-batteryInfo[batteryIdx][1]) <= batteryInfo[batteryIdx][2]) avaiableBatteryNun.add(batteryIdx);
		}
		return avaiableBatteryNun;
	}
	
	
	
	
	public static void main(String[] args) throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			map = new int[10+1][10+1]; // 0행, 0열은 사용하지 않는다.
			// 입력
			st = new StringTokenizer(br.readLine());
			int moveTime = Integer.parseInt(st.nextToken());
			
			int [] moveInfoA = new int[moveTime+1];
			int [] moveInfoB = new int[moveTime+1];
			
			int batteryCount = Integer.parseInt(st.nextToken());
			batteryInfo = new int[batteryCount+1][4];
			using = new boolean[batteryCount+1];
			
			//A, B 각 사용자의 이동명령어 (0,1,2,3,4)를 각 단위시간 T=1 ~ T=moveTime 까지 배열에 담음
			st = new StringTokenizer(br.readLine());
			for(int idx=1; idx<=moveTime; idx++) moveInfoA[idx] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int idx=1; idx<=moveTime; idx++) moveInfoB[idx] = Integer.parseInt(st.nextToken());
			
			// 배터리 정보를 batteryInfo에 1 ~ batteryCount개 까지 넣음
			for (int batteryNum = 1; batteryNum <= batteryCount; batteryNum++) {
				st = new StringTokenizer(br.readLine());
				int batteryX = Integer.parseInt(st.nextToken());
				int batteryY = Integer.parseInt(st.nextToken());
				int chargeRange = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				batteryInfo[batteryNum][0] = batteryX;
				batteryInfo[batteryNum][1] = batteryY;
				batteryInfo[batteryNum][2] = chargeRange;
				batteryInfo[batteryNum][3] = power;
			}
			
			// 초기 위치 설정
			int aUserCurrentX = 1, aUserCurrentY = 1;
			int bUserCurrentX = 10, bUserCurrentY = 10;
			
			// 총 파워량(정답)
			int totalMaxPower = 0;
			
			for(int time=0; time <= moveTime; time++) {
				//해당 구역에 대해서 가장 큰 충전량을 받을 수 있는 값을 구하여 계산
				aUserCurrentX += direction[moveInfoA[time]][0];
				aUserCurrentY += direction[moveInfoA[time]][1];
				bUserCurrentX += direction[moveInfoB[time]][0];
				bUserCurrentY += direction[moveInfoB[time]][1];
				List<Integer> userAPossibleList= findAvaialbeBatteryNum(aUserCurrentX, aUserCurrentY);
				List<Integer> userBPossibleList = findAvaialbeBatteryNum(bUserCurrentX, bUserCurrentY);
				int maxPower = 0;
				
				// A, B 모두 충전할 곳이 없을 경우
				if(userAPossibleList.isEmpty() && userBPossibleList.isEmpty()) continue;
				
				// B만 충전할 곳이 있는 경우 ( 가장 큰 곳을 선택해서 충전 )
				else if(userAPossibleList.isEmpty()) {
					for(int batteryB : userBPossibleList) maxPower = Math.max(maxPower, batteryInfo[batteryB][3]);
				}
				
				// A만 충전할 곳이 있는 경우 ( 가장 큰 곳을 선택해서 충전 )
				else if(userBPossibleList.isEmpty()) {
					for(int batteryA : userAPossibleList) maxPower = Math.max(maxPower, batteryInfo[batteryA][3]);
				} 
				
				// A, B 모두 충전할 수 있는 경우
				else {
					for(int batteryA : userAPossibleList) {
						using[batteryA] = true;
						for(int batteryB : userBPossibleList) {
							if (using[batteryB]) maxPower = Math.max(maxPower, batteryInfo[batteryA][3]);
							else maxPower = Math.max(maxPower, batteryInfo[batteryA][3] + batteryInfo[batteryB][3]);
						}
						using[batteryA] = false;
					}	
				}
				totalMaxPower += maxPower;
			}
			sb.append('#').append(testCase).append(" ").append(totalMaxPower).append('\n');
		}
		System.out.println(sb);
	}
}


