package week6.olsohee;

import java.util.*;

class 여행경로 {

    String[][] tickets;
    List<String> answerList = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", 0);
        Collections.sort(answerList);
        String[] answer = answerList.get(0).split(",");
        return answer;
    }

    private void dfs(String start, String route, int cnt) {

        // 티켓을 모두 사용한 경우
        if (cnt == tickets.length) {
            answerList.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route + "," + tickets[i][1], cnt + 1);
                visited[i] = false;
            }
        }
    }
}
