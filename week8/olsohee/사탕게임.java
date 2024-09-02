package week8.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 사탕게임 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static char[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 각 행에서 오른쪽과 변경
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                swap(i, j, i, j + 1);
                search();
                swap(i, j, i, j + 1);
            }
        }

        // 각 열에서 아래와 변경
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                swap(j, i, j + 1, i);
                search();
                swap(j, i, j + 1, i);
            }
        }

        System.out.println(answer);
    }

    private static void swap(int y1, int x1, int y2, int x2) {
        char temp = map[y1][x1];
        map[y1][x1] = map[y2][x2];
        map[y2][x2] = temp;
    }

    private static void search() {
        // 행을 기준으로
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = 0; j < n - 1; j++) {
                if (map[i][j] == map[i][j + 1]) {
                    cnt++;
                } else {
                    answer = Math.max(answer, cnt);
                    cnt = 1;
                }
                answer = Math.max(answer, cnt);
            }
        }

        // 열을 기준으로
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = 0; j < n - 1; j++) {
                if (map[j][i] == map[j + 1][i]) {
                    cnt++;
                } else {
                    answer = Math.max(answer, cnt);
                    cnt = 1;
                }
                answer = Math.max(answer, cnt);
            }
        }
    }
}
