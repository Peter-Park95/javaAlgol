/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
    {
    	Scanner sc = new Scanner(System.in);
		for(int testCase=1; testCase <= 10; testCase++) {
			int[][] ladder = new int[100][100];
			int testCaseNumber = Integer.parseInt(sc.nextLine());
			int current_x = 98;
			int current_y = 0;

			// 사다리 입력 받기
			for(int x=0; x<100; x++) {
				String[] nums = sc.nextLine().split(" ");
				for (int y=0; y<100; y++) {
					ladder[x][y] = Integer.parseInt(nums[y]);
					if (ladder[x][y] == 2) {
						current_y = y;
					}
				}
			}
//			System.out.println(current_y);
			while(true) {
				if (current_x == 0) break;
				if (0 <= current_y-1 && ladder[current_x][current_y-1] == 1) {
					while(0<= current_y-1 && ladder[current_x][current_y-1] == 1){ current_y--;}
				}
				else if (current_y+1 < 100 && ladder[current_x][current_y+1] == 1) {
					while(current_y+1 < 100 && ladder[current_x][current_y+1] == 1){current_y++;}
				}
				current_x--;	
			}
			System.out.println("#" + String.valueOf(testCase) + " " + String.valueOf(current_y));
			
		}
    
    
    
    }
    
    
}