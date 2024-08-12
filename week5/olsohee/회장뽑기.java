package week5.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 회장뽑기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            if (num1 == -1 && num2 == -1) break;
            map[num1][num2] = map[num2][num1] = 1;
        }

        // 각 노드에서 각 노드로의 최단 거리 구하기(플로이드)
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE) {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }

        int[] result = new int[n + 1];
        int totalMin = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= n; j++) {
                max = Math.max(max, map[i][j]);
            }
            result[i] = max;
            totalMin = Math.min(totalMin, result[i]);
        }

        int cnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (result[i] == totalMin) {
                cnt++;
                list.add(i);
            }
        }

        System.out.println(totalMin + " " + list.size());
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }
}
