package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뉴스_전하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parents = Integer.parseInt(st.nextToken());
            if (parents == -1) continue;
            list.get(parents).add(i);
        }

        // 0번 노드(루트 노드)부터 dfs 시작
        System.out.println(dfs(0));
    }

    private static int dfs(int now) {

        // 현재 노드에서 각 자식 노드가 그 자식 노드에게 전화하는데 드는 시간 저장
        Queue<Time> que = new PriorityQueue<>((o1, o2) -> o2.time - o1.time); // 오래 걸리는 자식 노드부터 전화해야 함

        // 현재 노드에서 자식 노드로 탐색
        for (int next : list.get(now)) {
            int time = dfs(next);
            que.add(new Time(next, time));
        }

        // 현재 노드에 딸린 모든 자식 노드에게 전화하는데 걸리는 시간
        int max = 0;
        int childCnt = 0;
        while (!que.isEmpty()) {
            Time time = que.poll();
            childCnt++;
            max = Math.max(time.time + childCnt, max);
        }

        return max;
    }

    static class Time {
        int parentsNode;
        int time;

        public Time(int parentsNode, int time) {
            this.parentsNode = parentsNode;
            this.time = time;
        }
    }
}
