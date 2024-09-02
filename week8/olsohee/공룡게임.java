package week8.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 공룡게임 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine()) + 1;
        long[][] dp = new long[1002][2];

        dp[0][0] = 0;
        dp[0][1] = 1;
        dp[1][0] = 1;
        dp[1][1] = 0;
        dp[2][0] = 1;
        dp[2][1] = 0;
        dp[3][0] = 2;
        dp[3][1] = 1;

        for (int i = 4; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0] + dp[i - 3][0];
            dp[i][0] %= 1000000007;

            dp[i][1] = dp[i - 1][1] + dp[i - 2][0] + dp[i - 2][1] * 2 + dp[i - 3][0] * 2 + dp[i - 3][1] * 3;
            dp[i][1] %= 1000000007;
        }

        System.out.println(dp[n][1]);
    }
}
