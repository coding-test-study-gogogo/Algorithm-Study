// 최적해를 구해야하니까 탐욕법 써야 한다.
package week6;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

class 단속카메라 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<int[]> routesList = new ArrayList<>();

        while (sc.hasNextInt()) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            routesList.add(new int[]{start, end});
        }

        int[][] routes = new int[routesList.size()][2];
        for (int i = 0; i < routesList.size(); i++) {
            routes[i] = routesList.get(i);
        }
        
        // Solution 인스턴스를 생성하고 solution 메서드를 호출
        단속카메라 sol = new 단속카메라();
        int answer = sol.solution(routes); // 전체 routes 배열을 전달
        
        System.out.println(answer);
        sc.close();
    }

    public int solution(int[][] routes) {
        int answer = 0;

        // 구간을 끝나는 지점 기준으로 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int lastCamera = Integer.MIN_VALUE;

        for (int[] route : routes) {
            if (lastCamera < route[0]) {
                answer++;
                lastCamera = route[1];
            }
        }

        return answer;
    }
}
