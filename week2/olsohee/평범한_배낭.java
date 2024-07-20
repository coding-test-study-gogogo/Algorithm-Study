package week2.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 평범한_배낭 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][k + 1]; //dp[i][j] = i번째 무게까지 고려했을 때 j만큼의 무게를 담았을 때 최대 가치
        int[][] arr = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (arr[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], arr[i][1] + dp[i - 1][j - arr[i][0]]);
                }
            }
        }

        // 최대 k만큼 짐을 싸면서, 가치의 최댓값!
        System.out.println(dp[n][k]);
    }
}
