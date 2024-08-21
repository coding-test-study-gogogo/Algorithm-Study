package week7.olsohee;

import java.util.*;

class 인사고과 {
    public int solution(int[][] scores) {
        int n = scores[0][0];
        int m = scores[0][1];

        // 제외할 사람 제외하기
        Arrays.sort(scores, (o1, o2) -> {
            if (o2[0] == o1[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        // [0]에 대해서는 내림차순인 상태
        // [1]에 대해서만 비교하기
        int last = scores[0][1];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i][1] < last) {
                if (scores[i][0] == n && scores[i][1] == m) return -1;
                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
                last = scores[i][1];
            }
        }

        // 등수 계산하기
        Arrays.sort(scores, (o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));

        int answer = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][0] + scores[i][1] > n + m) {
                answer++;
            } else {
                break;
            }
        }

        return answer + 1;
    }
}
