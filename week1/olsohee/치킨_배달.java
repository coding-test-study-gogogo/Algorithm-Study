package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치킨_배달 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[][] map;
    static List<Node> chickenList = new ArrayList<>();
    static List<Node> homeList = new ArrayList<>();
    static boolean[] choiced;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homeList.add(new Node(i, j));
                }
                if (map[i][j] == 2) {
                    chickenList.add(new Node(i, j));
                }
            }
        }

        // m개의 치킨 집 고르기
        choiced = new boolean[chickenList.size()];
        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int choiceCnt, int startIdx) {

        if (choiceCnt == m) {
            calculate();
            return;
        }

        for (int i = startIdx; i < chickenList.size(); i++) {
            choiced[i] = true;
            dfs(choiceCnt + 1, i + 1);

            choiced[i] = false;
        }
    }

    private static void calculate() {
        int sum = 0; // 해당 m개의 치킨 집의 경우, 도시의 치킨 거리(모든 집의 치킨 거리의 합)

        // 각 집마다 치킨 거리 구하기
        for (Node home : homeList) {
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < chickenList.size(); i++) {
                if (!choiced[i]) {
                    continue;
                }
                Node chicken = chickenList.get(i);
                minDist = Math.min(minDist, getDist(home, chicken));
            }

            sum += minDist;
        }

        answer = Math.min(answer, sum);
    }

    private static int getDist(Node home, Node chicken) {
        return Math.abs(home.y - chicken.y) + Math.abs(home.x - chicken.x);
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
