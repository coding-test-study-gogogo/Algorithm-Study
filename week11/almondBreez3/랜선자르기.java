package week11.almondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 랜선자르기 {
    public static int N,M;
    public static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        long start = 0;
        long end = arr[arr.length-1]+1;
        long mid = 0;
        while (start<end) {
            mid = (start+end) / 2;

            long count = 0;

            for (int i =1; i <= N; i++) {
                count += (arr[i]/mid);
            }

            if (count< M) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start-1);
    }
}