package week10.almondBreez3;

// priority Queue같음 -> 아니 원호 기준으로 돌리는 거 같음
import java.util.*;
class 인사고과{
    public int solution(int[][] scores) {
        int n = scores.length;
        List<int[]> arr = new ArrayList<>();
        int[] total = new int[scores.length];

        int answer = 1;
        for (int i = 0; i < scores.length; i++) {
            total[i] = scores[i][0]+scores[i][1];
        }

        for (int i = 1; i < n; i++) {
            if (total[i] > total[0]) {
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