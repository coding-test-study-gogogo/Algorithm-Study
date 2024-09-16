package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도:
public class 퍼즐 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Map<String, Integer> map = new HashMap<>();
    static Queue<String> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        String start = "";
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start += Integer.parseInt(st.nextToken());
            }
        }

        // bfs
        map.put(start, 0); // 시작점은 누적 count가 0
        que.add(start);
        while (!que.isEmpty()) {
            String now = que.poll();

            if (now.equals("123456780")) {
                System.out.println(map.get(now));
                return;
            }

            int zeroIdx = now.indexOf("0");

            // 위와 바꾸기
            if (zeroIdx > 2) {
                int moveIdx = zeroIdx - 3;
                move(now, moveIdx);
            }

            // 아래와 바꾸기
            if (zeroIdx < 6) {
                int moveIdx = zeroIdx + 3;
                move(now, moveIdx);
            }

            // 오른쪽과 바꾸기
            if (zeroIdx != 2 && zeroIdx != 5 && zeroIdx != 8) {
                int moveIdx = zeroIdx + 1;
                move(now, moveIdx);
            }

            // 왼쪽과 바꾸기
            if (zeroIdx != 0 && zeroIdx != 3 && zeroIdx != 6) {
                int moveIdx = zeroIdx - 1;
                move(now, moveIdx);
            }
        }
        System.out.println(-1);
    }

    private static void move(String now, int moveIdx) {
        // 움직이기
        String newStr = new String(now);

        char moveNum = now.charAt(moveIdx);
        newStr = newStr.replace(moveNum, 'c');
        newStr = newStr.replace('0', moveNum);
        newStr = newStr.replace('c', '0');

        // 방문 여부 확인 후 방문 안했으면, 방문 처리(map) 후 큐에 넣기
        if (!map.containsKey(newStr)) {
            map.put(newStr, map.get(now) + 1);
            que.add(newStr);
        }
    }
}
