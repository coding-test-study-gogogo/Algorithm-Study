package week10.almondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 퍼즐 {
    public static HashMap<String,Integer> map = new HashMap<>();
    public static int answer = Integer.MAX_VALUE;
    public static StringBuilder sb = new StringBuilder();
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        for (int i = 1; i < 10; i = i+3) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }

        //처음에 나는 무리하게 해시맵을 쓰려 했고, 모든 경우의 수에ㅔ 대해서 bfs탐색을 하려고 했다. 이러면 메모리 초과 + 시간초과가 난다. 딱 0 을 기점으로 탐색해야 하는 문제
        map.put(sb.toString(),0);
        bfs(sb.toString());
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    public static void bfs(String str) {
        Queue<String> q = new LinkedList<>();
        q.offer(str);
        int count = 0;

        while(!q.isEmpty()) {
            String temp = q.poll();
            int tempZeroIdx = temp.indexOf("0");

            if(temp.equals("123456780")) {
                answer = map.get("123456780");
                return ;
            }

            for (int i = 0; i < 4; i++) {
                // 상하좌우 탐색
                int nr = tempZeroIdx/3 + dx[i];
                int nc = tempZeroIdx%3 + dy[i];
                if(nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;

                int nTempZeroIdx = nr * 3 + nc;
                StringBuilder nSb = new StringBuilder(temp);
                char saveWords = nSb.charAt(nTempZeroIdx);
                nSb.setCharAt(nTempZeroIdx, '0');
                nSb.setCharAt(tempZeroIdx, saveWords);

                if(map.containsKey(nSb.toString()) == false) { //처음으로 들어온 값일경우에만
                    q.offer(nSb.toString());
                    map.put(nSb.toString(), map.get(temp) + 1);
                }
            }
        }


    }
}