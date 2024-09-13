package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이모티콘 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int s = Integer.parseInt(br.readLine());

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(1, 0, 0));
        int answer = 0;
        boolean[][] visited = new boolean[1001][1001];
        visited[0][1] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.screen == s) {
                answer = now.cnt;
                break;
            }

            // 1. 복사
            que.add(new Node(now.screen, now.screen, now.cnt + 1));

            // 2. 붙여넣기
            if (now.clipboard != 0) {
                if (now.screen + now.clipboard <= s && !visited[now.clipboard][now.screen + now.clipboard]) {
                    visited[now.clipboard][now.screen + now.clipboard] = true;
                    que.add(new Node(now.screen + now.clipboard, now.clipboard, now.cnt + 1));
                }
            }

            // 3. 화면에 있는 이모티콘 중 하나를 삭제
            if (now.screen > 0) {
                if (now.screen - 1 >= 0 && !visited[now.clipboard][now.screen - 1]) {
                    visited[now.clipboard][now.screen - 1] = true;
                    que.add(new Node(now.screen - 1, now.clipboard, now.cnt + 1));
                }
            }
        }

        System.out.println(answer);
    }

    private static class Node {
        int screen; // 화면에 있는 이모티콘 갯수
        int clipboard; // 클립보드에 있는 이모티콘 갯수
        int cnt;

        public Node(int screen, int clipboard, int cnt) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.cnt = cnt;
        }
    }
}
