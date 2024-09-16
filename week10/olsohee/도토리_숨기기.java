package week10.olsohee;

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
        List<Rule> rules = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rules.add(new Rule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int start = 1;
        int end = n;

        while (start <= end) {
            int mid = (start + end) / 2;

            long cnt = 0; // mid번째 상자까지 들어가는 도토리 개수
            for (Rule rule : rules) {
                if (mid < rule.start) continue;
                if (mid == rule.start) {
                    cnt++;
                    continue;
                }
                cnt += (Math.min(rule.end, mid) - rule.start) / rule.gap + 1;
            }

            if (cnt >= d) {
                end = mid - 1;
            } else if (cnt < d) {
                start = mid + 1;
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
