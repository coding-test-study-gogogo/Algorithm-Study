package week11.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최단경로 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, cost));
        }

        // 다익스트라(k에서 시작)
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[v + 1];
//        Queue<Node> que = new LinkedList<>();

        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        que.add(new Node(k, 0));
        dist[k] = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (visited[now.end]) continue;
            visited[now.end] = true;

            for (Node node : list.get(now.end)) {
                if (dist[node.end] > dist[now.end] + node.cost) {
                    dist[node.end] = dist[now.end] + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

    }

    private static class Node {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
