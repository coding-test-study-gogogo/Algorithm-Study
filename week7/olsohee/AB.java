package week7.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AB {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int a, b;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(a, 1));

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.num > b) continue;
            if (now.num == b) {
                answer = now.cnt;
                break;
            }

            que.add(new Node(now.num * 2, now.cnt + 1));
            que.add(new Node(now.num * 10 + 1, now.cnt + 1));
        }

        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
    }

    private static class Node {
        long num;
        int cnt;

        public Node(long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

