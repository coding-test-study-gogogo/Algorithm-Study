package week7.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 안녕 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        int[] stamina = new int[n + 1];
        int[] happy = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            stamina[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[101][n + 1];

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > stamina[j]) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - stamina[j]][j - 1] + happy[j]);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[100][n]);
    }
}
