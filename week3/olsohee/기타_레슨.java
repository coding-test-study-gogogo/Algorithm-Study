package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 기타_레슨 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 강의 수
        int m = Integer.parseInt(st.nextToken()); // 블루레이 수

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            start = Math.max(start, arr[i]);
            end += arr[i];
        }

        // 이분탐색 (하나의 블루레이의 최대 크기)
        while (start <= end) {
            int mid = (start + end) / 2;

            int cnt = 0;
            int sum = 0;
            for (int i : arr) {
                if (sum + i <= mid) {
                    sum += i;
                } else {
                    cnt++;
                    sum = i;
                }
            }
            cnt++;

            if (cnt > m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }
}
