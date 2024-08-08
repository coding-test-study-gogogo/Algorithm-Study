package week5.olsohee;

import java.util.*;

public class 베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> songCntMap = new HashMap<>(); // 장르마다 속한 노래 갯수
        Map<String, List<Song>> songMap = new HashMap<>(); // 장르에 따른 노래들

        for (int i = 0; i < genres.length; i++) {
            songCntMap.put(genres[i], songCntMap.getOrDefault(genres[i], 0) + plays[i]);
            List<Song> list = songMap.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Song(i, plays[i]));
            songMap.put(genres[i], list);
        }

        List<Genre> list = new ArrayList<>();
        for (String key : songCntMap.keySet()) {
            list.add(new Genre(key, songCntMap.get(key)));
        }

        Collections.sort(list, (o1, o2) -> o2.playCnt - o1.playCnt);

        List<Integer> answer = new ArrayList<>();
        for (Genre genre : list) {
            List<Song> songs = songMap.get(genre.genre);
            Collections.sort(songs, (o1, o2) -> {
                if (o2.playCnt == o1.playCnt) {
                    return o1.idx - o2.idx;
                }
                return o2.playCnt - o1.playCnt;
            });

            // 2개 수록
            if (songs.size() < 2) {
                answer.add(songs.get(0).idx);
            } else {
                answer.add(songs.get(0).idx);
                answer.add(songs.get(1).idx);
            }
        }

        // 앨범에 들어갈 노래의 고유 번호를 순서대로 반환
        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private class Genre {

        String genre;
        int playCnt;

        public Genre(String genre, int playCnt) {
            this.genre = genre;
            this.playCnt = playCnt;
        }
    }

    private class Song {

        int idx;
        int playCnt;

        public Song(int idx, int playCnt) {
            this.idx = idx;
            this.playCnt = playCnt;
        }
    }
}
