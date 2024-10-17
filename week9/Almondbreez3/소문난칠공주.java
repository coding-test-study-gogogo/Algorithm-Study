package week9.Almondbreez3;
import java.util.*;
import java.lang.*;
import java.io.*;

// 첫번째 풀이 때 놓친 것 -> 진짜로 내가 고른 값들이 연결 돼있는지
// 두번째 풀이 때 놓친 것 -> 이중 for문 말고 고정된 25개 값 이용해서 효율적으로 풀기
class Main {
    public static int N;
    public static char[][] arr;
    public static boolean[] visited;
    public static int[] dy={-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static int result;
    public static int[] selected = new int[7];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 5;
        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        dfs(0,0,0);
        System.out.println(result);
    }
    public static void dfs(int x,int depth, int count) {
        if (depth==7) {
            if (count >= 4) {
                visited = new boolean[7];
                bfs();
            }
            return;
        }


        for (int i = x; i < 25; i++) {

            selected[depth] = i;
            if (arr[i/5][i%5]=='S') {
                dfs(i+1,depth+1,count+1);
            } else {
                dfs(i+1,depth+1,count);
            }

        }
    }
    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{selected[0]/5, selected[0]%5});
        visited[0] = true;
        int conn = 1;

        while(!q.isEmpty()) {
            int[] tem = q.poll();
            int r = tem[0];
            int c = tem[1];

            for(int i = 0 ; i < 4 ; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                int ni = nr*5 + nc; //0~24의 다음 index

                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;

                //내가 뽑은 7개의 index가, 서로 연결되어있는지를 검사해주는 로직.
                //selected[0]과 selected[6], 이런식으로 떨어진 숫자끼리 선택되어 연결되어 있더라도
                //아래의 for문을 통해 다 만날 수 있다.
                for(int j = 0 ; j < 7 ; j++) {
                    if(!visited[j] && selected[j] == ni) {
                        q.offer(new int[] {nr, nc});
                        visited[j] = true;
                        conn++;
                        break;
                    }
                }


            }
        }
        if(conn==7) result++;
    }

}