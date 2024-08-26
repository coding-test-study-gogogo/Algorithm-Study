package week7.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 안녕 {
    public static int N;
    public static int[][] arr;
    public static int max = 0;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[2][N];
        visited = new boolean[N];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(0,100,0);

        System.out.println(max);
    }
    public static void dfs(int totalJoy, int totalHealth,int depth){
        if (totalHealth <= 0) {
            return;
        }

        for (int i = depth; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                totalJoy = totalJoy+arr[1][i];
                totalHealth = totalHealth - arr[0][i];
                dfs(totalJoy, totalHealth,i+1);
                totalJoy = totalJoy-arr[1][i];
                totalHealth = totalHealth + arr[0][i];
                visited[i] = false;
            }
        }


        max =Math.max(max,totalJoy);


    }
}