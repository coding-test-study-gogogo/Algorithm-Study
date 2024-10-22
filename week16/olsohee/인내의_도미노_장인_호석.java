package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인내의_도미노_장인_호석 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 세로
    static int m; // 가로
    static int r; // 라운드 횟수
    static int[][] map;
    static boolean[][] fall;
    static int score;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        fall = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            // 공격
            st = new StringTokenizer(br.readLine());
            score += attack(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken());

            // 수비
            st = new StringTokenizer(br.readLine());
            defense(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(score).append('\n');

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sb.append(fall[i][j] ? "F " : "S ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static int attack(int y, int x, String dir) {
        int fallCnt = 0;
        int max;

        switch (dir) {
            case "E": // 오른쪽
                max = map[y][x] - 1;
                for (int nx = x; nx <= m; nx++) {
                    if (!fall[y][nx]) {
                        fall[y][nx] = true;
                        fallCnt++;
                    }

                    if (nx == m || max == 0) {
                        break;
                    }

                    // 다음 도미노가 넘어져 있는 경우
                    if (fall[y][nx + 1]) {
                        max--;
                    } else {
                        max = Math.max(max - 1, map[y][nx + 1] - 1);
                    }
                }
                break;
            case "W": // 왼쪽
                max = map[y][x] - 1;
                for (int nx = x; nx >= 1; nx--) {
                    if (!fall[y][nx]) {
                        fall[y][nx] = true;
                        fallCnt++;
                    }

                    if (nx == 1 || max == 0) {
                        break;
                    }

                    // 다음 도미노가 넘어져 있는 경우
                    if (fall[y][nx - 1]) {
                        max--;
                    } else {
                        max = Math.max(max - 1, map[y][nx - 1] - 1);
                    }
                }
                break;
            case "S": // 아래
                max = map[y][x] - 1;
                for (int ny = y; ny <= n; ny++) {
                    if (!fall[ny][x]) {
                        fall[ny][x] = true;
                        fallCnt++;
                    }

                    if (ny == n || max == 0) {
                        break;
                    }

                    // 다음 도미노가 넘어져 있는 경우
                    if (fall[ny + 1][x]) {
                        max--;
                    } else {
                        max = Math.max(max - 1, map[ny + 1][x] - 1);
                    }
                }
                break;
            case "N": // 위
                max = map[y][x] - 1;
                for (int ny = y; ny >= 1; ny--) {
                    if (!fall[ny][x]) {
                        fall[ny][x] = true;
                        fallCnt++;
                    }

                    if (ny == 1 || max == 0) {
                        break;
                    }

                    // 다음 도미노가 넘어져 있는 경우
                    if (fall[ny - 1][x]) {
                        max--;
                    } else {
                        max = Math.max(max - 1, map[ny - 1][x] - 1);
                    }
                }
        }
        return fallCnt;
    }

    private static void defense(int y, int x) {
        fall[y][x] = false; // 세우기
    }
}
