package week7;
import java.util.ArrayList;
import java.util.Scanner;
public class 억억단을외우자 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int e = sc.nextInt();
		sc.nextLine();
		ArrayList<Integer> s = new ArrayList<>();
		
		while(true) {
			String input = sc.nextLine();
			if(input.equals(" ")) {
				break;
			}
			int number = Integer.parseInt(input);
			s.add(number);
		}
		
		int[] sArray = s.toArray(new int[s.size()]);
		
		int arrays = solution(e, sArray);
		
		Arrays.sort(solution, )
		
		sc.close();
	}
	public int[] solution(int e, int[]starts) {
		int[] answer = {};
		int maxCount = 0;
		for(int j = 0; j < starts.length; j++) {
			for(int i = e; i < starts[j]; i++) {
				int count = getCount(starts[j]);
				maxCount = Math.max(count, maxCount);
			}
			answer[j] = maxCount;
		}
		return answer;
	}
	public int getCount(int N) {
		int count = 0;
		for(int i = 1; i * i <= N; i++){
			if(i * i == N)
				count++;
			else if(N % i == 0)
				count += 2;
		}
		return count;
	}
}