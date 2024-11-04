package week18.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 점프 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = map[i][j];
                if (value == 0) break;
                // 아래로
                if (value + i < n) {
                    dp[value + i][j] += dp[i][j];
                }
                // 오른쪽으로
                if (value + j < n) {
                    dp[i][value + j] += dp[i][j];
                }
            }
        }
        System.out.println(dp[n - 1][n - 1]);
    }
}
