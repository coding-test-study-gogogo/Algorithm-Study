package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 세_용액 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long minSum = Long.MAX_VALUE;
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            // 투포인터로 최대한 0에 가까운 mid, end 값 찾기
            int start = i + 1;
            int end = arr.length - 1;

            while (start < end) {
                long sum = (long)arr[i] + arr[start] + arr[end];
                if (Math.abs(sum) < minSum) {
                    minSum = Math.abs(sum);
                    answer = List.of(arr[i], arr[start], arr[end]);
                }

                if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        // 3개 오름차순 출력
        for (Integer i : answer) {
            System.out.print(i + " ");
        }
    }
}
