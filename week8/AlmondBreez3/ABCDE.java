package week8.AlmondBreez3;

import java.util.*;
import java.io.*;

public class ABCDE {
    public static int N, M;
    public static Map<Integer, Set<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            if (dfs(i, 0, new boolean[N])) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    public static boolean dfs(int current, int depth, boolean[] visited) {
        if (depth == 4) {
            // depth == 4 means we have visited 5 nodes (including start node)
            return true;
        }

        visited[current] = true;
        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, depth + 1, visited)) {
                    return true;
                }
            }
        }
        visited[current] = false;
        return false;
    }
}
