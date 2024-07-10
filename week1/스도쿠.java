import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[9][9];
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j =0; j < 9;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        dfs(0,0);
    }
    public static void dfs(int x, int y) {
        //가로 확인 다 했다면
        if (y == 9) {
            dfs(x+1, 0);
            return;
        }

        //세로확인까지 다 했다면
        if (x == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j =0; j < 9;j++) {
                    sb.append(arr[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            System.exit(0);
        }


        //해당 위치의 값이 0이라면
        if (arr[x][y]==0) {
            for (int c = 1; c<10;c++) {
                if (find(x,y,c)) {
                    arr[x][y] = c;
                    dfs(x,y+1);
                }
            }
            arr[x][y] = 0;
            return;
        }

        dfs(x,y+1);
    }
    public static boolean find(int row, int col, int val) {
        // 같은 행에 있는 원소 중에 열 중복 검사
        for (int i = 0; i <9;i++) {
            if (arr[row][i] == val) {
                return false;
            }
        }

        // 같은 열에 있는 원소 중에 행 중복 검사
        for (int i = 0; i <9;i++) {
            if (arr[i][col] == val) {
                return false;
            }
        }

        // val이 속한 3 * 3칸에 중복되는 원소가 있는지 검사
        int start = (row / 3) *3 ;
        int end = (col/3) * 3;
        for (int i = start; i < start+3;i++) {
            for (int j =end; j <end+3; j++) {
                if (arr[i][j] == val) {
                    return false;
                }
            }
        }

        return true;

    }
}