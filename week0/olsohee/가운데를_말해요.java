package week0.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가운데를_말해요 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        Queue<Integer> minQue = new PriorityQueue<>();
        Queue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());

        // maxQue: 더 작은 수들이 저장됨, minQue: 더 큰 수들이 저장됨
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (maxQue.size() == minQue.size()) {
                maxQue.add(num);
                if (!minQue.isEmpty() && maxQue.peek() > minQue.peek()) {
                    minQue.add(maxQue.poll());
                    maxQue.add(minQue.poll());
                }
            } else {
                minQue.add(num);
                if (maxQue.peek() > minQue.peek()) {
                    minQue.add(maxQue.poll());
                    maxQue.add(minQue.poll());
                }
            }
            sb.append(maxQue.peek()).append('\n');
        }

        System.out.println(sb.toString());
    }
}
