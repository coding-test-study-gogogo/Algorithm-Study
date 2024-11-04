package week18.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이모티콘 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int s = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[1001][1001];
        Queue<Node> que = new LinkedList<>();
        visited[1][0] = true;
        que.add(new Node(1, 0, 0));

        int answer = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.screen == s) {
                answer = now.time;
                break;
            }

            // 복사
            if (!visited[now.screen][now.screen]) {
                que.add(new Node(now.screen, now.screen, now.time + 1));
                visited[now.screen][now.screen] = true;
            }

            // 붙여넣기
            if (now.clipboardCnt != 0) {
                if (now.screen + now.clipboardCnt <= s && !visited[now.screen + now.clipboardCnt][now.clipboardCnt]) {
                    visited[now.screen + now.clipboardCnt][now.clipboardCnt] = true;
                    que.add(new Node(now.screen + now.clipboardCnt, now.clipboardCnt, now.time + 1));
                }
            }

            // 삭제
            if (now.screen >= 1) {
                if (!visited[now.screen - 1][now.clipboardCnt]) {
                    visited[now.screen - 1][now.clipboardCnt] = true;
                    que.add(new Node(now.screen - 1, now.clipboardCnt, now.time + 1));

                }
            }
        }
        System.out.println(answer);
    }

    static class Node {
        int screen;
        int clipboardCnt;
        int time;

        public Node(int screen, int clipboardCnt, int time) {
            this.screen = screen;
            this.clipboardCnt = clipboardCnt;
            this.time = time;
        }
    }
}
