package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 오목 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map = new int[19][19];
    static int[] dy = {0, 1, -1, 1};
    static int[] dx = {1, 0, 1, 1};
    static int answer;
    static int[] answerNode = new int[2];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (map[i][j] == 0) continue;
                // 오른쪽 이동
                if (j == 0 || (j - 1 >= 0 && map[i][j - 1] != map[i][j])) {
                    dfs(i, j, i, j, 0, 1);
                }
                // 아래 이동
                if (i == 0 || (i - 1 >= 0 && map[i - 1][j] != map[i][j])) {
                    dfs(i, j, i, j, 1, 1);
                }
                // 오른쪽 위 대각선 이동
                if ((i == 18 || j == 0) || (i + 1 < 19  && j - 1 >= 0 && map[i + 1][j - 1] != map[i][j])) {
                    dfs(i, j, i, j, 2, 1);
                }
                // 오른쪽 아래 대각선 이동
                if ((i == 0 || j == 0) || (i - 1 >= 0  && j - 1 >= 0 && map[i - 1][j - 1] != map[i][j])) {
                    dfs(i, j, i, j, 3, 1);
                }
            }
        }

        System.out.println(answer);
        if (answer != 0) {
            System.out.print(answerNode[0] + " " + answerNode[1]);
        }

    }

    private static void dfs(int startY, int startX, int y, int x, int dir, int cnt) {
        if (cnt == 5) {
            // 6목이 아니어야 답 가능
            boolean isAnswer = false;
            switch (dir) {
                case 0:
                    if (x == 18 || (x + 1 < 19 && map[y][x + 1] != map[y][x])) {
                        isAnswer = true;
                    }
                    break;
                case 1:
                    if (y == 18 || (y + 1 < 19 && map[y + 1][x] != map[y][x])) {
                        isAnswer = true;
                    }
                    break;
                case 2:
                    if ((y == 0 || x == 18) || (y - 1 >= 0 && x + 1 < 19 && map[y - 1][x + 1] != map[y][x])) {
                        isAnswer = true;
                    }
                    break;
                case 3:
                    if ((y == 18 || x == 18) || (y + 1 < 19 && x + 1 < 19 && map[y + 1][x + 1] != map[y][x])) {
                        isAnswer = true;
                    }
            }

            if (isAnswer) {
                answer = map[y][x];
                answerNode[0] = startY + 1;
                answerNode[1] = startX + 1;
            }

            return;
        }

        int ny = y + dy[dir];
        int nx = x + dx[dir];
        if (ny >= 0 && ny < 19 && nx >= 0 && nx < 19) {
            if (map[y][x] == map[ny][nx]) {
                dfs(startY, startX, ny, nx, dir, cnt + 1);
            }
        }
    }
}
