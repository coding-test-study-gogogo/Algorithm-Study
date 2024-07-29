package week3.olsohee;

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
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int budget = Integer.parseInt(br.readLine());

        // 상한액 이분탐색
        int start = 1;
        int end = budget;
        while (start <= end) {
            int mid = (start + end) / 2;

            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += (arr[i] > mid) ? mid : arr[i];
            }

            if (sum > budget) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        // end: 상한액 출력
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, (arr[i] > end) ? end: arr[i]);
        }
        System.out.println(answer);
    }
}
