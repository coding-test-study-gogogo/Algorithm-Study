package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 퇴사 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int[] time = new int[n + 1];
        int[] money = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            money[i] = Integer.parseInt(st.nextToken());
        }

        int maxMoney = 0; // i날짜 전까지의 최대 수익
        for (int i = 1; i <= n; i++) {

            int end = time[i] + i - 1;

            if (end <= n) {
                dp[end] = Math.max(dp[end], money[i] + maxMoney);
            }

            maxMoney = Math.max(maxMoney, dp[i]);

        }

        System.out.println(maxMoney);
    }
}

