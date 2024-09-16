package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 팰린드롬 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static boolean[][] dp;
    static int[] input;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        input = new int[n + 1];
        dp = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // dp
        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                    continue;
                }

                if (input[i] == input[j]) {
                    if (i + 1 == j) dp[i][j] = true;
                    else if (dp[i + 1][j - 1]) dp[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            boolean result = dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
            sb.append(result ? 1 : 0)
                    .append('\n');
        }
        System.out.println(sb);
    }
}
