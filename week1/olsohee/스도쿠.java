package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스도쿠 {

    static int[][] map = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s, " ");

            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0, 0);
    }

    static void backTracking(int x, int y) {

        if (y > 8) {
            backTracking(x + 1, 0);
            return;
        }
        if (x > 8) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        if (map[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (checkMap(x, y, i)) {
                    map[x][y] = i;
                    backTracking(x, y + 1);
                }
            }

            map[x][y] = 0;
            return;
        }

        backTracking(x, y + 1);
    }

    static boolean checkMap(int x, int y, int value) {

        for (int i = 0; i < 9; i++) {
            if (map[x][i] == value)
                return false;
        }

        for (int j = 0; j < 9; j++) {
            if (map[j][y] == value) {
                return false;
            }
        }

        int sx = x / 3 * 3;
        int sy = y / 3 * 3;

        for (int i = sx; i < sx + 3; i++) {
            for (int j = sy; j < sy + 3; j++) {
                if (map[i][j] == value)
                    return false;
            }
        }

        return true;
    }
}
