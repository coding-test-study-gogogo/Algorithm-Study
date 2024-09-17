package week11.almondBreez3;

import java.util.*;
import java.io.*;

public class 데스노트 {
    static int N, M;
    static int[] nameLengths;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 이름의 수
        M = Integer.parseInt(st.nextToken());  // 한 줄의 최대 너비

        nameLengths = new int[N+1];  // 이름의 길이 배열
        dp = new int[N+1];  // DP 배열 초기화

        // 이름 길이 입력
        for (int i = 1; i <= N; i++) {
            nameLengths[i] = Integer.parseInt(br.readLine());
        }

        // DP 배열을 큰 값으로 초기화
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;  // 첫 번째 이름 이전까지 남은 공간은 0으로 설정

        // DP 계산
        for (int i = 1; i <= N; i++) {
            int lineLength = 0;  // 현재 줄의 이름 길이 합계

            for (int j = i; j <= N; j++) {
                // j번째 이름까지 한 줄에 포함하려는 경우
                lineLength += nameLengths[j];
                if (j > i) {
                    lineLength++;  // 이름 사이의 공백
                }

                // 줄의 너비를 넘지 않는 경우에만 계산
                if (lineLength <= M) {
                    int remainingSpace = M - lineLength;  // 남은 공간 계산
                    if (j == N) {  // 마지막 줄은 제곱합 계산에서 제외
                        dp[j] = Math.min(dp[j], dp[i-1]);
                    } else {
                        dp[j] = Math.min(dp[j], dp[i-1] + remainingSpace * remainingSpace);
                    }
                } else {
                    break;  // 줄의 너비를 초과하면 더 이상 확인하지 않음
                }
            }
        }

        // 마지막 이름까지의 최소 제곱합 출력
        System.out.println(dp[N]);
    }
}
