package week10.almondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 이모티콘 {
    public static int N;
    public static int[] arr;
    public static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 2 -> 2
        // 4 -> 4
        // 6 -> 5
        // 18 -> 8

        bfs();
        System.out.println(min);
    }
    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        // 클립보드 화면 횟수
        boolean[][] visited = new boolean[2001][2001];

        q.offer(new int[]{0,1,0});
        visited[1][0] = true;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tempC = temp[0];
            int tempS = temp[1];
            int c = temp[2];
            if(tempS==N) {
                min = Math.min(min,c);
                return;
            }
            // 화면에 출력
            if(tempC > 0 && tempC + tempS <= 2000 && !visited[tempC+tempS][tempC]) {
                q.offer(new int[]{tempC,tempC + tempS,c+1});
                visited[tempC+tempS][tempC] = true;
            }
            if (!visited[tempS][tempS]) {
                // 클립보드로 복사
                visited[tempS][tempS] = true;
                q.offer(new int[]{tempS, tempS, c+1});
            }

            if (tempS-1 >= 0 && !visited[tempS-1][tempC]) {
                // 화면에 있는 것 하나 빼기
                visited[tempS-1][tempC] =true;
                q.offer(new int[]{tempC,tempS-1,c+1});
            }


        }
    }
}
