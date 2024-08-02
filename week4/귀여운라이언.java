package week4;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 귀여운라이언 {
    public static int N,K;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N;i++) {
            if (arr[i]==1) {
                count++;
            }

            // 처음에 풀 때 놓친 부분 count--ㅊ
            while (count >= K) {
                min = Math.min(min, i-start +1);
                if (arr[start]==1) {
                    count--;
                }
                start++;
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}