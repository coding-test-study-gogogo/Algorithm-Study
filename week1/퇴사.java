import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int N;
    public static int[] arr;
    public static int[] weight;
    public static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        weight = new int[N];
        dp =  new int[N+1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] =  Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        //점화식

        //초기값
        for (int i = 0; i < N; i++) {
            if (i + arr[i] <= N) {
                dp[i+arr[i]] = Math.max(dp[i] + weight[i], dp[i+arr[i]]);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }



        System.out.println(dp[N]);
    }
}