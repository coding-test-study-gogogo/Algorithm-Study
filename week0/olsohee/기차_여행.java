package week0.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 기차_여행 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 도시의 수
    static int m; // 여행 기간

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 경로 입력받기
        int[] routes = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            routes[i] = Integer.parseInt(st.nextToken());
        }

        // 철도 이용 금액 입력받기 (1번 철도 ~ n - 1번 철도)
        int[][] price = new int[n][3];
        for (int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            price[i][0] = Integer.parseInt(st.nextToken()); // 티켓 금액
            price[i][1] = Integer.parseInt(st.nextToken()); // 카드 금액
            price[i][2] = Integer.parseInt(st.nextToken()); // 카드 구매 금액
        }

        // 누적합을 위한 배열 p 생성
        int[] p = new int[n + 1];
        for (int i = 0; i < m - 1; i++) {
            int start = routes[i];
            int end = routes[i + 1];
            if (start < end) {
                p[start]++;
                p[end]--;
            } else {
                p[end]++;
                p[start]--;
            }
        }

        // 1번 ~ n - 1번 철도의 이용 횟수 구하기 (누적합) + 총 비용 구하기
        int totalPrice = 0;
        int psum = 0;
        for (int i = 1; i < n; i++) {
            psum += p[i]; // psum: i번 철도를 이용하는 횟수
            totalPrice += Math.min(price[i][0] * psum, price[i][2] + price[i][1] * psum);
        }

        System.out.println(totalPrice);
    }
}
