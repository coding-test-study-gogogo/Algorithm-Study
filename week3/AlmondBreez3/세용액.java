package week3.AlmondBreez3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 세용액 {
    public static int N;
    public static long[] arr;
    public static long[] result;
    public static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        result = new long[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N - 2; i++) {
            findThreeElements(i);
        }

        for (long a : result) {
            System.out.print(a + " ");
        }
    }

    public static void findThreeElements(int index) {
        int start = index + 1;
        int end = N - 1;

        while (start < end) {
            long sum = arr[index] + arr[start] + arr[end];
            long absSum = Math.abs(sum);

            if (absSum < min) {
                min = absSum;
                result[0] = arr[index];
                result[1] = arr[start];
                result[2] = arr[end];
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }
    }
}
