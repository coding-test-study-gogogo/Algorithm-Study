package week7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 인사고과 {
	public int solution(int[][] scores) {
		int n = scores[0][0];
		int m = scores[0][1];
		
		Arrays.sort(scores, (o1, o2) ->{
			if(o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}else {
				return o2[0] - o1[0];
			}
		}); // 이거는 근무태도 중심으로 내림차순 나열한거
		
		int maxScore = scores[0][1];
		
		
		for(int i = 1; i < scores.length; i++) {
			if(scores[i][1] < maxScore) {
				if(scores[i][0] == n && scores[i][1] == m) // 그것이 완호 점수라면
					return -1;
				scores[i][0] = -1;
				scores[i][1] = -1;
			}else {
				maxScore = scores[i][1];
			}
		}
		
		Arrays.sort(scores, (o1, o2) -> 
			(o2[0] + o2[1]) - (o1[0] + o1[1])
		); 
		
		int answer = 1;
		
		// !! 완호보다 높은지만 알면 돼. 다른 애들은 필요 없음. 동석차 이런거 상관 X
		for(int i = 0; i < scores.length; i++) {
			if(scores[i][0] + scores[i][1] > n + m) {
				answer++;
			}else {
				break;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<int[]> scoreList = new ArrayList<>();
		
		while(sc.hasNext()) {
			int[] scores = new int[2];
			scores[0] = sc.nextInt();
			scores[1] = sc.nextInt();
			scoreList.add(scores);	
		}
		int[][] scoresArray = scoreList.toArray(new int[scoreList.size()][]);
		// 리스트를 배열로 반환
		
		인사고과 sol = new 인사고과();
		int rslt = sol.solution(scoresArray);
		System.out.println(rslt);
		sc.close();
	}
}