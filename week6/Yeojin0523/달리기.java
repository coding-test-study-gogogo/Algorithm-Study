package week6;

/*
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 달리기 {

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		// 시작점에서 도착점으로 이동하는 최소시간 -> bfs
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int x1, x2, y1, y2 = 0;

		String array[][] = new String[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = sc.next();
			}
		}

		x1 = sc.nextInt() - 1;
		y1 = sc.nextInt() - 1;
		x2 = sc.nextInt() - 1;
		x2 = sc.nextInt() - 1;
		// 입력받기

		int result = bfs(x1, y1, x2, y2, array, N, M, K);

		System.out.println(result);

		sc.close();
	}

	public static int bfs(int startX, int startY, int endX, int endY, String[][] graph, int N, int M, int K) {
		Queue<int[]> q = new LinkedList<>(); // int[] 타입의 배열을 저장하는 큐를 의미
		boolean[][] visited = new boolean[N][M];

		q.offer(new int[] { startX, startY, 0 }); // 시작점으로부터의 거리
		visited[startX][startY] = true;

		while (!q.isEmpty()) {
			int[] nodeIndex = q.poll();
			int x = nodeIndex[0];
			int y = nodeIndex[1];
			int distance = nodeIndex[2];

			if (x == endX && y == endY) {
				return distance;
			}

			// 인접 노드 선택
			for (int i = 0; i < 4; i++) {
				for (int step = 1; step <= K; step++) {
					int nx = x + dx[i] * step;
					int ny = y + dy[i] * step;

					// 1. 범위 내에 있는지 2. 방문한 적이 있는지 3. 이동가능한 지점인지
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && graph[nx][ny].equals(".")) {
						visited[nx][ny] = true;
						q.offer(new int[] { nx, ny, distance + 1 });
					} else {
						break;
					}
				}
			}

		}
		return -1;
	}
}
*/
import java.util.*;
import java.io.*;
public class 달리기{
    static int N, M, K;
    static char[][] arr;
    static int[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            Arrays.fill(visited[i], Integer.MAX_VALUE);
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken())-1;
        int y = Integer.parseInt(st.nextToken())-1;
        Node start = new Node(x, y);

        x = Integer.parseInt(st.nextToken())-1;
        y = Integer.parseInt(st.nextToken())-1;
        Node end = new Node(x, y);

        BFS(start, end);
        System.out.println(visited[end.x][end.y] == Integer.MAX_VALUE ? -1 : visited[end.x][end.y]);
    }

    public static void BFS(Node start, Node end) {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = 0;

        while(!q.isEmpty()) {
            Node n = q.poll();
            if (n.x == end.x && n.y == end.y) {
                return ;
            }
            for (int i = 0; i < 4; ++i) {
                for (int k = 1; k <= K; ++k) {
                    int nx = n.x + dx[i] * k;
                    int ny = n.y + dy[i] * k;
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == '#') {
                        break;
                    }
                    if (visited[nx][ny] < visited[n.x][n.y] + 1)
                        break;
                    if (arr[nx][ny] == '.' && visited[nx][ny] == Integer.MAX_VALUE) {
                        q.add(new Node(nx, ny));
                        visited[nx][ny] = visited[n.x][n.y]+1;
                    }
                }
            }
        }
    }
}

class Node {
    int x, y;
    Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
}