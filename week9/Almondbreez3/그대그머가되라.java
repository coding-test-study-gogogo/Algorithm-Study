package week9.Almondbreez3;

import java.io.*;
import java.util.*;

class Main {
    public static int N, M, a, b;
    public static int min = Integer.MAX_VALUE;
    public static HashMap<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 목표
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 1; i <= a; i++){
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(br.readLine());
            int tempA = Integer.parseInt(st.nextToken());
            int tempB = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(tempA, k -> new ArrayList<>()).add(tempB);
            map.computeIfAbsent(tempB, k -> new ArrayList<>()).add(tempA);
        }

        bfs(N);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min); // -1을 출력하여 경로가 없는 경우를 표시
    }

    public static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[a + 1];

        visited[start] = true;
        q.offer(new int[]{start, 0}); // 노드와 거리

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cur = temp[0];
            int curC = temp[1];

            if (cur == M) {
                min = Math.min(min, curC);
                continue; // 최소 거리 갱신 후 계속 진행
            }

            List<Integer> tempLis = map.getOrDefault(cur, new ArrayList<>());
            for (int next : tempLis) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, curC + 1});
                }
            }
        }
    }
}

