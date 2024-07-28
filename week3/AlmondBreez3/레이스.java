package week3.AlmondBreez3;

import java.util.*;
import java.io.*;

class 레이스 {
    public static int[] arr;
    public static int N, M, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int start = 1;
        int end = N;
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (canPlace(mid) >= M) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        int lastPlaced = -result;
        int placed = 0;

        for (int i = 0; i < C; i++) {
            if (arr[i] - lastPlaced >= result) {
                sb.append('1');
                lastPlaced = arr[i];
                placed++;
                if (placed == M) break;
            } else {
                sb.append('0');
            }
        }

        while (sb.length() < C) {
            sb.append('0');
        }

        System.out.println(sb.toString());
    }

    public static int canPlace(int dist) {
        int count = 1;
        int lastPos = arr[0];
        for (int i = 1; i < C; i++) {
            if (arr[i] - lastPos >= dist) {
                count++;
                lastPos = arr[i];
            }
        }
        return count;
    }
}
