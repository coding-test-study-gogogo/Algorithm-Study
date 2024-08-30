package week8.olsohee;

import java.util.*;

class 호텔_대실 {
    public int solution(String[][] book_time) {

        List<Time> list = new ArrayList<>();
        for (String[] time : book_time) {
            list.add(new Time(convertTime(time[0]), convertTime(time[1]) + 10));
        }

        Collections.sort(list, (o1, o2) -> {
            return o1.start - o2.start;
        });

        // 각 방의 끝나는 시간을 담는 큐(끝나는 시간이 이른 순으로)
        Queue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < list.size(); i++) {
            if (que.isEmpty()) {
                que.add(list.get(i).end);
                continue;
            }

            if (que.peek() <= list.get(i).start) {
                que.poll();
                que.add(list.get(i).end);
            } else {
                que.add(list.get(i).end);
            }
        }

        return que.size();
    }

    private int convertTime(String str) {
        return Integer.parseInt(str.split(":")[0]) * 60 + Integer.parseInt(str.split(":")[1]);
    }

    private class Time {

        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
