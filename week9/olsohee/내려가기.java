package week9.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 내려가기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int[][] maxDP;
    static int[][] minDP;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n][3];
        maxDP = new int[n][3];
        minDP = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxDP[0][0] = map[0][0];
        maxDP[0][1] = map[0][1];
        maxDP[0][2] = map[0][2];
        minDP[0][0] = map[0][0];
        minDP[0][1] = map[0][1];
        minDP[0][2] = map[0][2];

        for (int i = 1; i < n; i++) {
            maxDP[i][0] = Math.max(maxDP[i - 1][0], maxDP[i - 1][1]) + map[i][0];
            maxDP[i][1] = Math.max(maxDP[i - 1][0], Math.max(maxDP[i - 1][1], maxDP[i - 1][2])) + map[i][1];
            maxDP[i][2] = Math.max(maxDP[i - 1][1], maxDP[i - 1][2]) + map[i][2];

            minDP[i][0] = Math.min(minDP[i - 1][0], minDP[i - 1][1]) + map[i][0];
            minDP[i][1] = Math.min(minDP[i - 1][0], Math.min(minDP[i - 1][1], minDP[i - 1][2])) + map[i][1];
            minDP[i][2] = Math.min(minDP[i - 1][1], minDP[i - 1][2]) + map[i][2];

        }

        int maxAnswer = Math.max(maxDP[n - 1][0], Math.max(maxDP[n - 1][1], maxDP[n - 1][2]));
        int minAnswer = Math.min(minDP[n - 1][0], Math.min(minDP[n - 1][1], minDP[n - 1][2]));

        System.out.println(maxAnswer + " " + minAnswer);
    }
}
