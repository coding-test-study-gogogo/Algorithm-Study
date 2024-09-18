package week11.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 자두나무 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] tree = new int[t + 1][3];
        int[][] dp = new int[t + 1][w + 1];

        for (int i = 1; i <= t; i++) {
            int num = Integer.parseInt(br.readLine());
            tree[i][num] = 1;
        }

        // dp 배열 채우기
        dp[1][0] = tree[1][1];
        dp[1][1] = tree[1][2];

        for (int i = 2; i <= t; i++) {
            for (int j = 0; j <= w; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + tree[i][1];
                    continue;
                }
                // i초에 1번 나무에 있는 경우
                if (j % 2 == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + tree[i][1], dp[i - 1][j] + tree[i][1]);
                }
                // i초에 2번 나무에 있는 경우
                else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + tree[i][2], dp[i - 1][j] + tree[i][2]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= w; i++) {
            answer = Math.max(answer, dp[t][i]);
        }
        System.out.println(answer);
    }
}
