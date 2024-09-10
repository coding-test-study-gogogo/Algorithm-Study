package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1학년 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        long[][] dp = new long[n][21];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][nums[0]] = 1;

        for (int i = 1; i <= n - 2; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i  - 1][j] != 0) {
                    int plusResult = j + nums[i];
                    int minusResult = j - nums[i];
                    if (plusResult <= 20) {
                        dp[i][plusResult] += dp[i - 1][j];
                    }
                    if (minusResult >= 0) {
                        dp[i][minusResult] += dp[i - 1][j];
                    }
                }
            }
        }

        // nums[n - 1]과 비교
        System.out.println(dp[n - 2][nums[n - 1]]);
    }
}
