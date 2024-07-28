package week3.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class DFS와BFS {
    public static int N, M, V;
    public static List<List<Integer>> arr = new ArrayList<>();
    public static List<Integer> bfsArr = new ArrayList<>();
    public static List<Integer> dfsArr = new ArrayList<>();

    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];

        for (int i =0; i <=N;i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < M;i++) {
            st = new StringTokenizer(br.readLine());
            int a  = Integer.parseInt(st.nextToken());
            int b  = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(arr.get(i));
        }

        dfs(V);
        for (int a : dfsArr) {
            System.out.print(a+" ");
        }
        System.out.println();
        visited = new boolean[N+1];
        bfs(V);
        for (int a : bfsArr) {
            System.out.print(a+" ");
        }
        System.out.println();
    }
    public static void dfs(int v) {
        visited[v] = true;
        dfsArr.add(v);
        List<Integer> w = arr.get(v);

        for (int i = 0; i < w.size(); i++) {
            int temp = w.get(i);
            if (!visited[temp]) {
                dfs(w.get(i));
            }
        }
    }
    public static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;
        bfsArr.add(v);

        while(!q.isEmpty()) {
            int temp = q.poll();
            List<Integer> tempArr = arr.get(temp);
            for (int t : tempArr) {
                if (!visited[t]) {
                    q.offer(t);
                    visited[t] = true;
                    bfsArr.add(t);
                }
            }



        }
    }
}