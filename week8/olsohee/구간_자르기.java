package week8.olsohee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 구간_자르기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] count = new int[1000001];

        // 0~10 위치의 선분 = count[0]~count[9]에 값이 채워짐
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            count[start]++;
            count[end]--;
            max = Math.max(max, end);
            min = Math.min(min, start);
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // 투포인터로 s, e 찾기
        int start = min;
        int end = min;
        int sum = count[start];
        while (end < max) {
            if (sum < k) {
                end++;
                sum += count[end];
            }

            else if (sum > k) {
                sum -= count[start];
                start++;
            }

            else if (sum == k) {
                if (start == min) {
                    start = 0;
                }
                System.out.println(start + " " + (end + 1));
                return;
            }
        }

        System.out.println("0 0");
    }
}
