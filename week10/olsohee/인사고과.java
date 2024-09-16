package week10.olsohee;

import java.util.*;

class 인사고과 {
    public int solution(int[][] scores) {
        int score1 = scores[0][0];
        int score2 = scores[0][1];

        Arrays.sort(scores, (o1, o2) -> {
            if (o2[0] == o1[0]) {
                return o1[1] - o2[1]; // 태도 점수가 같은 경우 오름차순
            }
            return o2[0] - o1[0]; // 태도 점수 내림차순
        });

        int maxScore = scores[0][1]; // 최대 동료 평가 점수
        List<Score> list = new ArrayList<>();
        list.add(new Score(scores[0][0], scores[0][1]));

        for (int i = 1; i < scores.length; i++) {
            if (scores[i][1] < maxScore) {
                if (scores[i][0] == score1 && scores[i][1] == score2) return -1;
                continue;
            }
            list.add(new Score(scores[i][0], scores[i][1]));
            maxScore = Math.max(maxScore, scores[i][1]);
        }

        int answer = 0;
        Collections.sort(list, (o1, o2) -> o2.sum - o1.sum);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).score1 == score1 && list.get(i).score2 == score2) {
                answer = i + 1;
                break;
            }
        }

        return answer;
    }

    private class Score {
        int score1, score2, sum;

        public Score(int score1, int score2) {
            this.score1 = score1;
            this.score2 = score2;
            this.sum = score1 + score2;
        }
    }
}
