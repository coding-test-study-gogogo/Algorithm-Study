package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 파이프_옮기기_1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // flag: 1-가로, 2-세로, 3-대각선
        dfs(1, 2, 1);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int flag) {

        if (y == n && x == n) {
            answer++;
            return;
        }

        switch (flag) {
            case 1:
                if (canMoveToRight(y, x)) {
                    dfs(y, x + 1, 1);
                }
                if (canMoveToDiagonal(y, x)) {
                    dfs(y + 1, x + 1, 3);
                }
                break;
            case 2:
                if (canMoveToDown(y, x)) {
                    dfs(y + 1, x, 2);
                }
                if (canMoveToDiagonal(y, x)) {
                    dfs(y + 1, x + 1, 3);
                }
                break;
            case 3:
                if (canMoveToRight(y, x)) {
                    dfs(y, x + 1, 1);
                }
                if (canMoveToDown(y, x)) {
                    dfs(y + 1, x, 2);
                }
                if (canMoveToDiagonal(y, x)) {
                    dfs(y + 1, x + 1, 3);
                }
                break;
        }
    }

    private static boolean canMoveToRight(int y, int x) {
        if (x + 1 <= n && map[y][x + 1] == 0) return true;
        return false;
    }

    private static boolean canMoveToDown(int y, int x) {
        if (y + 1 <= n && map[y + 1][x] == 0) return true;
        return false;
    }

    private static boolean canMoveToDiagonal(int y, int x) {
        if (x + 1 <= n && y + 1 <= n && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0) return true;
        return false;
    }
}
