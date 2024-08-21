package week7.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class k번째수 {
    public static int N,K;
    public static int[][] arr;
    public static List<Integer> b = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());


        long lo = 1;
        long hi = K;

        while (lo <hi) {
            long mid =  (lo+hi)/2;
            long count = 0;
            /*
             *  임의의 x에 대해 i번 째 행을 나눔으로써 x보다 작거나 같은 원소의 개수 ->  구구단 원리
             *  누적 합을 구한다.
             *  이 때 각 행의 원소의 개수가 N(열 개수)를 초과하지 않는 선에서 합해주어야 한다.
             */
            for(int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (count >= K) {
                hi = mid;
            } else {
                lo = mid+1;
            }


        }

        System.out.println(lo);
    }
}