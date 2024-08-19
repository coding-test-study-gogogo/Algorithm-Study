package week7.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class N과M_1 {
    public static int N,M;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];


        dfs(1,0,new int[M]);

    }
    public static void dfs(int n,int depth,int[] arr) {
        if (depth == M) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            arr = new int[M];
            depth = 0;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                dfs(i+1, depth + 1,arr);
                visited[i] = false;
            }
        }
    }
}
