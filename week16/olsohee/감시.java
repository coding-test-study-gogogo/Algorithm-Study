package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 감시 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static List<Cctv> cctvList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // CCTV인 경우
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvList.add(new Cctv(i, j, map[i][j]));
                }
            }
        }

        // 1. dfs로 각 CCTV 방향 조합 만들기
        dfs(0, cloneMap(map));
        System.out.println(answer);
    }

    private static void dfs(int idx, int[][] map) {
        // 2. 모든 CCTV를 반영했을 때, 사각지대 찾기
        if (cctvList.size() == idx) {
            answer = Math.min(answer, findBlindSpot(map));
            return;
        }

        int[][] newMap;
        switch (cctvList.get(idx).num) {
            case 1:
                newMap = cloneMap(map);
                right(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                down(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                left(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                up(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                break;
            case 2:
                newMap = cloneMap(map);
                right(newMap, cctvList.get(idx));
                left(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                up(newMap, cctvList.get(idx));
                down(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                break;
            case 3:
                newMap = cloneMap(map);
                up(newMap, cctvList.get(idx));
                right(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                right(newMap, cctvList.get(idx));
                down(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                down(newMap, cctvList.get(idx));
                left(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                left(newMap, cctvList.get(idx));
                up(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                break;
            case 4:
                newMap = cloneMap(map);
                left(newMap, cctvList.get(idx));
                up(newMap, cctvList.get(idx));
                right(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                up(newMap, cctvList.get(idx));
                right(newMap, cctvList.get(idx));
                down(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                right(newMap, cctvList.get(idx));
                down(newMap, cctvList.get(idx));
                left(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                newMap = cloneMap(map);
                down(newMap, cctvList.get(idx));
                left(newMap, cctvList.get(idx));
                up(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);

                break;
            case 5:
                newMap = cloneMap(map);
                up(newMap, cctvList.get(idx));
                right(newMap, cctvList.get(idx));
                down(newMap, cctvList.get(idx));
                left(newMap, cctvList.get(idx));
                dfs(idx + 1, newMap);
        }
    }

    private static int findBlindSpot(int[][] map) {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // CCTV, 벽, 감시구역이 아닌 곳이 사각지대
                if (map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    private static void down(int[][] map, Cctv cctv) {
        for (int y = cctv.y + 1; y < n; y++) {
            // 벽인 경우
            if (map[y][cctv.x] == 6) {
                break;
            }
            // CCTV인 경우
            if (map[y][cctv.x] == 1 || map[y][cctv.x] == 2 || map[y][cctv.x] == 3 ||
                    map[y][cctv.x] == 4 || map[y][cctv.x] == 5) {
                continue;
            }
            map[y][cctv.x] = -1;
        }
    }

    private static void up(int[][] map, Cctv cctv) {
        for (int y = cctv.y - 1; y >= 0; y--) {
            // 벽인 경우
            if (map[y][cctv.x] == 6) {
                break;
            }
            // CCTV인 경우
            if (map[y][cctv.x] == 1 || map[y][cctv.x] == 2 || map[y][cctv.x] == 3 ||
                    map[y][cctv.x] == 4 || map[y][cctv.x] == 5) {
                continue;
            }
            map[y][cctv.x] = -1;
        }
    }

    private static void left(int[][] map, Cctv cctv) {
        for (int x = cctv.x - 1; x >= 0; x--) {
            // 벽인 경우
            if (map[cctv.y][x] == 6) {
                break;
            }
            // CCTV인 경우
            if (map[cctv.y][x] == 1 || map[cctv.y][x] == 2 || map[cctv.y][x] == 3 ||
                    map[cctv.y][x] == 4 || map[cctv.y][x] == 5) {
                continue;
            }
            map[cctv.y][x] = -1;
        }
    }

    private static void right(int[][] map, Cctv cctv) {
        for (int x = cctv.x + 1; x < m; x++) {
            // 벽인 경우
            if (map[cctv.y][x] == 6) {
                break;
            }
            // CCTV인 경우
            if (map[cctv.y][x] == 1 || map[cctv.y][x] == 2 || map[cctv.y][x] == 3 ||
                    map[cctv.y][x] == 4 || map[cctv.y][x] == 5) {
                continue;
            }
            map[cctv.y][x] = -1;
        }
    }

    private static int[][] cloneMap(int[][] origin) {
        int[][] newMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            newMap[i] = origin[i].clone();
        }
        return newMap;
    }

    static class Cctv {
        int y, x;
        int num;

        public Cctv(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }
}
