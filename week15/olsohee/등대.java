package week15.olsohee;

import java.util.*;

class 등대 {

    List<Integer>[] route;
    int answer = 0;

    public int solution(int n, int[][] lighthouse) {
        route = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            route[i] = new ArrayList<>();
        }

        // route 반영
        for (int[] light : lighthouse) {
            route[light[0]].add(light[1]);
            route[light[1]].add(light[0]);
        }

        // dfs(1번 노드를 루트 노드로)
        dfs(1, 0);


        return answer;
    }

    /*
    리프 노드 -> 켜지 X (1 반환)
    현재 노드의 자식 노드들의 dfs 합이 0
        = 자식 노드들이 모두 켠 것
        -> 현재 노드는 켜지 X (1 반환)
    현재 노드의 자식 노도들의 dfs 합이 0 초과
        = 자식 노드 중 켜지 않은 노드 존재
        -> 현재 노드 켜기 (0 반환)

    켤 때마다(0 반환할 때마다) answer++
    */
    private int dfs(int now, int pre) {
        // 리프 노드인 경우 켜지 X (1 반환)
        if (route[now].size() == 1 && route[now].get(0) == pre) {
            return 1;
        }

        // 자식 노드 dfs
        int childSum = 0;
        for (int next : route[now]) {
            if (next == pre) continue;
            childSum += dfs(next, now);
        }

        // 자식 노드들의 합이 0 = 모두 켠 것 = 현재 노드 켜지 X
        // 자식 노드들의 합이 1이상 = 하나라도 켜지 않은 것 = 현재 노드 킴 O
        if (childSum == 0) {
            return 1;
        } else {
            answer++;
            return 0;
        }
    }
}
