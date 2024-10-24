package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 문자열_판별 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String A = br.readLine();
        int n = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        int[] dp = new int[A.length()];
        for (int i = A.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < A.length(); j++) {
                if (dp[j] == 1) {
                    if (set.contains(A.substring(i, j))) {
                        dp[i] = 1;
                        break;
                    }
                }
            }
            if (set.contains(A.substring(i))) {
                dp[i] = 1;
            }
        }

        System.out.println(dp[0]);
    }
}
