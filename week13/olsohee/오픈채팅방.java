package week13.olsohee;

import java.util.*;

class 오픈채팅방 {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>(); // 유저 아이디, 닉네임
        Queue<String[]> que = new LinkedList<>();

        for (String str : record) {
            String[] arr = str.split(" ");
            String command = arr[0];
            String userId = arr[1];

            if (command.equals("Enter")) {
                String nickname = arr[2];
                que.add(new String[]{"Enter", userId});
                map.put(userId, nickname);
            }

            if (command.equals("Leave")) {
                que.add(new String[]{"Leave", userId});
            }

            if (command.equals("Change")) {
                String nickname = arr[2];
                map.put(userId, nickname);
            }
        }

        String[] answer = new String[que.size()];
        int idx = 0;
        while (!que.isEmpty()) {
            String[] str = que.poll();
            if (str[0].equals("Enter")) {
                answer[idx] = map.get(str[1]) + "님이 들어왔습니다.";
            }
            if (str[0].equals("Leave")) {
                answer[idx] = map.get(str[1]) + "님이 나갔습니다.";
            }
            idx++;
        }

        return answer;
    }
}
