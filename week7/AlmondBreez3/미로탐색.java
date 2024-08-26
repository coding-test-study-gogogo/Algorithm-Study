package week7.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 미로탐색 {
    public static int N,M;
    public static int[][] arr;
    public static int min = Integer.MAX_VALUE;
    public static boolean[][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] sts = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(sts[j]);
            }
        }

        bfs(0,0);
        System.out.println(min);
    }
    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new int[]{x,y,1});

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            int cnt = temp[2];
            if (tempX == N-1 && tempY == M-1) {
                min = Math.min(min,cnt);
                return;
            }
            for (int i = 0; i < 4;i++) {
                int newX = tempX + dx[i];
                int newY = tempY + dy[i];
                if (newX >= N || newY >= M || newX<0 || newY <0) {
                    continue;
                }
                if (arr[newX][newY] == 1) {
                    if (!visited[newX][newY]) {
                        visited[newX][newY] = true;
                        q.offer(new int[]{newX,newY,cnt+1});
                    }
                }
            }
        }
    }
}