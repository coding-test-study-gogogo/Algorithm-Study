package week3.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 상어초등학교 {
    public static int N;
    public static int[][] arr;
    public static Deque<List<Integer>> queue = new LinkedList<>();
    public static boolean[][] visited;
    //public static int max = Integer.MIN_VALUE;
    public static int[] dy = {-1,0,0,1};
    public static int[] dx = {0,-1,1,0};
    public static int total = 0;
    public static List<Node> res = new ArrayList<>();
    public static class Node {
        int x; int y; List<Integer> lis;
        public Node(int x, int y, List<Integer> lis) {
            this.x = x;
            this.y = y;
            this.lis = lis;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];

        //순서대로 배열 채우기
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int third = Integer.parseInt(st.nextToken());
            int fourth = Integer.parseInt(st.nextToken());
            List<Integer> tempo = new ArrayList<>();
            tempo.add(temp);
            tempo.add(first);
            tempo.add(second);
            tempo.add(third);
            tempo.add(fourth);
            queue.add(tempo);
        }

        for (List<Integer> lis : queue) {

            List<Integer> tempo = lis;

            // 이 안에서 로직 처리하기
            dfs(tempo.get(0),tempo);
        }

        //인접한 게 몇개 있는지
        for (Node t : res) {
            calculateRate(t.x,t.y,t.lis);
        }

        System.out.println(total);
    }
    public static void dfs(int start, List<Integer> first) {
        int maxEmpty = -1;
        int max = -1;
        int thisX = -1;
        int thisY = -1;
        // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    int[] ret = bfs(i, j, start, first);
                    if (ret[0] > max ||
                            (ret[0] == max && ret[1] > maxEmpty) ||
                            (ret[0] == max && ret[1] == maxEmpty && (i < thisX || (i == thisX && j < thisY)))) {
                        thisX = i;
                        thisY = j;
                        max = ret[0];
                        maxEmpty = ret[1];
                    }
                }
            }
        }

        arr[thisX][thisY] = start;

        res.add(new Node(thisX,thisY,first));
    }
    public static int[] bfs(int x, int y,int start, List<Integer> first) {
        int count = 0;
        int zero = 0;
        for (int i = 0;i < 4; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if (tempX >= N || tempY >= N || tempX < 0 || tempY <0) continue;
            if (first.contains(arr[tempX][tempY])) {
                count++;
            } else if (arr[tempX][tempY] == 0) {
                zero++;
            }
        }
        return new int[]{count,zero};
    }
    public static void calculateRate(int x,int y, List<Integer> first) {
        int count = 0;
        for (int i = 0;i < 4; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if (tempX >= N || tempY >= N || tempX < 0 || tempY <0) continue;
            if (first.contains(arr[tempX][tempY])) {
                count++;
            }
        }
        if (count == 1) {
            total+=1;
        } else if (count == 2) {
            total +=10;
        } else if (count == 3) {
            total += 100;
        } else if (count == 4) {
            total += 1000;
        }
    }
}
