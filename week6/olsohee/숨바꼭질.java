package week6.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 숨바꼭질 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] visited = new int[100001];


        //bfs
        Queue<Integer> que = new LinkedList<>();
        que.add(n);
        visited[n] = 1;
        int answer = 0;

        while (!que.isEmpty()) {
            Integer now = que.poll();

            if (now == k) {
                answer = visited[k] - 1;
            }

            if (now + 1 <= 100000 && visited[now + 1] == 0) {
                que.add(now + 1);
                visited[now + 1] = visited[now] + 1;
            }

            if (now - 1 >= 0 && visited[now - 1] == 0) {
                que.add(now - 1);
                visited[now - 1] = visited[now] + 1;
            }

            if (now * 2 <= 100000 && visited[now * 2] == 0) {
                que.add(now * 2);
                visited[now * 2] = visited[now] + 1;
            }
        }

        System.out.println(answer);
    }
}
