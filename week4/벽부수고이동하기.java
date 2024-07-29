package week4;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 벽부수고이동하기 {
    public static int N,M;
    public static int[][] arr;
    public static boolean[][][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static class Node {
        int x;
        int y;
        int c;
        int f;
        public Node(int x, int y, int c, int f) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.f = f;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N;i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int res = dfs(0,0,1,0);

        System.out.println(res);
    }
    public static int dfs(int x, int y, int cnt, int flg) {
        Queue<Node> q = new LinkedList<>();
        visited[x][y][flg] = true;
        q.offer(new Node(x,y,cnt,flg));

        while(!q.isEmpty()) {
            Node temp = q.poll();
            int tempX = temp.x;
            int tempY = temp.y;
            int c = temp.c;
            int fl = temp.f;
            if (tempX == N-1 && tempY == M-1) {
                return c;
            }
            for (int i = 0; i < 4; i++) {
                int newx = tempX + dx[i];
                int newy = tempY + dy[i];
                if (newx >= N || newy >= M || newx < 0 || newy <0) {
                    continue;
                }
                if (!visited[newx][newy][fl]) {
                    if (arr[newx][newy] == 0) {
                        visited[newx][newy][fl] = true;
                        q.offer(new Node(newx,newy,c+1,fl));
                    } else if (arr[newx][newy] == 1) {
                        if (fl == 0) {

                            visited[newx][newy][fl] = true;
                            q.offer(new Node(newx,newy,c+1,1));

                        }
                    }
                }
            }
        }
        return -1;
    }
}