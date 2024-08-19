package week6;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class 구슬찾기 {

	public static int N;
	public static int M;
	
	public static List<Integer>[] heavier, lighter;
	public static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 구슬 갯수
		M = sc.nextInt(); // 비교 정보
		
		heavier = new ArrayList[N + 1];
		lighter = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			heavier[i] = new ArrayList<>();
			lighter[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			heavier[b].add(a);
			lighter[a].add(b);
		}
		int answer = 0;
		int half = (N + 1) / 2;
		
		for(int i = 1; i <= N; i++) {
			if(bfs(i, heavier) >= half || bfs(i, lighter) >= half) {
				answer++;
			}
		}
		System.out.println(answer);
		sc.close();
	} 
	public static int bfs(int start, List<Integer>[] graph) {
		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[N + 1];
		queue.add(start);
		visited[start] = true;
		int count = 0;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int next : graph[current]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
					count++;
				}
			}
		}
		return count;
	}
}
