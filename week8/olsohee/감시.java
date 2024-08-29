package week8.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 감시 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map;
    static int y;
    static int x;
    static List<Cctv> cctvList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        map = new int[y][x];
        for (int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvList.add(new Cctv(i, j, map[i][j]));
                }
            }
        }

        // 1. cctv 방향 조합
        getCombination(0, map);

        System.out.println(answer);
    }

    private static void getCombination(int depth, int[][] map) {
        if (depth == cctvList.size()) {
            // 2. 각 조합마다 사각지대 구하기
            int cnt = getResult(map);
            answer = Math.min(answer, cnt);
            return;
        }

        int[][] newMap;

        switch (cctvList.get(depth).num) {
            case 1:
                newMap = copyMap(map);
                right(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                down(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                left(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                up(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);
                break;
            case 2:
                newMap = copyMap(map);
                right(newMap, cctvList.get(depth));
                left(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                down(newMap, cctvList.get(depth));
                up(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);
                break;
            case 3:
                newMap = copyMap(map);
                up(newMap, cctvList.get(depth));
                right(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                right(newMap, cctvList.get(depth));
                down(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                down(newMap, cctvList.get(depth));
                left(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                left(newMap, cctvList.get(depth));
                up(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);
                break;
            case 4:
                newMap = copyMap(map);
                left(newMap, cctvList.get(depth));
                up(newMap, cctvList.get(depth));
                right(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                up(newMap, cctvList.get(depth));
                right(newMap, cctvList.get(depth));
                down(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                right(newMap, cctvList.get(depth));
                down(newMap, cctvList.get(depth));
                left(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);

                newMap = copyMap(map);
                down(newMap, cctvList.get(depth));
                left(newMap, cctvList.get(depth));
                up(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);
                break;
            case 5:
                newMap = copyMap(map);
                down(newMap, cctvList.get(depth));
                left(newMap, cctvList.get(depth));
                up(newMap, cctvList.get(depth));
                right(newMap, cctvList.get(depth));
                getCombination(depth + 1, newMap);
        }
    }

    private static int getResult(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                // 감시 대상이거나, 씨씨티비이거나, 벽이면, 패스
                if (map[i][j] == -1 || (map[i][j] >= 1 && map[i][j] <= 5) || map[i][j] == 6) {
                    continue;
                }
                cnt++;
            }
        }
        return cnt;
    }

    private static void right(int[][] map, Cctv cctv) {
        int x = cctv.x;
        int y = cctv.y;
        for (int i = x; i < Main.x; i++) {
            // 벽인 경우, 끝내기
            if (map[y][i] == 6) {
                break;
            }
            // 다른 씨씨티비인 경우, 패스
            if (map[y][i] != 0) {
                continue;
            }
            map[y][i] = -1;
        }
    }
    private static void left(int[][] map, Cctv cctv) {
        int x = cctv.x;
        int y = cctv.y;
        for (int i = x; i >= 0; i--) {
            // 벽인 경우, 끝내기
            if (map[y][i] == 6) {
                break;
            }
            // 다른 씨씨티비인 경우, 패스
            if (map[y][i] != 0) {
                continue;
            }
            map[y][i] = -1;
        }
    }

    private static void up(int[][] map, Cctv cctv) {
        int x = cctv.x;
        int y = cctv.y;
        for (int i = y; i >= 0; i--) {
            // 벽인 경우, 끝내기
            if (map[i][x] == 6) {
                break;
            }
            // 다른 씨씨티비인 경우, 패스
            if (map[i][x] != 0) {
                continue;
            }
            map[i][x] = -1;
        }
    }

    private static void down(int[][] map, Cctv cctv) {
        int x = cctv.x;
        int y = cctv.y;
        for (int i = y; i < Main.y; i++) {
            // 벽인 경우, 끝내기
            if (map[i][x] == 6) {
                break;
            }
            // 다른 씨씨티비인 경우, 패스
            if (map[i][x] != 0) {
                continue;
            }
            map[i][x] = -1;
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    private static class Cctv {

        int y, x;
        int num;

        public Cctv(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }
}
