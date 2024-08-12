package week5.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;
// 다시 풀기
class Main {
    public static int totalCount;
    public static int N,M;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int depth = Integer.MAX_VALUE;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static int[] ndy = {-2,-1,2,1,-2,-1,2,1};
    public static int[] ndx = {-1,-2,-1,-2,1,2,1,2};
    public static List<int[]> lis = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr =new int[M][N];
        visited = new boolean[M][N];
        for (int i =0 ;i<M;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                arr[i][j] =Integer.parseInt(st.nextToken());
                if (arr[i][j]==1) {
                    lis.add(new int[]{i,j});
                }
            }
        }
        depth = bfs(0,0);

        for (int i =0 ;i<M;i++){
            for (int j=0;j<N;j++) {
                if (arr[i][j]==0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(depth-1);
    }
    public static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        int count = 0;

        for (int[] a : lis) {
            int row = a[0];
            int col = a[1];
            visited[row][col]= true;
            q.add(new int[]{row,col,1});
        }

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int temX = temp[0];
            int temY = temp[1];
            int c = temp[2];
            count = Math.max(c,count);
            for (int i = 0; i < 4; i++) {
                int newx = temX + dx[i];
                int newy = temY + dy[i];
                if (newx >= M || newy >= N || newx < 0 || newy < 0) continue;
                if (!visited[newx][newy]&& arr[newx][newy] == 0) {
                    visited[newx][newy]= true;
                    arr[newx][newy]=1;
                    q.offer(new int[]{newx,newy,c+1});
                }
            }
        }

        return count;

    }
}