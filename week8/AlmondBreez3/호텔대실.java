package week8.AlmondBreez3;

import java.util.*;

class Solution {
    public class Time {
        int start;
        int end;
        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Time> arr = new ArrayList<>();
    public PriorityQueue<Time> pq = new PriorityQueue<>((t1, t2) -> Integer.compare(t1.end, t2.end));

    public int solution(String[][] book_time) {
        for (String[] time : book_time) {
            String[] first = time[0].split(":");
            String[] second = time[1].split(":");

            int firstN = Integer.parseInt(first[0]) * 60 + Integer.parseInt(first[1]);
            int secondN = Integer.parseInt(second[0]) * 60 + Integer.parseInt(second[1]) + 10;
            arr.add(new Time(firstN, secondN));
        }

        Collections.sort(arr, (t1, t2) -> Integer.compare(t1.start, t2.start));
        int answer = 0;

        for (Time a : arr) {
            while (!pq.isEmpty() && pq.peek().end <= a.start) {
                pq.poll();
            }

            pq.offer(a);
            answer = Math.max(answer, pq.size());
        }

        return answer;
    }
}
