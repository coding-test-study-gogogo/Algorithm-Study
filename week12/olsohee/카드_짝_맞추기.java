package week12.olsohee;

import java.util.*;

class 카드_짝_맞추기 {

    int[][] board;
    Map<Integer, List<Node>> cardMap = new HashMap<>();
    List<Integer> cardList = new ArrayList<>();
    List<Node[]> combinations = new ArrayList<>();
    int answer = Integer.MAX_VALUE;
    int y, x; // 현재 위치
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public int solution(int[][] board, int r, int c) {
        this.board = board;

        // 1. Map 채우기 (key: 카드 번호, value: 해당 카드가 위치한 두 개의 노드)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) continue;
                int card = board[i][j];
                if (cardMap.containsKey(card)) {
                    cardMap.get(card).add(new Node(i, j));
                } else {
                    cardMap.put(card, new ArrayList<>());
                    cardMap.get(card).add(new Node(i, j));
                }
            }
        }
        // 2. 카드 뽑는 순서 생성
        for (Integer card : cardMap.keySet()) {
            cardList.add(card);
        }
        dfs(new boolean[cardList.size()], 0, new Node[cardList.size() * 2]);

        // 3. 각 조합별 계산
        for (Node[] combination : combinations) {
            int[][] copyBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                copyBoard[i] = board[i].clone();
            }

            y = r;
            x = c;
            int cnt = 0;
            for (Node node : combination) {
                cnt+= bfs(copyBoard, node.y, node.x);
            }
            cnt += combination.length; // 엔터 누른 횟수 더하기
            answer = Math.min(answer, cnt);
        }

        return answer;
    }

    private int bfs(int[][] board, int destinationY, int destinationX) {
        // 현재 위치에서 목표 위치로 가기
        boolean[][] visited = new boolean[4][4];
        Queue<MoveNode> que = new LinkedList<>();;

        visited[y][x] = true;
        que.add(new MoveNode(y, x, 0));

        while (!que.isEmpty()) {
            MoveNode now = que.poll();

            // 도착
            if (now.y == destinationY && now.x == destinationX) {
                y = destinationY;
                x = destinationX;
                board[y][x] = 0;
                return now.cnt;
            }

            // 계속 이동 - 한 칸 이동
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                que.add(new MoveNode(ny, nx, now.cnt + 1));
            }

            // 계속 이동 - 컨트롤 이동
            for (int i = 0; i < 4; i++) {
                int ny = now.y;
                int nx = now.x;

                while (true) {
                    if (i == 0 && ny == 0) break;
                    if (i == 1 && ny == 3) break;
                    if (i == 2 && nx == 0) break;
                    if (i == 3 && nx == 3) break;

                    if (board[ny][nx] != 0 && !(ny == now.y && nx == now.x)) {
                        break;
                    }

                    ny += dy[i];
                    nx += dx[i];
                }

                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    que.add(new MoveNode(ny, nx, now.cnt + 1));
                }
            }
        }

        return 0;
    }

    private void dfs(boolean[] visited, int depth, Node[] result) {
        if (depth == cardList.size()) {
            Node[] copyResult = new Node[result.length];
            for (int i = 0; i < result.length; i++) {
                copyResult[i] = new Node(result[i].y, result[i].x);
            }
            combinations.add(copyResult);
            return;
        }

        for (int i = 0; i < cardList.size(); i++) {
            int cardNum = cardList.get(i);
            if (!visited[i]) {

                visited[i] = true;

                result[depth * 2] = cardMap.get(cardNum).get(0);
                result[depth * 2 + 1] = cardMap.get(cardNum).get(1);
                dfs(visited, depth + 1, result);

                result[depth * 2] = cardMap.get(cardNum).get(1);
                result[depth * 2 + 1] = cardMap.get(cardNum).get(0);
                dfs(visited, depth + 1, result);

                visited[i] = false;
            }
        }
    }

    private class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private class MoveNode {
        int y, x, cnt;

        public MoveNode(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
