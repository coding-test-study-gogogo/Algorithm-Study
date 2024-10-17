package week9.Almondbreez3;

import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class 내려가기 {
    public static int N;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N+1][4]; // 1-based indexing

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열 초기화
        int[][] minDp = new int[N+1][4];
        int[][] maxDp = new int[N+1][4];

        for (int i = 1; i <= 3; i++) {
            minDp[1][i] = arr[1][i];
            maxDp[1][i] = arr[1][i];
        }

        for (int i = 2; i <= N; i++) {
            minDp[i][1] = Math.min(minDp[i-1][1], minDp[i-1][2]) + arr[i][1];
            minDp[i][2] = Math.min(Math.min(minDp[i-1][1], minDp[i-1][2]), minDp[i-1][3]) + arr[i][2];
            minDp[i][3] = Math.min(minDp[i-1][2], minDp[i-1][3]) + arr[i][3];

            maxDp[i][1] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + arr[i][1];
            maxDp[i][2] = Math.max(Math.max(maxDp[i-1][1], maxDp[i-1][2]), maxDp[i-1][3]) + arr[i][2];
            maxDp[i][3] = Math.max(maxDp[i-1][2], maxDp[i-1][3]) + arr[i][3];
        }

        int minResult = Math.min(Math.min(minDp[N][1], minDp[N][2]), minDp[N][3]);
        int maxResult = Math.max(Math.max(maxDp[N][1], maxDp[N][2]), maxDp[N][3]);

        System.out.println(maxResult + " " + minResult);
    }
}
