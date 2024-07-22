package week2.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 일피벳 {
    public static int R,C;
    public static int[] alpha = new int[27];
    public static char[][] arr;
    public static boolean[][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i <27;i++) {
            alpha[i] = 1;
        }

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        visited[0][0]=true;
        alpha[arr[0][0]-'A'] = 0;
        int res = dfs(0,0,1);

        System.out.println(res);
    }
    public static int dfs(int x,int y,int cnt) {

        for (int i = 0; i < 4; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if (tempX >=0 && tempY >=0 && tempX <R && tempY <C) {
                if (!visited[tempX][tempY] && alpha[arr[tempX][tempY]-'A'] == 1){
                    visited[tempX][tempY] = true;
                    alpha[arr[tempX][tempY]-'A'] = 0;
                    cnt = cnt + 1;
                    max = Math.max(max,dfs(tempX,tempY,cnt));
                    cnt = cnt - 1;
                    alpha[arr[tempX][tempY]-'A'] = 1;
                    visited[tempX][tempY] = false;
                }
            }
        }
        cnt = Math.max(cnt,max);
        return cnt;
    }
}