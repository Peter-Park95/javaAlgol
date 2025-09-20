import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main{

    static BufferedReader in;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception{

        in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        
        int teamAmount = Integer.parseInt(st.nextToken());
        int ratingSum = Integer.parseInt(st.nextToken());
        int ratingPersonal = Integer.parseInt(st.nextToken());

        int[][] ratingInfo = new int[teamAmount][3];
        Deque<Integer> passRate = new ArrayDeque<>();
        int passTeamCount = 0;
        for(int teamIdx=0; teamIdx<teamAmount; ++teamIdx){
            st = new StringTokenizer(in.readLine());
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());
            int person3 = Integer.parseInt(st.nextToken());
            if(person1 + person2 + person3 < ratingSum) continue;
            if (person1 >= ratingPersonal && person2 >= ratingPersonal && person3 >= ratingPersonal){
                passTeamCount++;
                sb.append(person1).append(' ').append(person2).append(' ').append(person3).append(' ');
            }
        }

        System.out.println(passTeamCount);
        System.out.print(sb);
    }
}