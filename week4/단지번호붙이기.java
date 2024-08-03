package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class 단지번호붙이기 {
    public static int N;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static List<Integer> lis = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        visited =new boolean[N][N];
        for (int i = 0; i<N;i++) {
            String str = br.readLine();
            for (int j = 0; j<N;j++) {
                arr[i][j] = str.charAt(j) -'0';
            }
        }

        int count = 0;
        for (int i =0; i < N;i++) {
            for (int j = 0; j < N;j++) {
                if (!visited[i][j] && arr[i][j]==1) {
                    int n = dfs(i,j,1);
                    count++;
                    lis.add(n);
                }
            }
        }

        Collections.sort(lis);
        System.out.println(count);
        for (int a :lis) {
            System.out.println(a);
        }
    }
    public static int dfs(int x, int y,int cnt) {
        visited[x][y] = true;

        for (int i = 0; i < 4;i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];
            if (newx >= N && newy >= N && newx <0 && newy <0) {
                continue;
            }
            if (!visited[x][y]) {
                if (arr[newx][newy]==1) {
                    dfs(newx,newy,cnt+1);
                }
            }
        }
        return cnt;

    }
}
