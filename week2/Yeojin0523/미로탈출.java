package week2;
import java.util.*;

class 미로탈출 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int 미로탈출(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length(); 
        
        char[][] maze = new char[rows][cols];
        int startX = -1, startY = -1, leverX = -1, leverY = -1, exitX = -1, exitY = -1;
        
        for (int i = 0; i < rows; i++) {
            maze[i] = maps[i].toCharArray(); 
            
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (maze[i][j] == 'L') {
                    leverX = i;
                    leverY = j;
                } else if (maze[i][j] == 'E') {
                    exitX = i;
                    exitY = j;
                }
            }
        }
        
        int distToLever = bfs(maze, startX, startY, leverX, leverY, rows, cols);
        if (distToLever == -1) return -1;
        
        int distToExit = bfs(maze, leverX, leverY, exitX, exitY, rows, cols);
        if (distToExit == -1) return -1;
        
        return distToLever + distToExit;
    }
    
    private int bfs(char[][] maze, int startX, int startY, int targetX, int targetY, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];
            
            if (x == targetX && y == targetY) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && maze[nx][ny] != 'X' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, dist + 1});
                }
            }
        }
        
        return -1; 
    }
}
