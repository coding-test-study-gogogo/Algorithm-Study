import java.util.*;

// DFS풀이
class Solution {
    public int[] getInfo;
    public int[][] getEdges;
    public int max = 0;
    public int solution(int[] info, int[][] edges) {
        getInfo = info;
        getEdges = edges;
        boolean[] visited = new boolean[info.length];
        dfs(0,visited,0,0);

        int answer = 0;
        return max;
    }
    public void dfs(int idx, boolean[] visited, int sheep, int wolf) {
        visited[idx] = true;
        if (getInfo[idx] == 1) {
            wolf++;
        } else {
            sheep++;
            max = Math.max(sheep,max);
        }

        if (sheep <= wolf) {
            return;
        }

        for (int[] edge: getEdges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] newVisit = new boolean[visited.length];
                for (int i = 0; i < visited.length; i++) {
                    newVisit[i] = visited[i];
                }
                dfs(edge[1],newVisit,sheep,wolf);
            }
        }
    }
}

//BFS 풀이
import java.util.*;
class Solution {
    //현재 위치, 양의 수, 늑대의 수 방문한 노드 저장을 위한 클래스
    public static class Info {
        int node, sheep, wolf;
        HashSet<Integer> visited;

        public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
    }
    private static ArrayList<Integer>[] tree; //트리 정보를 저장할 인접리스트

    // 트리 구축하는 메소드
    private static void buildTree(int[] info, int[][] edges) {
        tree = new ArrayList[info.length];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge: edges){
            tree[edge[0]].add(edge[1]);
        }
    }
    public int solution(int[] info, int[][] edges) {
        buildTree(info,edges);
        int answer = 0;

        // BFS를 위한 큐 생성
        ArrayDeque<Info> q = new ArrayDeque<>();
        q.add(new Info(0,1,0,new HashSet<>()));

        while (!q.isEmpty()) {
            Info now = q.poll();
            //최대 양의 수 업데이트
            answer = Math.max(answer,now.sheep);
            now.visited.addAll(tree[now.node]);

            for (int next: now.visited) {
                //기존 해시셋의 데이터를 복사하고 현재 방문한 정점을 해시셋에서 제거
                HashSet<Integer> set = new HashSet<>(now.visited);
                set.remove(next);

                if (info[next]==1) {
                    if (now.sheep != now.wolf + 1) {
                        q.add(new Info(next,now.sheep,now.wolf+1,set));
                    }
                } else {

                    //양일 경우
                    q.add(new Info(next,now.sheep+1,now.wolf,set));
                }


            }

        }

        return answer;
    }
}