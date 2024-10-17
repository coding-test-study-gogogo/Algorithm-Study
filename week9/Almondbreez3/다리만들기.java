package week9.Almondbreez3;

import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class 다리만들기 {
    public static int N;
    public static int[][] arr;
    public static boolean[][] visited;
    public static boolean[][] visit;
    public static int min = Integer.MAX_VALUE;
    public static int[] dx = {0, 0, 1, -1}; // 동, 서, 남, 북
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 bfs를 이용해서 fill
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    bfs(i, j, num);
                    num++;
                }
            }
        }

        // 브릿지 bfs돌기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0 ) {
                    visit = new boolean[N][N];
                    bridge(i, j);
                }
            }
        }
        System.out.println(min);
    }

    public static void bfs(int x, int y, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        arr[x][y] = n;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            for (int i = 0; i < 4; i++) {
                int newX = tempX + dx[i];
                int newY = tempY + dy[i];
                if (newX >= N || newY >= N || newX < 0 || newY < 0) {
                    continue;
                }
                if (!visited[newX][newY] && arr[newX][newY] == 1) {
                    visited[newX][newY] = true;
                    arr[newX][newY] = n;
                    q.offer(new int[]{newX, newY});
                }
            }
        }
    }

    public static void bridge(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y, 0});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            int count = temp[2];
            for (int i = 0; i < 4; i++) {
                int newX = tempX + dx[i];
                int newY = tempY + dy[i];
                if (newX >= N || newY >= N || newX < 0 || newY < 0) {
                    continue;
                }
                if (visit[newX][newY]) continue;
                if (arr[newX][newY] == arr[x][y]) { // Same island
                    continue;
                }
                if (arr[newX][newY] != 0) { // Different island
                    min = Math.min(min, count);
                    return;
                }
                visit[newX][newY] = true;
                q.offer(new int[]{newX, newY, count + 1});
            }
        }
    }
}
