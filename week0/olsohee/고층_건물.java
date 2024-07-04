package week0.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 고층_건물 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            int cnt = 0;

            // 오른쪽 빌딩: 최대 기울기 찾기
            double maxSlope = 0;
            for (int j = i + 1; j < n; j++) {
                if (j == i + 1) {
                    maxSlope = (double)(arr[j] - arr[i]) / (j - i); // 바로 오른쪽 건물과의 기울기로 초기화
                    cnt++;
                    continue;
                }

                double slope = (double)(arr[j] - arr[i]) / (j - i);
                if (maxSlope < slope) {
                    cnt++;
                    maxSlope = slope;
                }
            }

            // 왼쪽 빌딩: 최저 기울기 찾기
            double minSlope = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (j == i - 1) {
                    minSlope = (double)(arr[i] - arr[j]) / (i - j); // 바로 왼쪽 건물과의 기울기로 초기화
                    cnt++;
                    continue;
                }
                double slope = (double)(arr[i] - arr[j]) / (i - j);
                if (minSlope > slope) {
                    cnt++;
                    minSlope = slope;
                }
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
