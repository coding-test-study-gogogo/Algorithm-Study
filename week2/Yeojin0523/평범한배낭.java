// 2024-07-20
package week2;

import java.util.Scanner;

public class 평범한배낭 {

    static int N;
    static int K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        K = sc.nextInt();
        
        int[] W = new int[N];
        int[] V = new int[N];
        
        for (int i = 0; i < N; i++) {
            W[i] = sc.nextInt(); // 무게
            V[i] = sc.nextInt(); // 가치
        }
        
        System.out.println(maxValue(W, V, N, K));
        sc.close();
    }

    public static int maxValue(int[] W, int[] V, int N, int K) {
        int[] dp = new int[K + 1];
        
        for (int i = 0; i < N; i++) {
            for (int j = K; j >= W[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
            }
        }
        
        return dp[K];
    }
}

