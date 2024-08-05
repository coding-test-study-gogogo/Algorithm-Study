package week4.AlmondBreez3;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        final int INF = 1000000000;
        int[][] dist = new int[V + 1][V + 1];

        // 초기화
        for (int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 1; i <= V; i++) {
            dist[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = c; // 단방향 그래프
        }

        // Floyd-Warshall 알고리즘
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 최소 사이클 찾기
        int minCycle = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j && dist[i][j] != INF && dist[j][i] != INF) {
                    minCycle = Math.min(minCycle, dist[i][j] + dist[j][i]);
                }
            }
        }

        if (minCycle == INF) {
            System.out.println(-1);
        } else {
            System.out.println(minCycle);
        }
    }
}
