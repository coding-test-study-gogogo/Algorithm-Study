package week2.AlmondBreez3;

import java.util.*;

class 미로_탈출 {
    public char[][] arr;
    public int startX;
    public int startY;
    public int leverX;
    public int leverY;
    public int endX;
    public int endY;
    public int[] dy = {-1,1,0,0};
    public int[] dx = {0,0,-1,1};
    public int N,M;
    public boolean[][] visited;
    public int min = Integer.MAX_VALUE;
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        arr = new char[N][M];
        visited=  new boolean[N][M];
        for (int i = 0; i < maps.length;i++) {
            String str = maps[i];
            for (int j = 0; j <str.length(); j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
                if (arr[i][j] == 'L') {
                    leverX = i;
                    leverY = j;
                }
                if (arr[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }

        min = bfs1(startX,startY,false,0);
        if (min ==-1) {
            return -1;
        }
        visited = new boolean[N][M];
        int res = bfs(leverX,leverY,min);



        return res;
    }
    // 레버에 대한 최소 경로 구한ㄴ bfs
    public int bfs1(int x, int y,boolean visit, int cnt) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,cnt});
        visited[x][y] = true;
        int cc = 0;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int X = temp[0];
            int Y = temp[1];
            int c = temp[2];
            for (int i = 0; i < 4; i++) {
                int tempX = X +dx[i];
                int tempY = Y + dy[i];
                if (tempX >= 0&& tempY >=0 && tempX < N && tempY < M) {

                    if (arr[tempX][tempY] == 'L') {
                        c += 1;
                        visit= true;
                        return c;
                    }
                    if (arr[tempX][tempY] != 'X'&& !visited[tempX][tempY]) {
                        q.add(new int[]{tempX,tempY,c+1});
                        visited[tempX][tempY] = true;
                    }


                }
            }
        }


        return -1;
    }
    public int bfs(int x, int y, int cnt) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,cnt});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int X = temp[0];
            int Y = temp[1];
            int c = temp[2];
            for (int i = 0; i < 4; i++) {
                int tempX = X +dx[i];
                int tempY = Y + dy[i];
                if (tempX >= 0&& tempY >=0 && tempX < N && tempY < M) {
                    if (arr[tempX][tempY] == 'E') {
                        c += 1;
                        cnt = c;
                        return cnt;
                    }
                    if (!visited[tempX][tempY]) {
                        visited[tempX][tempY] =true;
                        if (arr[tempX][tempY] != 'X') {
                            System.out.println(tempX+" " + tempY+" "+arr[tempX][tempY]);
                            q.add(new int[]{tempX,tempY,c+1});
                        }
                    }

                }
            }
        }

        return -1;

    }
}
