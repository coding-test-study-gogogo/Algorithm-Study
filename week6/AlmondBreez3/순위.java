package week6.AlmondBreez3;

import java.util.*;

class 순위 {
    private HashMap<Integer, Set<Integer>> map = new HashMap<>();
    private HashMap<Integer, Set<Integer>> lose = new HashMap<>();
    private int N;
    private boolean[] visited;

    public int solution(int n, int[][] results) {
        N = n;
        for (int[] result : results) {
            int a = result[0];
            int b = result[1];

            map.computeIfAbsent(a, k -> new HashSet<>()).add(b);
            lose.computeIfAbsent(b, k -> new HashSet<>()).add(a);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            visited[i] = true;
            bfs(i, map);
            visited = new boolean[N+1];
            visited[i] = true;
            bfs(i, lose);
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (map.getOrDefault(i, new HashSet<>()).size() + lose.getOrDefault(i, new HashSet<>()).size() == N - 1) {
                answer++;
            }
        }
        return answer;
    }

    private void bfs(int i, HashMap<Integer, Set<Integer>> m) {
        Queue<Integer> temp = new LinkedList<>();
        temp.offer(i);

        while(!temp.isEmpty()) {
            int a = temp.poll();
            // null 체크 여부보다는 null일때 collections.emptySet()가져오는 것을 생각하자
            Set<Integer> tem = m.getOrDefault(a, Collections.emptySet());
            for (int b : tem) {
                if (!visited[b]) {
                    visited[b] = true;
                    temp.offer(b);
                    m.computeIfAbsent(i, k -> new HashSet<>()).add(b);
                }
            }
        }
    }
}