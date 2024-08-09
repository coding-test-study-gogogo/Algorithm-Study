package week5.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 쉬운최단거리 {
    public static int n,m;
    public static int[][] arr;
    public static int totalx,totaly;
    public static int[][] res;
    public static boolean[][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        totalx = 0;
        totaly = 0;
        arr = new int[n][m];
        res = new int[n][m];
        visited =new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st  = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    totalx =i;
                    totaly = j;
                } else if (arr[i][j] ==0) {
                    visited[i][j] = true;
                }
            }
        }

        bfs(totalx,totaly);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <m;j++) {
                if (!visited[i][j]) {
                    System.out.print(-1+" ");
                }
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{x,y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int temX = temp[0];
            int temY = temp[1];
            for (int i =0;i < 4;i++) {
                int newX = temX + dx[i];
                int newY = temY + dy[i];
                if (newX >= n || newY >= m|| newX < 0 || newY < 0) continue;
                if (arr[newX][newY]==1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    res[newX][newY] = res[temX][temY] + 1;
                    q.offer(new int[]{newX,newY});
                }
            }
        }

    }
}