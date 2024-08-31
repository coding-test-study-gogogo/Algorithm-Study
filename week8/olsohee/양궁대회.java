package week8.olsohee;

import java.util.*;

class 양궁대회 {

    int n;
    int[] info;
    int max = 0;
    int[] answer;

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;

        getCombination(0, n, new int[11]);

        // 라이언이 우승할 수 없는 경우
        if (max == 0) return new int[]{-1};

        return answer;
    }

    private void getCombination(int idx, int remain, int[] score) {
        if (remain == 0 || idx == 11) {
            int result = getResult(score); // 라이언 점수 - 어피치 점수 계산
            // 어피치가 이기는 경우
            if (result <= 0) {
                return;
            }
            // 라이언이 이기는 경우
            if (result == max) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] == score[i]) continue;
                    else if (answer[i] > score[i]) break;
                    else {
                        answer = score.clone();
                        break;
                    }
                }
            }
            if (result > max) {
                max = result;
                answer = score.clone();
            }
            return;
        }

        for (int i = 0; i <= remain; i++) {
            score[idx] = i;
            getCombination(idx + 1, remain - i, score);
            score[idx] = 0;
        }
    }

    private int getResult(int[] score) {
        int totalScore1 = 0; // 어피치 총점
        int totalScore2 = 0; // 라이언 총점
        for (int i = 0; i <= 10; i++) {
            if (info[i] == 0 && score[i] == 0) continue;
            if (info[i] >= score[i]) {
                totalScore1 += (10 - i);
            } else {
                totalScore2 += (10 - i);
            }
        }

        return totalScore2 - totalScore1;
    }
}
