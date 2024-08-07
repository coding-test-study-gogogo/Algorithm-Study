package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 대표_선수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
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

        int answer = Integer.MAX_VALUE;

        int[] idxArr = new int[n];
        while (true) {
            int minIdx = -1;
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int i = 0; i < n; i++) {
                if (min > arr[i][idxArr[i]]) {
                    min = arr[i][idxArr[i]];
                    minIdx = i;
                }
                max = Math.max(max, arr[i][idxArr[i]]);
            }

            answer = Math.min(answer, max - min);
            idxArr[minIdx]++;
            if (idxArr[minIdx] == m) {
                break;
            }
        }

        // 능력치의 최대~최소 차이의 최솟값
        System.out.println(answer);
    }
}
