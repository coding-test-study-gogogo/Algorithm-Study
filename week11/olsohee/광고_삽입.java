package week11.olsohee;

import java.util.*;

class 광고_삽입 {
    int[] ad = new int[360000]; // ad[i] = i 시간에 보고 있는 시청자 수
    public String solution(String play_time, String adv_time, String[] logs) {


        for (String log : logs) {
            String[] arr = log.split("-");
            int start = timeToInt(arr[0]);
            int end = timeToInt(arr[1]);
            ad[start]++;
            ad[end]--; // 누적합
        }

        // 누적합 반영
        for (int i = 1; i < timeToInt(play_time); i++) {
            ad[i] += ad[i - 1];
        }

        // 슬라이딩 윈도우
        int start = 0;
        int end = timeToInt(adv_time);

        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += ad[i];
        }
        long max = sum;
        int answer = 0;

        while (end <= timeToInt(play_time)) {
            if (max < sum) {
                max = sum;
                answer = start;
            }

            sum -= ad[start++];
            sum += ad[end++];
        }

        // 시작 시간 반환
        return intToTime(answer);
    }

    private int timeToInt(String str) {
        String[] arr = str.split(":");
        int time = 0;
        time += Integer.parseInt(arr[0]) * 3600;
        time += Integer.parseInt(arr[1]) * 60;
        time += Integer.parseInt(arr[2]);
        return time;
    }

    private String intToTime(int num) {
        String answer = "";

        int h = num / 3600;
        if (h < 10) answer += "0";
        answer += h + ":";

        int m = num % 3600 / 60;
        if (m < 10) answer += "0";
        answer += m + ":";

        int s = num % 3600 % 60;
        if (s < 10) answer += "0";
        answer += s;

        return answer;
    }
}

