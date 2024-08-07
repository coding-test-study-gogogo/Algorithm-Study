package week5;

// 다시 풀기
import java.util.*;
class 광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = toIntTime(play_time);
        int adTime = toIntTime(adv_time);
        int[] times = new int[360_000];
        for (String log : logs) {
            String[] splitLog = log.split("-");
            int startTime = toIntTime(splitLog[0]);
            int endTime = toIntTime(splitLog[1]);
            for (int i = startTime; i < endTime; i++) {
                times[i]++;
            }
        }

        int end = 0;
        int total = 0;
        for (int i = 0; i < adTime; i++) {
            total += times[i];
        }
        long maxTotalTime = total;
        for (int i = adTime; i < playTime; i++) {
            total += times[i] - times[i-adTime];
            if (total > maxTotalTime) {
                maxTotalTime = total;
                end = i -adTime + 1;
            }
        }

        return toStrTime(end);
    }
    int toIntTime(String strTime) {
        int[] HMS = Arrays.stream(strTime.split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();

        return 3600 * HMS[0] + 60 * HMS[1] + HMS[2];
    }
    String toStrTime(int time) {
        int H = time / 3600;
        int M = (time - 3600 * H) / 60;
        int S = time - 3600 * H - 60 * M;

        return (H < 10 ? "0" : "") + H + ":" +
                (M < 10 ? "0" : "") + M + ":" +
                (S < 10 ? "0" : "") + S;
    }


}