package week11.olsohee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 그대_그머가되어 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[n1][n2] = map[n2][n1] = true;
        }

        boolean[] visited = new boolean[n + 1];

        Queue<Node> que = new LinkedList<>();

        int answer = -1;
        que.add(new Node(start, 0));
        visited[start] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.end == end) {
                answer = now.cost;
                break;
            }

            for (int i = 1; i <= n; i++) {
                if (map[now.end][i] && !visited[i]) {
                    que.add(new Node(i, now.cost + 1));
                    visited[i] = true;
                }
            }
        }

        System.out.println(answer);
    }

    private static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
