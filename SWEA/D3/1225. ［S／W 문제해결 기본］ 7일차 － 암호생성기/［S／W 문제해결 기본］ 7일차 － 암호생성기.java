import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static BufferedReader in;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static boolean complete;

    public static void main(String[] args) throws IOException{
        in = new BufferedReader(new InputStreamReader(System.in));
        for(int testCase = 1; testCase <= 10; testCase++){
            in.readLine();
            st = new StringTokenizer(in.readLine());
            Deque<Integer> secretNum = new ArrayDeque<>();

            for(int n=1; n <= 8; ++n) secretNum.add(Integer.parseInt(st.nextToken()));

            complete = false;
            int currentNum = 1;
            while(!complete){

                for(int num = 1; num <= 5; ++num){
                    currentNum = secretNum.poll();
                    if(currentNum - num <= 0){
                        secretNum.add(0);
                        complete = true;
                        break;
                    }
                    else{
                        secretNum.add(currentNum-num);
                    }
                }
            }


            sb.append('#').append(testCase).append(' ');
            while(!secretNum.isEmpty()){
                sb.append(secretNum.poll()).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }
}