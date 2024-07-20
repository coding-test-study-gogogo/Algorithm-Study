import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int N,M, H;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int startX, startY;
    public static int max = Integer.MIN_VALUE;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static List<int[]> chocomilk = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        visited = new boolean[N][N];
        arr = new int[N][N];

        // 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j =0; j < N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (arr[i][j] == 2) {
                    chocomilk.add(new int[]{i,j});
                }
            }
        }

        dfs(startX,startY,M,0);
        System.out.println(max);
    }
    public static void dfs(int x, int y, int health, int count) {
        visited[x][y] = true;


        for (int[] milk : chocomilk) {
            if (visited[milk[0]][milk[1]]) continue;
            int tempHealth = Math.abs(x-milk[0]) + Math.abs(y-milk[1]);
            if (tempHealth  <= health) {
                dfs(milk[0],milk[1], health - tempHealth + H, count+1);
            }
        }
        if (health >= Math.abs(startX-x) + Math.abs(startY-y)) {
            max = Math.max(max,count);
        }
        visited[x][y] = false;
    }
}