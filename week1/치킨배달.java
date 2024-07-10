import java.util.*;
import java.io.*;

class Main {
    public static int N, M;
    public static int min = Integer.MAX_VALUE;
    public static int[][] arr;
    public static boolean[] visited;
    public static List<int[]> chicken = new ArrayList<>();
    public static List<int[]> house = new ArrayList<>();
    public static List<int[]> selected = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
                if (arr[i][j] == 1) {
                    house.add(new int[]{i, j});
                }
            }
        }
        visited = new boolean[chicken.size()];
        dfs(0, 0);
        System.out.println(min);
    }

    public static void dfs(int start, int depth) {
        if (depth == M) {
            calculateDistance();
            return;
        }
        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected.add(chicken.get(i));
                dfs(i + 1, depth + 1);
                selected.remove(selected.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void calculateDistance() {
        int totalDistance = 0;
        for (int[] h : house) {
            int minDistance = Integer.MAX_VALUE;
            for (int[] c : selected) {
                int distance = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                minDistance = Math.min(minDistance, distance);
            }
            totalDistance += minDistance;
        }
        min = Math.min(min, totalDistance);
    }
}