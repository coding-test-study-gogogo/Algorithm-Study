package week6.AlmondBreez3;

import java.util.*;

// 아예 손도 못댄 문제 다시 풀기
class 카드짝맞추기 {
    // 카드 번호와 그 카드의 두 좌표를 저장하는 맵
    Map<Integer, int[][]> card;
    int n, result, R, C, order[][], map[][];
    boolean used[];
    int[] dr = { -1, 1, 0, 0 }; // 상하좌우 이동을 위한 방향 벡터
    int[] dc = { 0, 0, -1, 1 };

    // 주어진 보드와 시작 좌표를 통해 최소 비용을 계산하는 메소드
    int solution(int[][] board, int r, int c) {
        card = new HashMap<>();
        n = 0; // 카드 쌍의 수
        R = r; // 시작 좌표의 행
        C = c; // 시작 좌표의 열
        map = board; // 원본 보드
        result = Integer.MAX_VALUE; // 최솟값을 찾기 위해 무한대로 초기화

        // 보드에서 각 카드의 좌표를 찾아 card 맵에 저장
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    // 카드 번호가 처음 발견되면 좌표를 저장
                    if (!card.containsKey(board[i][j])) {
                        card.put(board[i][j], new int[2][]);
                        card.get(board[i][j])[0] = new int[] { i, j };
                        n++;
                    }
                    // 카드 번호가 이미 발견된 경우 두 번째 좌표를 저장
                    else
                        card.get(board[i][j])[1] = new int[] { i, j };
                }
            }

        order = new int[2 * n][]; // 카드 뒤집기 순서를 저장할 배열
        used = new boolean[n + 1]; // 카드 사용 여부를 기록할 배열
        dfs(0); // DFS를 통해 가능한 카드 뒤집기 순서를 찾음

        return result; // 최소 비용 반환
    }

    // 카드 뒤집을 순열을 생성하는 깊이 우선 탐색(DFS) 메소드
    void dfs(int cnt) {
        if (cnt == n) {
            // 모든 카드 뒤집기 순서를 정한 경우, 해당 순서로 비용을 계산
            result = Integer.min(result, findCard());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;

                // 첫 번째 카드 -> 두 번째 카드 순으로 뒤집기
                order[cnt * 2] = card.get(i)[0];
                order[cnt * 2 + 1] = card.get(i)[1];
                dfs(cnt + 1);

                // 두 번째 카드 -> 첫 번째 카드 순으로 뒤집기
                order[cnt * 2] = card.get(i)[1];
                order[cnt * 2 + 1] = card.get(i)[0];
                dfs(cnt + 1);

                used[i] = false;
            }
        }
    }

    // 주어진 순서대로 카드를 뒤집으며 비용을 계산하는 메소드
    int findCard() {
        // 원본 보드를 복사하여 작업
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                copy[i][j] = map[i][j];

        int[] start = { R, C, 0 }; // 시작 좌표 (행, 열, 현재 비용)
        int cost = 0; // 총 비용

        // 카드 뒤집기 순서에 따라 이동하며 비용을 계산
        for (int[] next : order) {
            Queue<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[4][4]; // 방문 여부 체크
            q.offer(start);
            visited[start[0]][start[1]] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int w = cur[2];

                // 목표 좌표에 도달하면 비용을 추가하고, 카드 위치를 0으로 설정
                if (r == next[0] && c == next[1]) {
                    cost += w + 1;
                    copy[next[0]][next[1]] = 0;
                    break;
                }

                // 일반 이동 (상하좌우) 탐색
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nc < 0 || nr == 4 || nc == 4)
                        continue;

                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new int[] { nr, nc, w + 1 });
                    }
                }

                // Ctrl 키를 이용한 이동 (직선으로 이동)
                for (int d = 0; d < 4; d++) {
                    int nr = r;
                    int nc = c;

                    while (true) {
                        // 경계를 벗어나면 이전 위치로 돌아감
                        if (nr + dr[d] < 0 || nc + dc[d] < 0 || nr + dr[d] == 4 || nc + dc[d] == 4) {
                            if (!visited[nr][nc]) {
                                visited[nr][nc] = true;
                                q.offer(new int[] { nr, nc, w + 1 });
                            }
                            break;
                        }
                        nr += dr[d];
                        nc += dc[d];

                        // 카드가 있으면 해당 위치로 이동
                        if (copy[nr][nc] != 0) {
                            if (!visited[nr][nc]) {
                                visited[nr][nc] = true;
                                q.offer(new int[] { nr, nc, w + 1 });
                            }
                            break;
                        }
                    }
                }
            }

            // 현재 목표 위치를 새로운 시작 위치로 설정
            start[0] = next[0];
            start[1] = next[1];
            start[2] = 0;
        }

        return cost; // 계산된 비용 반환
    }
}
