package week3.AlmondBreez3;

import java.util.*;
class 단어_변환 {
    public class Word {
        String word;
        int depth;
        public Word(String word,int depth) {
            this.word = word;
            this.depth = depth;
        }
    }
    public int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }


        boolean[] visited = new boolean[words.length];
        Queue<Word> queue = new LinkedList<>();
        queue.offer(new Word(begin,0));
        int count = 0;
        while(!queue.isEmpty()) {
            Word w = queue.poll();
            for (int i = 0; i < words.length ;i++) {
                if (!visited[i] && isOneChar(w.word, words[i])) {

                    if (target.equals(words[i])) {
                        return w.depth +1;
                    }
                    visited[i] = true;
                    queue.offer(new Word(words[i],w.depth+1));
                }
            }
        }
        return 0;
    }
    public static boolean isOneChar(String word1, String word2) {
        int count = 0;

        for (int i =0; i <word1.length();i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }

        return count ==  1;
    }
}
