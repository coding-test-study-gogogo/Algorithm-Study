// N과 M을 입력받는다
// 최단 경로로 이동한다 = 맵에서 가장 적은 개수의 칸을 지나는 경로
// 벽은 한개까지 부술 수 있다
// 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다
import java.util.*;
import java.lang.*;
import java.io.*;
class Node {
    int x;
    int y;
    int f;
    int c;
    public Node(int x, int y, int f, int c) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.c = c;
    }
}

// The main method must be in a class named "Main".
class Main {
    public static int N, M;
    public static int[][] arr;
    public static boolean[][][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M][2];

        // 조건 1 : 벽 한번 부술 수 있음

        //배열 입력받기
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) -'0';
            }
        }

        //최단 경로 탐색
        int res = bfs(0,0,1,0);


        System.out.println(res);
    }
    public static int bfs(int x, int y,int cnt,int flg) {
        visited[x][y][flg] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,flg,cnt));

        while (!q.isEmpty()){
            Node temp = q.poll();
            int tempX = temp.x;
            int tempY = temp.y;
            int fl = temp.f;
            int count = temp.c;

            if (tempX == N-1 && tempY == M-1) {
                return count;
            }
            for (int i = 0; i < 4; i++) {
                int newX = tempX + dx[i];
                int newY = tempY + dy[i];
                if (newX < N && newY <M && newX >= 0 && newY >= 0) {
                    if (!visited[newX][newY][fl]) {
                        if(arr[newX][newY] == 0) {
                            q.offer(new Node(newX,newY,fl,count+1));
                            visited[newX][newY][fl] = true;
                        } else if (arr[newX][newY] == 1 && fl == 0)  {
                            q.offer(new Node(newX,newY,1,count+1));
                            visited[newX][newY][1] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
}