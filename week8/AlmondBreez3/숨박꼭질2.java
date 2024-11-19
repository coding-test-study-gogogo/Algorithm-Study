package week8.AlmondBreez3;

import java.util.*;
import java.io.*;

class 숨박꼭질2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // BFS를 위한 큐
        Queue<int[]> queue = new LinkedList<>();
        int[] visited = new int[100001]; // 최소 시간을 기록하기 위한 배열

        // 시작 지점 설정: 현재 위치는 N, 이동 횟수는 0
        Arrays.fill(visited, -1); // 방문 배열을 -1로 초기화 (방문하지 않음 표시)
        queue.offer(new int[]{N, 0});
        visited[N] = 0; // 시작 위치 방문 처리 (시간 0)

        int minC = 0; // 최단 시간에 도달하는 경우의 수

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int time = current[1];

            // 목표 도달 시 처리
            if (position == M) {
                if (visited[M] == time) {
                    minC++; // 같은 시간에 도달한 경우의 수 증가
                } else if (visited[M] > time || visited[M] == -1) {
                    visited[M] = time; // 새로운 최소 시간 갱신
                    minC = 1; // 경우의 수 초기화
                }
                continue;
            }

            // 다음 가능한 상태로 이동
            int[] nextPositions = {position + 1, position - 1, position * 2};
            for (int next : nextPositions) {
                // 경계를 넘지 않고, 해당 위치에 처음 도달하거나 더 빠른 시간으로 도달할 때만 큐에 추가
                if (next >= 0 && next <= 100000) {
                    if (visited[next] == -1 || visited[next] >= time + 1) {
                        visited[next] = time + 1;
                        queue.offer(new int[]{next, time + 1});
                    }
                }
            }
        }

        System.out.println(visited[M]); // 최단 시간 출력
        System.out.println(minC); // 최단 시간에 도달하는 방법의 수 출력
    }
}
