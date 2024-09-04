package week9.olsohee;

import java.util.*;

class 카운트_다운 {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= target; i++) {
            // 볼
            if (i >= 50) {
                if (dp[i][0] > dp[i - 50][0] + 1) {
                    dp[i][0] = dp[i - 50][0] + 1;
                    dp[i][1] = dp[i - 50][1] + 1;
                } else if (dp[i][0] == dp[i - 50][0] + 1) {
                    dp[i][1] = Math.max(dp[i][1], dp[i - 50][1] + 1);
                }
            }

            for (int j = 1; j <= 20; j++) {
                // 트리플
                if (j * 3 <= i) {
                    if (dp[i][0] > dp[i - j * 3][0] + 1) {
                        dp[i][0] = dp[i - j * 3][0] + 1;
                        dp[i][1] = dp[i - j * 3][1];
                    } else if (dp[i][0] == dp[i - j * 3][0] + 1) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - j * 3][1]);
                    }
                }

                // 더블
                if (j * 2 <= i) {
                    if (dp[i][0] > dp[i - j * 2][0] + 1) {
                        dp[i][0] = dp[i - j * 2][0] + 1;
                        dp[i][1] = dp[i - j * 2][1];
                    } else if (dp[i][0] == dp[i - j * 2][0] + 1) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - j * 2][1]);
                    }
                }

                // 싱글
                if (j <= i) {
                    if (dp[i][0] > dp[i - j][0] + 1) {
                        dp[i][0] = dp[i - j][0] + 1;
                        dp[i][1] = dp[i - j][1] + 1;
                    } else if (dp[i][0] == dp[i - j][0] + 1) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - j][1] + 1);
                    }
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = dp[target][0];
        answer[1] = dp[target][1];
        return answer;
    }
}
