package week7.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class K번째_수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int start = 1;
        int end = k;
        int answer = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            // mid 보다 작거나 같은 숫자가 k개
            long cnt = 0;

            for (int i = 1; i <= n; i++) {
                cnt += Math.min(mid / i, n);
            }
            if (cnt < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
                answer = mid;
            }
        }
        System.out.println(answer);

    }
}
