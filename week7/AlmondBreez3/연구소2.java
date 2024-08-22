package week7.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 연구소 {
    public static int N,M;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int min = Integer.MAX_VALUE;
    public static List<int[]> virusArr = new ArrayList<>();
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        visited = new boolean[N][N];
        for (int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j]==2) {
                    virusArr.add(new int[]{i,j});
                }
            }
        }

        // 놓친 점 1-아예 바이러스가 시작조차 못하는 경우
        if (M==0) {
            System.out.println(0);

            return;
        }



        dfs(0,0, new ArrayList<>());
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
    public static void dfs(int idx, int depth,List<int[]> selectedVirus) {
        if (depth == M) {
            bfs(selectedVirus);
            return;
        }
        // 놓친 점 2- 시간 초과 나지 않으려면 기존 조건에 부합하는 것들을 리스트로 받아서 거기서 조합을 생성해야 한다
        for (int i = idx; i < virusArr.size(); i++) {
            selectedVirus.add(virusArr.get(i));
            dfs(i+1,depth+1,selectedVirus);
            selectedVirus.remove(selectedVirus.size()-1);
        }

    }
    public static  void bfs(List<int[]> selectedVirus) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][N];
        int[][] tempArr = new int[N][N];
        // 놓친점 3- 자바 배열의 깊은/ 얕은 복사에 대해 알아야 한다
        for (int i = 0; i < N; i++) {
            System.arraycopy(arr[i], 0, tempArr[i], 0, N);
        }
        for (int[] pos : selectedVirus) {
            int x = pos[0];
            int y = pos[1];
            queue.add(new int[]{x, y, 0});
            visit[x][y] = true;
            tempArr[x][y] = 0;
        }

        int count = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            int cnt =  temp[2];
            count = Math.max(count,cnt);
            for (int i = 0; i < 4; i++) {
                int newX = tempX + dx[i];
                int newY = tempY + dy[i];
                if (newX >= N || newX < 0 || newY >= N || newY < 0) {
                    continue;
                }
                if (!visit[newX][newY]) {
                    if (tempArr[newX][newY] == 0 || tempArr[newX][newY]==2) {
                        visit[newX][newY] = true;
                        tempArr[newX][newY] = cnt+1;
                        queue.add(new int[]{newX,newY,cnt+1});
                    }
                }

            }
        }

        if (check(tempArr,visit)) {
            min = Math.min(min,count);
        }
    }
    public static boolean check(int[][] a,boolean[][] visit) {
        int zeroC = 0;
        for (int i=0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    if (a[i][j]==2 ||a[i][j]==0) {
                        return false;
                    }
                }
            }
        }
        return true;

    }
}