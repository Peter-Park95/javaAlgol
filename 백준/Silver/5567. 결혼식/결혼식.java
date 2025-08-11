import java.util.*;
import java.io.*;
 


 
public class Main {
	static BufferedReader br;
	static StringTokenizer st;

    
    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	int friendNumber = Integer.parseInt(br.readLine().trim());
    	int relationNumber = Integer.parseInt(br.readLine().trim());
    	Set<Integer>[] friends = new HashSet[friendNumber + 1];
    	for (int i = 1; i <= friendNumber; i++) {
    		friends[i] = new HashSet<>();
    	}
    	for(int num = 1; num <= relationNumber; num++) {
    		st = new StringTokenizer(br.readLine());
    		int human1 = Integer.parseInt(st.nextToken());
    		int human2 = Integer.parseInt(st.nextToken());
    		friends[human1].add(human2);
    		friends[human2].add(human1);
    	}
    	Set<Integer> guest = new HashSet<>();
    	// 친구 추가
    	for(int friend : friends[1]) guest.add(friend);
    	
    	// 친구의 친구 추가
    	for(int friend : friends[1]) {
    		for(int friendFriend : friends[friend]) {
    			if (friendFriend != 1) guest.add(friendFriend);
    		}
    	}
    	System.out.println(guest.size());

    }
 
}