package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 레이스 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 이분탐색: 간격 구하기 (최대)
        int min = 1;
        int max = arr[arr.length - 1] - arr[0];
        while (min <= max) {
            int mid = (min + max) / 2;

            int cnt = 1;
            int before = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] - before >= mid) {
                    cnt++;
                    before = arr[i];
                }
            }

            // 간격 늘리기
            if (cnt >= m) {
                min = mid + 1;
            }
            // 간격 줄이기
            else {
                max = mid - 1;
            }
        }

        // max가 간격이 됨
        String answer = "1";
        int before = arr[0];
        int cnt = 1;
        for (int i = 1; i < arr.length; i++) {
            if (cnt == m) {
                answer += "0";
                continue;
            }
            if (arr[i] - before >= max) {
                answer += "1";
                before = arr[i];
                cnt++;
            } else {
                answer += "0";
            }
        }
        System.out.println(answer);
    }
}
