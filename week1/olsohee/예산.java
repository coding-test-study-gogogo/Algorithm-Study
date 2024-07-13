package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 예산 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int min = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int m = Integer.parseInt(br.readLine());

        while (min <= max) {
            int mid = (max + min) / 2;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += (arr[i] >= mid) ? mid : arr[i];
            }

            // 상한선 낮추기
            if (sum > m) {
                max = mid - 1;
            }
            // 상한선 높이기
            else {
                min = mid + 1;
            }
        }

        System.out.println(max);
    }
}
