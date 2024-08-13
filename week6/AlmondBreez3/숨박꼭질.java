package week6.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 숨박꼭질 {
    public static int N,K;
    public static int min = Integer.MAX_VALUE;
    public static class Node {
        int x;
        int cnt;
        boolean[] visited;
        public Node(int x, int cnt,boolean[] visited) {
            this.x = x;
            this.cnt = cnt;
            this.visited = visited;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(min);
    }
    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visit = new boolean[100001];
        queue.offer(new Node(N,0,visit));


        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            int tempX = cur.x;
            int count = cur.cnt;

            if (tempX == K) {
                min = Math.min(min,count);
            }
            if (tempX > 100000 || tempX < 0) {
                continue;
            }
            if (!visit[tempX]) {
                visit[tempX]=true;

                queue.offer(new Node(tempX-1,count+1,visit));
                queue.offer(new Node(tempX+1,count+1,visit));
                queue.offer(new Node(tempX*2,count+1,visit));
            }



        }
    }
}