package week16;

import java.util.*;
import java.io.*;

public class 뉴스전하기 {
    public static class Pair implements Comparable<Pair> {
        int x; // 값
        int y; // 인덱스 (필요하지 않지만 문제에서 사용될 수 있으므로 남겨둠)

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            return this.x - o.x; // x 값을 기준으로 오름차순 정렬
        }
    }

    static int[] dp;
    static ArrayList<Integer>[] tree;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 숫자의 개수
        StringTokenizer st = new StringTokenizer(br.readLine()); // 숫자 입력
        dp = new int[N];
        tree = new ArrayList[N];
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq.add(new Pair(num, i)); // 숫자와 인덱스를 페어로 큐에 삽입
            arr[i] = num;
        }

        for (int i = 0; i < N; i++) {
            tree[i].add(arr[i]);
        }

        System.out.println(dfs(0));

        int prevX = Integer.MIN_VALUE; // 이전 값과 비교하기 위해 초기값 설정
        int count = 0; // 고유한 값의 개수를 저장하는 변수

        while (!pq.isEmpty()) {
            Pair cur = pq.poll(); // 우선순위 큐에서 가장 작은 값부터 꺼냄
            if (cur.x != prevX) { // 현재 값이 이전 값과 다를 경우
                count++; // 고유한 값으로 간주하고 카운트 증가
                prevX = cur.x; // 현재 값을 이전 값으로 저장
            }
        }

        System.out.println(count); // 결과 출력
    }

    public static int dfs(int num){
        int cnt = 0, max = 0;
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (Integer next: tree[num]){
            dp[next] = dfs(next);
            q.add(new int[]{next, dp[next]});
        }

        while(!q.isEmpty()){
            int[] temp = q.poll();
            cnt++;
            max = Math.max(max, temp[1] + cnt);
        }

        return max;
    }
}
