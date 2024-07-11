package week1.olsohee;

import java.util.*;

/*
백트래킹을 통해 가능한 모든 순서로 노드 탐색
그 과정에서 ansnwer 갱신
*/
class 양과_늑대 {

    private int answer = 0;
    private int[] info;
    private int[][] edges;
    private List<List<Integer>> graph = new ArrayList<>();

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;

        dfs(0, 0, 0, new boolean[info.length]);
        return answer;
    }

    private void dfs(int node, int sheep, int wolf, boolean[] visited) {
        visited[node] = true;

        if (info[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return;
        }

        answer = Math.max(answer, sheep);

        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] newVisited = new boolean[info.length];
                for (int i = 0; i < info.length; i++) {
                    newVisited[i] = visited[i];
                }
                dfs(edge[1], sheep, wolf, newVisited);
            }
        }
    }
}
