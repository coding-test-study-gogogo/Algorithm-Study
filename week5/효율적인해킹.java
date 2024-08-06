package week5;

import java.util.*;
import java.lang.*;
import java.io.*;


// The main method must be in a class named "Main".
class 효율적인해킹 {
    public static List<Integer> res = new ArrayList<>();
    public static List<List<Integer>> arr = new ArrayList<>();
    public static HashMap<Integer, Integer> map = new HashMap<>();
    public static int max = Integer.MIN_VALUE;
    public static boolean[] visited;
    public static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        answer = new int[n+1];

        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            Queue<Integer> q = new LinkedList<>();
            visited[i] = true;
            int count = 1;
            q.offer(i);

            while(!q.isEmpty()) {
                int temp = q.poll();
                for (int x: arr.get(temp)) {
                    if (!visited[x]) {
                        visited[x] = true;
                        q.offer(x);
                        count++;
                    }
                }
            }
            answer[i] = count;
        }

        for (int i : answer){
            max = Math.max(max, i);
        }

        for (int i = 1; i <= n; i++) {
            if (max == answer[i]) {
                res.add(i);
            }
        }


        Collections.sort(res);
        for (int x : res) {
            System.out.print(x+ " ");
        }
    }
}