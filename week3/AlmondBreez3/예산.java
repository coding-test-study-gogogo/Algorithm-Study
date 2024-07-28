package week3.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 예산 {
    public static int N, M;
    public static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i =0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
        }
        M = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        long start = 0;
        long end = arr[N-1]+1;
        while (start < end) {
            long mid = (start+end) / 2;

            if (getSum(mid) <= M) {
                start = mid + 1;
            } else {
                end = mid ;
            }
        }
        System.out.println(start-1);
    }
    public static long getSum(long num) {
        long sum = 0;
        for (int i =0; i < N;i++) {
            if (arr[i] < num) {
                sum += arr[i];
            } else {
                sum += num;
            }
        }
        return sum;
    }
}
