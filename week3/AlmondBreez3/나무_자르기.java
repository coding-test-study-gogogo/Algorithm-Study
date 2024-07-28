package week3.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 나무_자르기 {
    public static int N, M;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 1. 높이 H지정
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long start = 0;
        long end = arr[N-1];
        long res = 0;
        while (start <= end) {
            long mid = (start + end) / 2;

            if (getTree(mid) < M) {
                end = mid - 1;
            } else {
                // 처음에 풀 때는 getTree(mid) == M 일때 break했는데 생각해보니까 그렇게 하면
                // 최댓값을 못구한다!
                res = mid;
                start = mid + 1;
            }
        }

        System.out.println(res);
    }
    public static long getTree(long a) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > a) {
                sum += (arr[i] - a);
            }
            if (sum > M) {
                return sum;
            }
        }
        return sum;
    }
}