package week2.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 퇴사2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] duration = new int[n + 1];
        int[] money = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            duration[i] = Integer.parseInt(st.nextToken());
            money[i] = Integer.parseInt(st.nextToken());
        }

        // dp
        int max = 0; // 현재까지의 최대 이익
        for (int i = 1; i <= n; i++) {
            int endDay = duration[i] + i - 1;
            if (endDay <= n) {
                dp[endDay] = Math.max(dp[endDay], max + money[i]);
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
