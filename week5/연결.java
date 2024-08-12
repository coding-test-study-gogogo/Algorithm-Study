package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class 연결{

    public static int N;
    public static int M;
    public static int answer;
    public static ArrayList<Integer>[] edgeList;
    public static boolean[] visited;
    public static int[] dfsArr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] split = line.split(" ");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        dfsArr = new int[N];
        edgeList = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            edgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            String line1 = br.readLine();
            String[] split1 = line1.split(" ");
            int from = Integer.parseInt(split1[0]);
            int to = Integer.parseInt(split1[1]);
            edgeList[from].add(to);
            edgeList[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start) {
        visited[start] = true;
        for (int to : edgeList[start]) {
            if (!visited[to]) {
                dfs(to);
            }
        }
    }
}