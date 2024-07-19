package week2.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 가장_큰_정사각형 {
    public static int N,M;
    public static int[][] arr;
    public static int[][] dp;
    public static boolean[][] visited;
    public static int max = Integer.MIN_VALUE;
    public static int[] dy = {1,0,1};
    public static int[] dx = {0,1,1};
    public static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr= new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        visit = new boolean[N][M];
        List<int[]> lis = new ArrayList<>();

        // 초기화
        int maxLength = 0;
        for (int i = 0; i < N;i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j <M;j++) {
                arr[i][j] = Integer.parseInt(str[j]);
                if (i == 0 || j==0) {
                    dp[i][j] = arr[i][j];
                }
                else if (arr[i][j]==1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i][j-1]),dp[i-1][j])+1;
                }
                maxLength = Math.max(maxLength,dp[i][j]);
            }
        }

        System.out.println(maxLength * maxLength);
    }
}