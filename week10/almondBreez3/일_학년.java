package week10.almondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 일_학년{
    public static int N;
    public static int[][] dp;
    public static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());



        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0;i < N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N][21];
        dp[0][arr[0]] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 21; j++) {
                int plus = j+arr[i];
                int minus = j - arr[i];

                if (plus <= 20 && dp[i-1][j]!=0) {
                    dp[i][plus] += dp[i-1][j];
                }


                if (minus>=0 && dp[i-1][j]!=0) {
                    dp[i][minus] += dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N-2][arr[N-1]]);
    }
}