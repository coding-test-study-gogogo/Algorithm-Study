package week10.almondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 도토리숨기기{
    public static int L,M,N;
    public static int[][] arr;
    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()); // 도토리
        arr = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());


            arr[i][0]=a;
            arr[i][1]=b;
            arr[i][2]=len;

        }

        System.out.println(search());

    }
    public static long search() {
        int start = 1;
        int end = L;
        while (start<=end) {
            int mid = (start+end) / 2;

            //특정 범위에 있는 개수 구하기
            if (count(mid) < N) {
                start = mid+ 1;
            } else {
                end = mid- 1;
            }
        }

        return start;
    }
    public static long count(int mid) {
        long c = 0;
        for (int[] temp : arr) {
            int tempA = temp[0];
            int tempB = temp[1];
            int tempLen = temp[2];

            if (tempA > mid) continue;
            int end = Math.min(mid,tempB);
            if (end >= tempA) {
                c += (end - tempA)/tempLen + 1;
            }
        }
        return c;
    }
}