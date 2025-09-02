import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in;
	static StringTokenizer st;
	static int studentCount;
	static int compareCount;
	static Deque<Integer> [] studentInMyFront;
	static Deque<Integer> line;
	static boolean[] lined;
	
	public static void searching(int peopleNum) {
		lined[peopleNum] = true;
		while(!studentInMyFront[peopleNum].isEmpty()) {
			int nextStudentNum = studentInMyFront[peopleNum].poll();
			if(!lined[nextStudentNum]) searching(nextStudentNum);
		}
		
		line.add(peopleNum);

	}
	
	
	
	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(in.readLine());
		studentCount = Integer.parseInt(st.nextToken());
		compareCount = Integer.parseInt(st.nextToken());
		
		studentInMyFront = new ArrayDeque[studentCount+1];
        for (int i = 0; i < studentInMyFront.length; i++) {
        	studentInMyFront[i] = new ArrayDeque<>();
        }
        
        
		int student1, student2;
		for(int compare = 1; compare <= compareCount; ++compare) {
			st = new StringTokenizer(in.readLine());
			student1 = Integer.parseInt(st.nextToken());
			student2 = Integer.parseInt(st.nextToken());

			studentInMyFront[student2].add(student1);
		}
		
		line = new ArrayDeque<>();
		lined = new boolean[studentCount+1];
		
		for(int studentNum = 1; studentNum <= studentCount; studentNum++) {
			if(!lined[studentNum]) searching(studentNum);
		}
			
		// 나머지 줄세우기
		for(int num: line) {
			System.out.print(num);
			System.out.print(" ");
		}
	}

}
