package week7.AlmondBreez3;

import java.util.*;

class 인사고과 {
    public int solution(int[][] scores) {
        int n = scores.length;
        int[] totalScores = new int[n];
        boolean[] valid = new boolean[n];
        Arrays.fill(valid, true);

        // Calculate total scores
        for (int i = 0; i < n; i++) {
            totalScores[i] = scores[i][0] + scores[i][1];
        }

        int answer = 1;
        // Determine if each employee is valid
        for (int i = 1; i < n; i++) {
            if (totalScores[i] > totalScores[0]) {
                if (check(scores[i],scores)) {
                    answer++;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (scores[i][0] > scores[0][0] && scores[i][1] > scores[0][1]) {
                answer = -1;
            }
        }

        return answer;
    }
    public boolean check(int[] a, int[][] scores) {
        for (int j = 0; j < scores.length; j++) {
            if (a[0] < scores[j][0] && a[1] < scores[j][1]) {
                return false;
            }
        }
        return true;
    }
}
