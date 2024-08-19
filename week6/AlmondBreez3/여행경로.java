package week6.AlmondBreez3;

// BFS가 적합하지 않은 이유
// 모든 경로를 사전순으로 정렬하는 문제
import java.util.*;

class 여행경로 {
    private Map<String, PriorityQueue<String>> arr = new HashMap<>();
    private LinkedList<String> result = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        // Initialize the map with priority queues
        for (String[] ticket : tickets) {
            arr.putIfAbsent(ticket[0], new PriorityQueue<>());
            arr.get(ticket[0]).offer(ticket[1]);
        }

        // Perform DFS starting from "ICN"
        dfs("ICN");

        // Convert result list to array and return
        return result.toArray(new String[0]);
    }

    private void dfs(String departure) {
        PriorityQueue<String> arrivals = arr.get(departure);

        // Process all destinations from the current departure point
        while (arrivals != null && !arrivals.isEmpty()) {
            String next = arrivals.poll();
            dfs(next);
        }

        // Add the current departure point to the result list
        result.addFirst(departure);
    }
}
