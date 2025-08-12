import java.util.*;
import java.io.*;
 


 
public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	
	public static boolean nextPermutation(int[] sequence) {
		int N = sequence.length;
		if (N == 1) return false;
		int pivot = N-2;
		while(pivot >= 0 && sequence[pivot] > sequence[pivot+1]) pivot--;
		if(pivot <0 ) return false;
		int idx = N-1;
		while (sequence[pivot]> sequence[idx]) idx--;
		int temp = sequence[pivot]; sequence[pivot] = sequence[idx]; sequence[idx] = temp;
		int leftIdx = pivot+1, rightIdx = N-1;
		while (leftIdx < rightIdx) {
			int tmp = sequence[leftIdx]; sequence[leftIdx] = sequence[rightIdx]; sequence[rightIdx] = tmp;
			leftIdx++;
			rightIdx--;
		}
		return true;
	}

    
    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	int lastNumber = Integer.parseInt(br.readLine().trim());
    	
    	int[] inputSequence = new int[lastNumber];
    	st = new StringTokenizer(br.readLine());
    	for(int idx=0; idx<lastNumber;idx++) inputSequence[idx] = Integer.parseInt(st.nextToken());
    	if (!nextPermutation(inputSequence)) {
    		System.out.println(-1);
    		return;
    	}
    	for(int num : inputSequence) System.out.print(String.valueOf(num)+" ");
    }
}
