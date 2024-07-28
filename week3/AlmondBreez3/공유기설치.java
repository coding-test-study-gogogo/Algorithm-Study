package week3.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 공유기설치 {
    public static int[] arr;
    public static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i =0; i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int start = 1;
        int end = arr[N-1] - arr[0] +1;

        while(start<end) {
            int mid = (start + end) / 2;
            if (canGet(mid) < M) {
                end = mid;
            } else {
                start = mid+1;
            }
        }

        System.out.println(start-1);
    }
    public static int canGet(int num) {
        int dist = arr[0];
        int count=1;

        for (int i = 1; i < N; i++) {
            if (arr[i] - dist >= num) {
                count++;
                dist = arr[i];
            }
        }
        return count;
    }
}