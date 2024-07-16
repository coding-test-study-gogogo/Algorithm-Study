package week2.AlmondBreez3;

public class 사라지는발판 {
    public int N, M;
    public int[] dy = {-1, 1, 0, 0};
    public int[] dx = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]).moveCount;
    }

    private Result dfs(int[][] board, int ax, int ay, int bx, int by) {
        if (!isValid(board, ax, ay)) {
            return new Result(false, 0); // 현재 플레이어가 더 이상 움직일 수 없는 경우 패배
        }

        boolean canWin = false;
        int minMoves = Integer.MAX_VALUE;
        int maxMoves = 0;

        for (int i = 0; i < 4; i++) {
            int nextAx = ax + dx[i];
            int nextAy = ay + dy[i];

            if (isValid(board, nextAx, nextAy)) {
                board[ax][ay] = 0; // 현재 위치의 발판을 제거
                Result result = dfs(board, bx, by, nextAx, nextAy);
                board[ax][ay] = 1; // 발판 복구

                if (!result.canWin) {
                    canWin = true;
                    minMoves = Math.min(minMoves, result.moveCount + 1);
                } else {
                    maxMoves = Math.max(maxMoves, result.moveCount + 1);
                }
            }
        }

        if (canWin) {
            return new Result(true, minMoves);
        } else {
            return new Result(false, maxMoves);
        }
    }

    private boolean isValid(int[][] board, int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && board[x][y] == 1;
    }

    private static class Result {
        boolean canWin;
        int moveCount;

        Result(boolean canWin, int moveCount) {
            this.canWin = canWin;
            this.moveCount = moveCount;
        }
    }
}

//// 2번째 풀이방법
//import java.util.*;
//class Solution {
//    // 백트래킹을 위한 재귀 메서드의 반환값을 저장함
//    private static class Result {
//        boolean win;
//        int step;
//
//        public Result(boolean win, int step) {
//            this.win = win;
//            this.step = step;
//        }
//    }
//    // 게임의 행과 열의 개수를 저장한다
//    private static int ROW,COL;
//
//    private static final int[] DR = {0,1,0,-1};
//    private static final int[] DC = {-1,0,1,0};
//    private static boolean[][] visited;
//    private static int[][] Board;
//
//    private static boolean isValid(int r, int c) {
//        return 0 <= r && r <ROW && 0<=c &&c <COL;
//    }
//
//    private static Result recursive(int[] alpha, int[] beta, int step) {
//        // 현재 플레이어의 위치와 이동 가능한지 여부, 상대 플레이어가 이긴 경우를 저장하는 변수들
//        int[] now = step % 2 == 0 ? alpha : beta;
//        boolean canMove = false;
//        boolean isOpponentWinner = true;
//
//        //이긴 경우 진 경우를 저장하는 리스트
//        ArrayList<Integer> winSteps = new ArrayList<>();
//        ArrayList<Integer> loseSteps = new ArrayList<>();
//
//        //현재 위치에서 이동할 수 있는 모든 방향으로 이동한다
//        for (int i = 0; i <4 ;i++) {
//            int nr = now[0] + DR[i];
//            int nc = now[1] +DC[i];
//
//            if (isValid(nr,nc) && !visited[nr][nc] && Board[nr][nc] == 1) {
//                canMove = true;
//
//                // 두 플레이어의 위치가 같으면 A 플레이어가 이긴 것 true, step+1반환
//                if (alpha[0]==beta[0] && alpha[1] == beta[1]) {
//                    return new Result(true, step+1);
//                }
//
//
//                visited[now[0]][now[1]] = true;
//                Result result = step % 2 == 0 ? recursive(new int[]{nr,nc}, beta,step+1) : recursive(alpha,new int[]{nr,nc}, step+1);
//                visited[now[0]][now[1]] = false;
//
//                //상대 플레이어가 이긴 경우만 true로 유지
//                isOpponentWinner &= result.win;
//
//                //이긴 경우와 지는 경우를 저장
//                if (result.win) {
//                    winSteps.add(result.step);
//                } else {
//                    loseSteps.add(result.step);
//                }
//            }
//        }
//        if (!canMove) {
//            return new Result(false,step);
//        }
//        if (isOpponentWinner)
//            return new Result(false,winSteps.stream().max(Comparator.comparingInt(o->o)).get());
//        return new Result(true, loseSteps.stream().min(Comparator.comparingInt(o->o)).get());
//    }
//    public int solution(int[][] board, int[] aloc, int[] bloc) {
//        Board = board;
//        ROW =board.length;
//        COL = board[0].length;;
//        visited = new boolean[ROW][COL];
//
//        return recursive(aloc,bloc,0).step;
//    }
//}
