package week10.almondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 타임머신 {
    public static int N,M;
    public static class Edge{
        int start;
        int end;
        int cost;
        public Edge(int start,int end, int cost) {
            this.start =start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static int[][] arr;
    public static Edge[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        graph = new Edge[M+1];
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <M; i++) {
            st = new StringTokenizer(br.readLine());
            int tempX = Integer.parseInt(st.nextToken());
            int tempY = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            //map.computeIfAbsent(tempX, k->new ArrayList<>()).add(tempY);
            graph[i] = new Edge(tempX,tempY,dis);
        }

        dist[1] = 0;
        for (int i =1 ; i< N;i ++) {
            for (int j = 0; j< M; j++) {
                Edge e = graph[j];
                if (dist[e.start] != Integer.MAX_VALUE && dist[e.end] > dist[e.start] + e.cost) {
                    dist[e.end] = dist[e.start] + e.cost;
                }
            }
        }

        boolean cycle = false;
        for (int i = 0; i < M; i++) {
            Edge e = graph[i];
            if (dist[e.start] != Integer.MAX_VALUE && dist[e.end] > dist[e.start] + e.cost) {
                cycle = true;
            }
        }

        if (cycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i]==Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else{
                    System.out.println(dist[i]);
                }
            }
        }

    }
}