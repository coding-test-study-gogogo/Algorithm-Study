package week5.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 보물섬 {
    public static int a,b;
    public static int max= Integer.MIN_VALUE;
    public static char[][] arr;
    public static boolean[][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        arr = new char[a][b];
        visited = new boolean[a][b];

        for (int i =0; i <a;i++) {
            String temp = br.readLine();
            char[] tempArr = temp.toCharArray();
            for (int j = 0;j <b;j++) {
                arr[i][j] = tempArr[j];
            }
        }

        for (int i =0; i <a;i++) {
            for (int j = 0;j <b;j++) {
                if (arr[i][j]=='L') {
                    bfs(i,j);
                }
            }
        }

        System.out.println(max);
    }
    public static void bfs(int x, int y) {
        visited = new boolean[a][b];
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new int[]{x,y,0});
        int count = 0;

        while(!q.isEmpty()) {
            int[] tem = q.poll();
            int temX = tem[0];
            int temY = tem[1];
            int c = tem[2];
            count = c;
            for (int i = 0; i < 4; i++) {
                int newx = temX + dx[i];
                int newy = temY + dy[i];
                if (newx >= a || newy >= b || newx < 0 || newy <0) continue;
                if (arr[newx][newy]=='W' || visited[newx][newy]) continue;
                visited[newx][newy]=true;
                q.offer(new int[]{newx,newy,c+1});
            }
        }

        max = Math.max(max,count);
    }
}
