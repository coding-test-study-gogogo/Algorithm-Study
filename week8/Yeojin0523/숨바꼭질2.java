package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질2 {

	static int N, K;
	static int time[];
	static int move[] = new int[] { 1, -1, 2 };
	static int cnt;

	static void find() {
		Queue<Integer> que = new ArrayDeque<>();

		que.add(N);

		if (N == K) {
			cnt++;
			return; // 수빈이와 동생의 위치가 같다면 cnt 1, 종료
		}

		// BFS
		while (!que.isEmpty()) {
			int now = que.poll();

			for (int i = 0; i < 3; i++) {
				int next;
				if (i == 2)
					next = now * move[i]; // 순간이동
				else
					next = now + move[i]; // 걷기

				// next가 범위 밖이거나, 이미 방문한 지점인데 기존 소요 시간보다 오래 걸린다면 X
				if (next < 0 || next > 100_000 || (time[next] != 0 && time[next] < time[now] + 1))
					continue;
				time[next] = time[now] + 1; // 소요 시간 저장
				que.add(next);

				if (next == K)
					cnt++; // 동생을 찾은 경우
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = new int[100_001];

		find();
		System.out.println(time[K]); // 최단 소요 시간
		System.out.println(cnt); // 방법 가지수
	}
}