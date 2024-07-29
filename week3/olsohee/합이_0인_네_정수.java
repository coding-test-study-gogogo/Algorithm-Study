package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 합이_0인_네_정수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] ab = new int[n * n];
        int[] cd = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[idx] = arr[i][0] + arr[j][1];
                cd[idx] = arr[i][2] + arr[j][3];
                idx++;
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        int left = 0;
        int right = n * n - 1;
        long answer = 0;

        while (left < n * n && right >= 0) {
            int num1 = ab[left];
            int num2 = cd[right];

            if (num1 + num2 > 0) {
                right--;
            } else if (num1 + num2 < 0) {
                left++;
            } else {
                int sameCnt1 = 0;
                int sameCnt2 = 0;
                while (left < n * n && ab[left] == num1) {
                    sameCnt1++;
                    left++;
                }
                while (right >= 0 && cd[right] == num2) {
                    sameCnt2++;
                    right--;
                }
                answer += (long)sameCnt1 * sameCnt2;
            }
        }
        System.out.println(answer);
    }
}
