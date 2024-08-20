package week7.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 도토리_숨기기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        List<Rule> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Rule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 이분탐색 (가장 마지막 도토리가 들어갈 상자)
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = (start + end) / 2;
            // 1 ~ mid 상자까지 들어가는 도토리 수
            long cnt = 0;

            for (Rule rule : list) {
                if (rule.start > mid) continue;
                if (rule.start == mid) {
                    cnt++;
                    continue;
                }
                cnt += (Math.min(rule.end, mid) - rule.start) / rule.gap + 1;
            }

            if (cnt < d) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start);
    }

    private static class Rule {
        int start, end, gap;

        public Rule(int start, int end, int gap) {
            this.start = start;
            this.end = end;
            this.gap = gap;
        }
    }
}
