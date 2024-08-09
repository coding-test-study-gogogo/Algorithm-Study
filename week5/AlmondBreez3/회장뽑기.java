package week5.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 회장뽑기 {
    public static int people;
    public static HashMap<Integer, List<Integer>> map = new HashMap<>();
    public static boolean[] visited;
    public static List<Integer> candidate = new ArrayList<>();
    public static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        people = Integer.parseInt(br.readLine());
        visited = new boolean[people+1];

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a==-1&&b==-1) {
                break;
            }

            map.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            map.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        for (int i = 1; i <= people;i++) {
            bfs(i);
        }

        int totalSize = candidate.size();
        System.out.println(min+" "+totalSize);
        for (int a: candidate) {
            System.out.print(a+" ");
        }
    }
    public static void bfs(int idx) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visit = new boolean[people+1];
        visit[idx]=true;
        q.offer(new int[]{idx,0});
        int score = 0;
        int count = 0;

        while(!q.isEmpty() && count<people) {
            int[] tempo = q.poll();
            int next = tempo[0];
            int temScore = tempo[1];
            score = Math.max(temScore,score);
            for (int i : map.get(next)) {
                if (!visit[i]) {
                    visit[i] = true;
                    count++;
                    q.offer(new int[]{i,temScore+1});
                }
            }

        }

        if (score == min) {
            candidate.add(idx);
        } else if (min > score) {
            min = score;
            candidate.clear();
            candidate.add(idx);
        }



    }
}