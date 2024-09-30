package week12.olsohee;

class 전력망을_둘로_나누기 {

    int n;
    int[][] wires;
    boolean[] visited;
    boolean[][] map;
    int answer = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {
        this.n = n;
        this.wires = wires;

        for (int i = 0; i < n - 1; i++) {
            visited = new boolean[n + 1];
            map = new boolean[n + 1][n + 1];

            for (int j = 0; j < n - 1; j++) {
                if (i == j) continue;
                map[wires[j][0]][wires[j][1]] = map[wires[j][1]][wires[j][0]] = true;
            }

            int result = dfs(1);
            int diff = Math.abs(result - (n - result));
            answer = Math.min(answer, diff);
        }

        return answer;
    }

    private int dfs(int start) {

        int sum = 1;
        visited[start] = true;

        for (int i = 1; i <= n; i++) {
            if (map[start][i] && !visited[i]) {
                sum += dfs(i);
            }
        }

        return sum;
    }
}
