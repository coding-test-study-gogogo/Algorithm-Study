package week2.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 카드_구매하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] money = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            money[i] = Integer.parseInt(st.nextToken());
            dp[i] = money[i];
        }

        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = i - 1; j >= (i - j); j--) {
                max = Math.max(max, dp[j] + dp[i - j]);
            }

            dp[i] = Math.max(dp[i], max);
        }

        System.out.println(dp[n]); // 카드 n개를 구매하는데 지불하는 최대 금액
    }
}
