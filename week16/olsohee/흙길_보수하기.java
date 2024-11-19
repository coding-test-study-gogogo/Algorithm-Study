package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 흙길_보수하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        List<Puddle> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Puddle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list, (o1, o2) -> o1.start - o2.start);

        int last = -1; // 널빤지의 마지막 위치
        int answer = 0;
        for (Puddle puddle : list) {
            // 이전 널빤지로 커버가 다 되는 경우
            if (puddle.end - 1 <= last) {
                continue;
            }

            int start = Math.max(last + 1, puddle.start);
            int len = puddle.end - start; // 물웅덩이 길이

            answer += len / l;
            int remain = len % l;
            if (remain != 0) {
                answer++;
                last = puddle.end - 1 + (l - remain);
            } else {
                last = puddle.end - 1;
            }
        }

        System.out.println(answer);

    }

    static class Puddle {
        int start;
        int end;

        public Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
