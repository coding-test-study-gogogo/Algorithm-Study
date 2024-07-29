package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 상어초등학교 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static List<List<Integer>> likeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i <= n * n; i++) {
            likeList.add(new ArrayList<>());
        }

        List<Integer> order = new ArrayList<>();
        for (int i = 1; i <= n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            order.add(studentNum);
            List<Integer> list = likeList.get(studentNum);
            for (int j = 0; j < 4; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 차례대로 자리 고르기
        for (Integer student : order) {
            Position position = getPosition(student);
            map[position.y][position.x] = student;
        }

        // 만족도 구하기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int likeCnt = 0;

                for (int k = 0; k < 4; k++) {
                    int ny = dy[k] + i;
                    int nx = dx[k] + j;
                    if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                    if (likeList.get(map[i][j]).contains(map[ny][nx])) likeCnt++;
                }

                if (likeCnt == 1) answer++;
                if (likeCnt == 2) answer += 10;
                if (likeCnt == 3) answer += 100;
                if (likeCnt == 4) answer += 1000;
            }
        }

        System.out.println(answer);
    }

    private static Position getPosition(int student) {
        List<Position> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 이미 해당 자리에 다른 학생이 있으면 패스
                if (map[i][j] != 0) continue;
                // map[i][j] 위치를 기준으로
                int likeCnt = 0;
                int emptyCnt = 0;


                for (int k = 0; k < 4; k++) {
                    int ny = dy[k] + i;
                    int nx = dx[k] + j;
                    if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                    if (map[ny][nx] == 0) emptyCnt++;
                    if (map[ny][nx] != 0 && likeList.get(student).contains(map[ny][nx])) likeCnt++;
                }
                list.add(new Position(i, j, likeCnt, emptyCnt));
            }
        }

        Collections.sort(list);
        return list.get(0);
    }

    private static class Position implements Comparable<Position> {

        int y, x;
        int likeCnt;
        int emptyCnt;

        public Position(int y, int x, int likeCnt, int emptyCnt) {
            this.y = y;
            this.x = x;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Position o) {
            if (o.likeCnt == this.likeCnt) {
                if (o.emptyCnt == this.emptyCnt) {
                    // 3. 그 중, 가장 위, 왼쪽 자리 고로기
                    if (this.y == o.y) {
                        return this.x - o.x;
                    }
                    return this.y - o.y;
                }
                return o.emptyCnt - this.emptyCnt; // 2. 그 중, 인접한 빈 칸이 가장 많은 자리 구하기
            }
            return o.likeCnt - this.likeCnt; // 1. 인접한 좋아하는 학생이 가장 많은 자리 구하기
        }
    }
}
