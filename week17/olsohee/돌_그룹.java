package week17.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 돌_그룹 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] visited = new boolean[1001][1001];
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int[] stones = new int[3];
        for (int i = 0; i < 3; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }
        if (stones[0] == stones[1] && stones[1] == stones[2]) {
            System.out.println(1);
            return;
        }

        // dfs
        visited[stones[0]][stones[1]] = true;
        dfs(stones.clone(), 0, 1);
        dfs(stones.clone(), 0, 2);
        dfs(stones.clone(), 1, 2);

        System.out.println(answer);
    }

    private static void dfs(int[] stones, int idx1, int idx2) {
        // idx1번 돌과 idx2번 돌의 개수 재조정
        if (stones[idx1] < stones[idx2] && stones[idx1] * 2 <= 1000) {
            stones[idx2] -= stones[idx1];
            stones[idx1] *= 2;
        } else if (stones[idx2] < stones[idx1] && stones[idx2] * 2 <= 1000) {
            stones[idx1] -= stones[idx2];
            stones[idx2] *= 2;
        } else {
            return;
        }

        if (stones[0] == stones[1] && stones[1] == stones[2]) {
            answer = 1;
            return;
        }

        // next dfs
        if (!visited[stones[0]][stones[1]]) {
            visited[stones[0]][stones[1]] = true;
            dfs(stones.clone(), 0, 1);
            dfs(stones.clone(), 0, 2);
            dfs(stones.clone(), 1, 2);
        }
    }
}
