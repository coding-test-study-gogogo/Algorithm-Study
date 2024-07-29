package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 공유기_설치 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static int c;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
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

            // 많이 설치되면, 사이 간격을 늘리기
            if (cnt >= c) {
                min = mid + 1;
            }
            // 적게 설치되면, 사이 간격을 줄이기
            else {
                max = mid - 1;
            }
        }

        System.out.println(max);
    }
}
