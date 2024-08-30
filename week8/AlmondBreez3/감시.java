import java.io.*;
import java.util.*;

public class 감시 {
    public static int N, M;
    public static int min = Integer.MAX_VALUE;
    public static int[][] arr;
    public static List<CCTV> ar = new ArrayList<>();
    public static boolean[][] visited;

    public static class CCTV {
        int num;
        int x;
        int y;

        public CCTV(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        int notZero = 0;

        for (int i = 0; i < N; i++) {
            String[] sts = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(sts[j]);
                if (arr[i][j] != 0 && arr[i][j] != 6) {
                    ar.add(new CCTV(arr[i][j], i, j));
                }
            }
        }

        dfs(0, ar, arr);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void dfs(int depth, List<CCTV> s, int[][] pass) {
        if (depth == s.size()) {
            min = Math.min(min, getZeroCount(pass));
            return;
        }
        int[][] newPass;

        CCTV temp = s.get(depth);
        int n = temp.num;
        int tempX = temp.x;
        int tempY = temp.y;

        if (n == 1) {
            newPass = copyMap(pass);
            countLeft(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countRight(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countUp(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countDown(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

        } else if (n == 2) {
            // 반대 방향
            newPass = copyMap(pass);
            countLeft(newPass, tempX, tempY);
            countRight(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countUp(newPass, tempX, tempY);
            countDown(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

        } else if (n == 3) {
            // 직각
            newPass = copyMap(pass);
            countUp(newPass, tempX, tempY);
            countRight(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countUp(newPass, tempX, tempY);
            countLeft(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countDown(newPass, tempX, tempY);
            countRight(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countDown(newPass, tempX, tempY);
            countLeft(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

        } else if (n == 4) {
            // 세 방향
            newPass = copyMap(pass);
            countUp(newPass, tempX, tempY);
            countLeft(newPass, tempX, tempY);
            countRight(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countDown(newPass, tempX, tempY);
            countLeft(newPass, tempX, tempY);
            countRight(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countUp(newPass, tempX, tempY);
            countRight(newPass, tempX, tempY);
            countDown(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

            newPass = copyMap(pass);
            countUp(newPass, tempX, tempY);
            countLeft(newPass, tempX, tempY);
            countDown(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);

        } else if (n == 5) {
            // 모든 방향
            newPass = copyMap(pass);
            countLeft(newPass, tempX, tempY);
            countRight(newPass, tempX, tempY);
            countUp(newPass, tempX, tempY);
            countDown(newPass, tempX, tempY);
            dfs(depth + 1, s, newPass);
        }
    }

    public static int getZeroCount(int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int[][] copyMap(int[][] ori) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < ori.length; i++) {
            for (int j = 0; j < ori[0].length; j++) {
                newMap[i][j] = ori[i][j];
            }
        }
        return newMap;
    }

    public static void countUp(int[][] map, int x, int y) {
        for (int i = x - 1; i >= 0; i--) {
            if (map[i][y] == 6) return;
            if (map[i][y] != 0) continue;
            map[i][y] = -1;
        }
    }

    public static void countDown(int[][] map, int x, int y) {
        for (int i = x + 1; i < N; i++) {
            if (map[i][y] == 6) return;
            if (map[i][y] != 0) continue;
            map[i][y] = -1;
        }
    }

    public static void countLeft(int[][] map, int x, int y) {
        for (int i = y - 1; i >= 0; i--) {
            if (map[x][i] == 6) return;
            if (map[x][i] != 0) continue;
            map[x][i] = -1;
        }
    }

    public static void countRight(int[][] map, int x, int y) {
        for (int i = y + 1; i < M; i++) {
            if (map[x][i] == 6) return;
            if (map[x][i] != 0) continue;
            map[x][i] = -1;
        }
    }
}
