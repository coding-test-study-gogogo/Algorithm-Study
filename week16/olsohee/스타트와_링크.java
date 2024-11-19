package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트와_링크 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] ability;
    static boolean[] choiced;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
       /*
       두개의 팀으로 나누는 조합 (최대 20C10)
        - A팀에 속하는지 아닌지를 boolean 배열로 표현
        - boolean 배열에 N/2 개가 true이어야 함 (cnt로 표현)

        각 조합마다 능력치 구하기 (20 * 20)

        */

        n = Integer.parseInt(br.readLine());
        ability = new int[n][n];
        choiced = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합 찾기
        func(0, 0, 0);

        System.out.println(answer);
    }

    private static void func(int start, int depth, int choicedCnt) {
        if (choicedCnt == n / 2) {
            // 두개의 팀으로 나눠졌으면, 두 팀의 능력치 차이 구하기
            answer = Math.min(answer, getDiff());
            return;
        }

        for (int i = start; i < n; i++) {
            choiced[i] = true;
            func(i + 1, depth + 1, choicedCnt + 1);
            choiced[i] = false;
        }
    }

    private static int getDiff() {
        int teamA = 0;
        int teamB = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 둘 다 팀A인 경우
                if (choiced[i] && choiced[j]) {
                    teamA += ability[i][j];
                    teamA += ability[j][i];
                }
                // 둘 다 팀B인 경우
                else if (!choiced[i] && !choiced[j]) {
                    teamB += ability[i][j];
                    teamB += ability[j][i];
                }
            }
        }
        return Math.abs(teamA - teamB);
    }
}
