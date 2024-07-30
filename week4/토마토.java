package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class 토마토 {
    public static int N,M,H;
    public static int[][][] arr;
    public static boolean[][][] visited;
    public static int[] dz ={-1,1,0,0,0,0};
    public static int[] dy = {0,0,-1,1,0,0};
    public static int[] dx  = {0,0,0,0,-1,1};
    public static List<int[]> tomatoes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][N][M];
        visited = new boolean[H][N][M];
        // 초기화
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N ; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k ++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if (arr[i][j][k] == 1) {
                        tomatoes.add(new int[]{i,j,k});
                    }
                }
            }
        }


        int count = bfs();

        System.out.println(count);

    }
    //bfs인듯
    public static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        for (int[] tomatoe : tomatoes) {
            q.offer(new int[]{tomatoe[0], tomatoe[1], tomatoe[2],0});
            visited[tomatoe[0]][tomatoe[1]][tomatoe[2]] = true;
        }

        int days = 0;
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            int z = temp[0];
            int x = temp[1];
            int y = temp[2];
            int c = temp[3];

            days = Math.max(c,days);
            for (int i = 0; i < 6; i++) {
                int newZ = z + dz[i];
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= N || newX < 0 || newY < 0 || newY >= M || newZ >= H || newZ < 0) {
                    continue;
                }
                if (!visited[newZ][newX][newY] && arr[newZ][newX][newY] == 0) {
                    visited[newZ][newX][newY] = true;
                    arr[newZ][newX][newY] = 1;
                    q.offer(new int[]{newZ, newX, newY, c + 1});
                }

            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k ++) {
                    if (arr[i][j][k] == 0) {
                        return -1;
                    }
                }
            }
        }

        return days;

    }
}