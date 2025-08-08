import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main
{
	static int N,M;
	static int[] pick;
	

	public static void comb(int depth, int start) {
		if(depth == M) {
			for(int idx=0; idx<depth; idx++) System.out.print(String.valueOf(pick[idx])+ " ");
			System.out.println();
			return;
		}
		for(int number = start; number<=N; number++) {
			pick[depth] = number;
			comb(depth+1, number+1);
		}
	}
	
    public static void main(String args[]) throws Exception{
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	pick = new int[M];
    	
    	comb(0,1);
    }
  
}