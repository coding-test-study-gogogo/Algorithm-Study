package week3.olsohee;

import java.util.*;

class 단어_변환 {

    String target;
    String[] words;
    boolean[] visited;
    int answer;

    public int solution(String begin, String target, String[] words) {
        this.target = target;
        this.words = words;

        visited = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(begin)) {
                visited[i] = true;
            }
        }
        dfs(0, begin);

        return answer;
    }

    private void dfs(int cnt, String now) {

        if (now.equals(target)) {
            answer = cnt;
        }

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(now)) {
                continue;
            }
            if (canConvert(words[i], now) && !visited[i]) {
                visited[i] = true;
                dfs(cnt + 1, words[i]);
                visited[i] = false;
            }
        }
    }

    private boolean canConvert(String s1, String s2) {
        int diff = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }
}
