package week7.olsohee;

import java.util.*;

class Solu억억단을_외우자tion {
    public int[] solution(int e, int[] starts) {
        //dp
        Node[] dp = new Node[e + 1];

        for (int i = 0; i <= e; i++) {
            dp[i] = new Node(i, 0);
        }
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                dp[j].cnt++;
            }
        }

        Arrays.sort(dp, (o1, o2) -> {
            if (o2.cnt == o1.cnt) {
                return o1.idx - o2.idx;
            }
            return o2.cnt - o1.cnt;
        });


        int[] answer = new int[starts.length];

        for (int i = 0; i < starts.length; i++) {
            // s ~ e 사이의 값 중 억억단에서 가장 많이 등장한 수(여러 개이면 가장 작은 수)
            int s = starts[i];

            for (int j = 0; j < dp.length; j++) {
                if (dp[j].idx >= s) {
                    answer[i] = dp[j].idx;
                    break;
                }
            }
        }

        return answer;
    }

    private class Node {
        int idx;
        int cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
}
