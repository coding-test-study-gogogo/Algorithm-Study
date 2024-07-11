import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int N,M;
    public static int[][] arr;
    public static int[][] melt;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        melt = new int[N][M];
        visited = new boolean[N][M];

        for (int i =0; i<N;i++) {
            st= new StringTokenizer(br.readLine());
            for (int j = 0; j< M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while(true) {
            int count = 0;
            for (int i =0; i<N;i++) {
                for (int j = 0; j< M;j++) {
                    if (arr[i][j]!=0 && !visited[i][j]) {
                        dfs(i,j);
                        count++;
                    }
                }
            }
            if (count == 0) {
                System.out.println(0);
                break;
            }
            if (count >=2) {
                System.out.println(day);
                break;
            }
            melt();
            day++;
            visited = new boolean[N][M];
            melt = new int[N][M];
        }
    }
    public static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int tempX = x+ dx[i];
            int tempY = y +dy[i];
            if (tempX >=0 && tempY>=0 && tempX <N && tempY <M) {
                if (arr[tempX][tempY] ==0) {
                    melt[x][y]++;
                } else {
                    if (!visited[tempX][tempY]) {
                        dfs(tempX,tempY);
                    }
                }
            }
        }

    }
    public static void melt() {
        for (int i =0; i<N;i++) {
            for (int j = 0; j< M;j++) {
                arr[i][j] -= melt[i][j];
                if (arr[i][j] <= 0) {
                    arr[i][j] = 0;
                }
            }
        }
    }
}