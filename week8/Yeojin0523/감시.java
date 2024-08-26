package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int[][] tempBoard;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<cctv> cctvs = new ArrayList<>();

        int min = 0;
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0 && board[i][j] != 6) {
                    cctvs.add(new cctv(i, j, board[i][j]));
                }
                if(board[i][j] == 0) min++;
            }
        }

        if (min == 0) {
            System.out.println(min);
            System.exit(0);
        }


        for (int i = 0; i < (1 << (2 * cctvs.size())); i++) {
            int temp = i;
            tempBoard = new int[N][M];
            for (int k = 0; k < N; k++) {
                System.arraycopy(board[k], 0, tempBoard[k], 0, M);
            }

            for (int j = 0; j < cctvs.size(); j++) {
                int kind = cctvs.get(j).kind;
                int x = cctvs.get(j).x;
                int y = cctvs.get(j).y;
                int dir = temp % 4;
                temp /= 4;

                if (kind == 1) {
                    fill(dir,x,y);
                } else if (kind == 2) {
                    fill(dir,x,y);
                    fill(dir + 2,x,y);
                } else if (kind == 3) {
                    fill(dir, x, y);
                    fill(dir + 1, x, y);
                } else if (kind == 4) {
                    fill(dir, x, y);
                    fill(dir + 1, x, y);
                    fill(dir + 2, x, y);
                } else {
                    fill(dir, x, y);
                    fill(dir + 1, x, y);
                    fill(dir + 2, x, y);
                    fill(dir + 3, x, y);
                }
            }
            int count = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tempBoard[j][k] == 0) {
                        count++;
                    }
                }
            }
            min = Math.min(count, min);
            if (min == 0) {
                System.out.println(min);
                System.exit(0);
            }
        }
        System.out.println(min);

    }
    //머릿속에 4방향 한번에? 띠로따로? 가 떠오르겠지만 you can do it!!!e
    // fill 함수 구현은...dir 방향에 따라서 한방향으로 쭉 채워주는 함수.
    //fill 구현시 아무 한 방향으로 구현만 해놓으면, 그냥 쭈루룩 되겠지?!
    
    static void fill(int dir,int x, int y) {
        dir %= 4;
        for (int i = 0; i < 8; i++) {
            x += dx[dir]; //실수
            y += dy[dir];

            if (0 <= x && x < N && 0 <= y && y < M) {
                if (tempBoard[x][y] == 0) { //tempBoard 지 board아님.
                    tempBoard[x][y] = 7;
                } else if (tempBoard[x][y] == 6) {
                    return;
                }
            }
        }
    }

    static class cctv {
        int x;
        int y;
        int kind;

        cctv(int x, int y, int kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }
    }
}