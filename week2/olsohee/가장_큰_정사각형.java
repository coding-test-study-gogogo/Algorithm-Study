package week2.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가장_큰_정사각형 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int answer = 0;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(arr[j - 1]);
            }
        }

        // dp
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (map[i][j] == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                // 위, 오른쪽 옆, 오른쪽 위 대각선 3곳을 고려하기
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;

                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.println(answer * answer);
    }
}
