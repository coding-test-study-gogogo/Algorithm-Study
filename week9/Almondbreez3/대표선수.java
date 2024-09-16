package week9.Almondbreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 그룹의 수
        int M = Integer.parseInt(st.nextToken()); // 각 그룹의 점수 개수

        // N x M 크기의 2차원 배열로 각 학생 그룹의 점수를 저장
        int[][] students = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 그룹의 점수를 오름차순으로 정렬
        int[] indexs = new int[N];
        for (int i = 0; i < N; i++) {
            Arrays.sort(students[i]);
            indexs[i] = 0; // 각 그룹의 현재 선택된 점수의 인덱스 초기화
        }

        int min = Integer.MAX_VALUE; // 최소 차이를 저장할 변수

        while(true) {
            int partialMin = students[0][indexs[0]]; // 현재 선택된 점수들 중 최소값
            int partialMax = students[0][indexs[0]]; // 현재 선택된 점수들 중 최대값
            int minIdx = 0; // 최소값을 가진 그룹의 인덱스

            // 모든 그룹을 순회하며 최소값과 최대값 찾기
            for (int i = 1; i < N; i++) {
                if (partialMin > students[i][indexs[i]]){
                    partialMin = students[i][indexs[i]];
                    minIdx = i;
                }
                if (partialMax < students[i][indexs[i]]){
                    partialMax = students[i][indexs[i]];
                }
            }

            // 현재 최대값과 최소값의 차이가 지금까지의 최소 차이보다 작으면 갱신
            if ((partialMax - partialMin) < min) {
                min = (partialMax - partialMin);
            }

            // 최소값을 가진 그룹의 다음 점수로 이동
            // 만약 해당 그룹의 모든 점수를 탐색했다면 루프 종료
            if (++indexs[minIdx] >= M)
                break;
        }

        // 최종적으로 찾은 최소 차이 출력
        System.out.println(min);
    }
}