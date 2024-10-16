package week13.olsohee;

import java.util.*;

class 등산코스_정하기 {

    int n;
    int[][] paths;
    int[] gates;
    int[] summits;
    List<List<Node>> list = new ArrayList<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        this.paths = paths;
        this.gates = gates;
        this.summits = summits;

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int n1 = path[0];
            int n2 = path[1];
            int intensity = path[2];
            // n1 -> n2
            if (isGate(n1) || isSummit(n2)) {
                list.get(n1).add(new Node(n2, intensity));
            }

            // n2 -> n1
            else if (isGate(n2) || isSummit(n1)) {
                list.get(n2).add(new Node(n1, intensity));
            }

            // 양방향
            else {
                list.get(n1).add(new Node(n2, intensity));
                list.get(n2).add(new Node(n1, intensity));
            }
        }

        // 1. 출입구 -> 산봉우리로 이동 (다익스트라)
        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.intensity - o2.intensity); //???
        boolean[] visited = new boolean[n + 1];
        int[] intensity = new int[n + 1]; // intensity[i] = 출입구에서 i번 노드까지 가는데 걸린 최대 intensity
        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            intensity[gate] = 0;
            que.add(new Node(gate, 0));
        }

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (visited[now.end]) continue;
            visited[now.end] = true;

            // now -> next로 이동, intensity가 더 짧으면 갱신 (intensity가 가장 짧은 경로를 찾는 것임)
            for (Node next : list.get(now.end)) {
                if (intensity[next.end] > Math.max(now.intensity, next.intensity)) {
                    intensity[next.end]  = Math.max(now.intensity, next.intensity);
                    que.add(new Node(next.end, intensity[next.end]));
                }
            }
        }

        // 2. intensity가 가장 작은 산봉우리와 intensity 구하기
        int minIntensity = Integer.MAX_VALUE;
        int answerSummit = 0;
        Arrays.sort(summits);
        for (int summit : summits) {
            if (minIntensity > intensity[summit]) {
                minIntensity = intensity[summit];
                answerSummit = summit;
            }
        }
        return new int[]{answerSummit, minIntensity};
    }

    private boolean isGate(int num) {
        for (int n : gates) {
            if (num == n) return true;
        }
        return false;
    }

    private boolean isSummit(int num) {
        for (int n : summits) {
            if (num == n) return true;
        }
        return false;
    }

    private class Node {
        int end, intensity;

        public Node (int end, int intensity) {
            this.end = end;
            this.intensity = intensity;
        }
    }
}
