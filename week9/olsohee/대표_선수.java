package week9.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 대표_선수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n, m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.sort(arr[i]);
        }

        int[] idxArr = new int[n];

        int minIdx = 0;
        int diff = Integer.MAX_VALUE;

        while (true) {
            // 차이의 최댓값 구하기
            int minNum = Integer.MAX_VALUE;
            int maxNum = 0;
            for (int i = 0; i < n; i++) {
                if (minNum > arr[i][idxArr[i]]) {
                    minNum = arr[i][idxArr[i]];
                    minIdx = i;
                }
                maxNum = Math.max(maxNum, arr[i][idxArr[i]]);
            }

            diff = Math.min(diff, maxNum - minNum);

            // 최소값의 idx 증가시키기
            idxArr[minIdx]++;
            if (idxArr[minIdx] == m) break;
        }

        System.out.println(diff);
    }
}
