package week5;

import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class 연결요소의개수 {
    public static boolean[] visited;
    public static List<List<Integer>> arr; // List 초기화
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];

        // 배열을 n + 1 크기로 초기화
        arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                count++;
                bfs(i);
            }
        }
        System.out.println(count);
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true; // 시작 노드 방문 표시
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : arr.get(node)) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true; // 방문 표시
                }
            }
        }
    }
}
