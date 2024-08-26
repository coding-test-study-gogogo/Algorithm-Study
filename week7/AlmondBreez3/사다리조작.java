package week7.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

class 사다리조작{
    public static int N, M, H;
    public static int[][] arr;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H + 1][N + 1];  // Adjust the size to match 1-based index

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;  // Ladder exists between column b and b+1 at height a
        }

        dfs(1, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void dfs(int x, int depth) {
        if (check()) {
            answer = Math.min(answer, depth);
            return;
        }
        if (depth >= 3 || depth >= answer) return;

        for (int i = x; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == 0 && arr[i][j + 1] == 0) {  // Can place a ladder here
                    arr[i][j] = 1;
                    dfs(i, depth + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    public static boolean check() {
        for (int i = 1; i <= N; i++) {
            int pos = i;
            for (int j = 1; j <= H; j++) {
                if (arr[j][pos] == 1) {
                    pos++;
                } else if (pos > 1 && arr[j][pos - 1] == 1) {
                    pos--;
                }
            }
            if (pos != i) return false;
        }
        return true;
    }
}
