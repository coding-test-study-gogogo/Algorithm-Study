package week8.olsohee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 숨바꼭질2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = Integer.MAX_VALUE;
    static int answerCnt = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Integer> que = new LinkedList<>();
        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;
        que.add(start);

        while (!que.isEmpty()) {
            int now = que.poll();
            if (now == end) {
                if (visited[end] == answer) {
                    answerCnt++;
                }
                if (visited[end] < answer) {
                    answer = visited[end];
                    answerCnt = 1;
                }
                continue;
            }

            if (now + 1 <= 100000) {
                if (visited[now] + 1 <= visited[now + 1]) {
                    visited[now + 1] = visited[now] + 1;
                    que.add(now + 1);
                }
            }
            if (now - 1 >= 0) {
                if (visited[now] + 1 <= visited[now - 1]) {
                    visited[now - 1] = visited[now] + 1;
                    que.add(now - 1);
                }
            }
            if (now * 2 <= 100000) {
                if (visited[now] + 1 <= visited[now * 2]) {
                    visited[now * 2] = visited[now] + 1;
                    que.add(now * 2);
                }
            }
        }

        System.out.println(answer);
        System.out.println(answerCnt);
    }
}
