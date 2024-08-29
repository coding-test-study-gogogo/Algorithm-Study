package week8.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

class 알고스팟 {
    public static int n, m;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};
    public static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m + 1][n + 1];
        visited = new boolean[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                arr[i][j + 1] = Integer.parseInt(str[j]);
            }
        }

        result = bfs();
        System.out.println(result);
    }

    public static int bfs() {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.offer(new int[]{1, 1, 0}); // x, y, count
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int tempX = cur[0];
            int tempY = cur[1];
            int cnt = cur[2];

            if (tempX == m && tempY == n) {
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int newX = tempX + dx[i];
                int newY = tempY + dy[i];

                if (newX <= 0 || newY <= 0 || newX > m || newY > n) continue;

                if (!visited[newX][newY] && arr[newX][newY] == 0) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, cnt});
                }

                if (!visited[newX][newY] && arr[newX][newY] == 1) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, cnt + 1});
                }
            }
        }
        return 0; // Return -1 if there's no valid path
    }
}

