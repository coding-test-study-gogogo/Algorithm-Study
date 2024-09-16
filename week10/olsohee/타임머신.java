package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 타임머신 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static List<List<Node>> list = new ArrayList<>();
    static long[] dist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, cost));
        }

        dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        StringBuilder sb = new StringBuilder();
        if (bellmanFord()) {
            sb.append("-1");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    sb.append("-1").append('\n');
                } else {
                    sb.append(dist[i]).append('\n');
                }
            }
        }

        System.out.println(sb);
    }

    public static boolean bellmanFord() {
        dist[1] = 0;
        boolean update = false;

        for (int i = 1; i < n; i++) {
            update = false;

            for (int j = 1; j <= n; j++) {
                for (Node now : list.get(j)) {
                    if (dist[j] == Integer.MAX_VALUE) {
                        break;
                    }
                    if (dist[now.end] > dist[j] + now.cost) {
                        dist[now.end] = dist[j] + now.cost;
                        update = true;
                    }
                }
            }

            if (!update) {
                break;
            }
        }

        if (update) {
            for (int i = 1; i <= n; i++) {
                for (Node now : list.get(i)) {
                    if (dist[i] == Integer.MAX_VALUE) {
                        break;
                    }
                    if (dist[now.end] > dist[i] + now.cost) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Node {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
