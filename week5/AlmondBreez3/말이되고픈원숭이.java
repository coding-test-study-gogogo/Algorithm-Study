package week5.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;
// 다시 풀기
class 말이되고픈원숭이 {
    public static int totalCount;
    public static int N,M;
    public static int[][] arr;
    public static boolean[][][] visited;
    public static int depth = Integer.MAX_VALUE;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static int[] ndy = {-2,-1,2,1,-2,-1,2,1};
    public static int[] ndx = {-1,-2,-1,-2,1,2,1,2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        totalCount = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr =new int[M][N];
        visited = new boolean[M][N][totalCount+1];
        for (int i =0 ;i<M;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                arr[i][j] =Integer.parseInt(st.nextToken());
            }
        }
        depth = bfs(0,0);

        if (depth == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {

            System.out.println(depth);
        }
    }
    public static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y][totalCount] = true;
        q.offer(new int[]{x,y,0,totalCount});

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int temX = temp[0];
            int temY = temp[1];
            if (temX ==M-1&& temY==N-1) {
                return temp[2];
            }
            for (int i = 0; i < 4; i++) {
                int newx = temX + dx[i];
                int newy = temY + dy[i];
                if (newx >= M || newy >= N || newx < 0 || newy < 0) continue;
                if (!visited[newx][newy][temp[3]]&& arr[newx][newy] == 0) {
                    visited[newx][newy][temp[3]] = true;
                    q.offer(new int[]{newx,newy,temp[2]+1,temp[3]});
                }
            }
            if (temp[3] > 0) {
                for (int i = 0; i < 8; i++) {
                    int newx = temX + ndx[i];
                    int newy = temY + ndy[i];
                    if (newx >= M || newy >= N || newx < 0 || newy < 0) continue;
                    if (!visited[newx][newy][temp[3]-1] && arr[newx][newy] == 0) {
                        visited[newx][newy][temp[3]-1] = true;
                        q.offer(new int[]{newx,newy,temp[2]+1,temp[3]-1});
                    }
                }
            }
        }
        return depth;
    }
}