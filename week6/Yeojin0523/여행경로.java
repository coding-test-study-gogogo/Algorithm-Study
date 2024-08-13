package week6;
import java.util.Arrays;
import java.util.Scanner;

public class 여행경로 {

    public static boolean[] check; // 티켓 사용 여부를 추적하는 배열
    public static String[] answer; // 정답을 저장할 배열
    public static boolean found = false; // 정답 경로를 찾았는지 여부

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt(); // 티켓 수를 입력받는다.
        String[][] tickets = new String[n][2];
        
        for(int i = 0; i < n; i++) {
            tickets[i][0] = sc.next(); // 출발지
            tickets[i][1] = sc.next(); // 도착지
        }
        
        sc.close();

        String[] result = solution(tickets);
        System.out.println(Arrays.toString(result)); // 결과 출력
    }

    public static String[] solution(String[][] tickets) {
        check = new boolean[tickets.length]; // 모든 티켓에 대해 사용 여부를 false로 초기화
        answer = new String[tickets.length + 1]; // 전체 경로를 저장할 배열, 티켓 수 + 1 크기로 초기화
        
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1])); // 도착지를 기준으로 티켓 정렬
        
        backTracking(tickets, "ICN", 0); // ICN에서 출발하여 경로 탐색
        
        return answer;
    }

    static void backTracking(String[][] tickets, String current, int count) {
        answer[count] = current; // 현재 공항을 경로에 저장
        
        if(count == tickets.length) { // 모든 티켓을 사용한 경우
            found = true; // 정답 경로를 찾음
            return;
        }

        for(int i = 0; i < tickets.length; i++) {
            if(found) return; // 경로를 이미 찾은 경우 더 이상 탐색하지 않음
            
            if(!check[i] && tickets[i][0].equals(current)) { // 티켓이 사용되지 않았고, 출발지가 현재 공항인 경우
                check[i] = true; // 티켓 사용
                backTracking(tickets, tickets[i][1], count + 1); // 다음 공항으로 이동
                
                if(found) return; // 경로를 찾은 경우 탐색 종료
                check[i] = false; // 백트래킹: 티켓 사용 기록 초기화
            }
        }
    }
}

/*
ICN SFO ATL ICN ATL SFO (O)
ICN ATL ICN SFO ATL (X)
ICN ATL SFO ATL ICN SFO (O)

BFS/DFS 써야할거같은데

이 문제는 dfs! 1. 스택 사용하거나 2. 재귀 사용(보편적)
bfs 1. 큐 이용
*/