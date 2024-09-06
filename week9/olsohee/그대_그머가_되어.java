package week9.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 그대_그머가_되어 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int start, end;
    static boolean[][] map;
    static boolean[] visited;
    static int n;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[n1][n2] = map[n2][n1] = true;
        }

        // bfs
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start, 0));
        visited[start] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.num == end) {
                answer = now.cnt;
                break;
            }

            for (int i = 1; i <= n; i++) {
                if (map[now.num][i] && !visited[i]) {
                    visited[i] = true;
                    que.add(new Node(i, now.cnt + 1));
                }
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static class Node {
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
