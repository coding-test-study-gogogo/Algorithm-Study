package week6.AlmondBreez3;

import java.util.*;
import java.io.*;

//꼭 복습
class 구슬찾기 {
    public static int N, M;
    public static HashMap<Integer, Set<Integer>> map = new HashMap<>();
    public static HashMap<Integer, Set<Integer>> map2 = new HashMap<>();
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int totalSize = (N + 1) / 2;  // The size of the majority you want to check against.
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(b, k -> new HashSet<>()).add(a);
            map2.computeIfAbsent(a, k -> new HashSet<>()).add(b);
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            int count = dfs(i, map, new HashSet<>());

            visited = new boolean[N + 1];
            visited[i] = true;
            int secCount = dfs(i, map2, new HashSet<>());

            if (count >= totalSize || secCount >= totalSize) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }

    public static int dfs(int node, HashMap<Integer, Set<Integer>> graph, Set<Integer> visitedNodes) {
        visitedNodes.add(node);
        int count = 0;

        if (graph.containsKey(node)) {
            for (int neighbor : graph.get(node)) {
                if (!visitedNodes.contains(neighbor)) {
                    count += dfs(neighbor, graph, visitedNodes) + 1;
                }
            }
        }
        return count;
    }
}

