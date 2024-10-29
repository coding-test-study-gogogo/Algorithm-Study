package week17.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2048 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, copyMap(map));
        System.out.println(answer);
    }

    private static void dfs(int cnt, int[][] map) {
        if (cnt == 5) {
            // 5번을 다 이동한 경우
            answer = Math.max(answer, getMaxBlock(map));
            return;
        }

        dfs(cnt + 1, up(copyMap(map)));
        dfs(cnt + 1, down(copyMap(map)));
        dfs(cnt + 1, right(copyMap(map)));
        dfs(cnt + 1, left(copyMap(map)));
    }

    private static int[][] up(int[][] map) {
        int[][] newMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            int lastIdx = 0;
            for (int j = 0; j < n; j++) {
                if (map[j][i] == 0) continue;

                if (map[j][i] == newMap[lastIdx][i]) {
                    newMap[lastIdx][i] *= 2;
                    lastIdx++;
                } else {
                    if (newMap[lastIdx][i] == 0) {
                        newMap[lastIdx][i] = map[j][i];
                    } else {
                        newMap[++lastIdx][i] = map[j][i];
                    }
                }
            }
        }
        return newMap;
    }

    private static int[][] down(int[][] map) {
        int[][] newMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            int lastIdx = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] == 0) continue;

                if (map[j][i] == newMap[lastIdx][i]) {
                    newMap[lastIdx][i] *= 2;
                    lastIdx--;
                } else {
                    if (newMap[lastIdx][i] == 0) {
                        newMap[lastIdx][i] = map[j][i];
                    } else {
                        newMap[--lastIdx][i] = map[j][i];
                    }
                }
            }
        }
        return newMap;
    }

    private static int[][] right(int[][] map) {
        int[][] newMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            int lastIdx = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j] == 0) continue;

                if (map[i][j] == newMap[i][lastIdx]) {
                    newMap[i][lastIdx] *= 2;
                    lastIdx--;
                } else {
                    if (newMap[i][lastIdx] == 0) {
                        newMap[i][lastIdx] = map[i][j];
                    } else {
                        newMap[i][--lastIdx] = map[i][j];
                    }
                }
            }
        }
        return newMap;
    }

    private static int[][] left(int[][] map) {
        int[][] newMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            int lastIdx = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) continue;

                if (map[i][j] == newMap[i][lastIdx]) {
                    newMap[i][lastIdx] *= 2;
                    lastIdx++;
                } else {
                    if (newMap[i][lastIdx] == 0) {
                        newMap[i][lastIdx] = map[i][j];
                    } else {
                        newMap[i][++lastIdx] = map[i][j];
                    }

                }
            }
        }
        return newMap;
    }

    private static int getMaxBlock(int[][] map) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    private static int[][] copyMap(int[][] origin) {
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = origin[i][j];
            }
        }
        return copyMap;
    }
}
