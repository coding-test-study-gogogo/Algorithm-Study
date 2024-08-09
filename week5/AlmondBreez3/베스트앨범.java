package week5.AlmondBreez3;

import java.util.*;
class 베스트앨범 {
    public HashMap<String, List<Integer>> map = new HashMap<>();
    public HashMap<String, Integer> play = new HashMap<>();
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        for (int i = 0; i < genres.length; i++) {
            String cur = genres[i];
            if (play.containsKey(cur)) {
                play.put(cur, play.get(cur) + plays[i]);
            } else {
                play.put(cur,plays[i]);
            }
        }

        // 내림차순
        List<String> genresList = new ArrayList<>(play.keySet());
        Collections.sort(genresList,(o1,o2)->play.get(o2).compareTo(play.get(o1)));

        // 장르별
        List<Integer> answerList = new ArrayList<>();
        genresList.forEach(li->{
            HashMap<Integer,Integer> playsMap = new HashMap<>();
            for (int i = 0; i < genres.length;i++) {
                if (li.equals(genres[i])) {
                    playsMap.put(i, plays[i]);
                }
            }

            List<Integer> playsList = new ArrayList<>(playsMap.keySet());
            Collections.sort(playsList, (o1, o2) -> (playsMap.get(o2).compareTo(playsMap.get(o1))));

            answerList.add(playsList.get(0));
            if (playsList.size() != 1) {
                answerList.add(playsList.get(1));
            }
        });
        answer = new int[answerList.size()];

        for (int idx = 0; idx < answerList.size(); idx++) {
            answer[idx] = answerList.get(idx);
        }
        return answer;
    }
}
