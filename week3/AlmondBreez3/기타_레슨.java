package week3.AlmondBreez3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기타_레슨 {
    public static int N, M;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int start = 0; // 시작 지점 (최소 블루레이 크기는 가장 긴 강의의 길이)
        int end = 0;   // 모든 강의 길이의 합 (최대 블루레이 크기)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            start = Math.max(start, arr[i]);
            end += arr[i];
        }

        int res = end;
        while (start <= end) {
            int mid = (start + end) / 2;
            int total = getCount(mid);

            if (total <= M) { // M개의 블루레이로 가능하면 크기를 줄여본다
                res = mid;
                end = mid - 1;
            } else { // M개의 블루레이로 불가능하면 크기를 키운다
                start = mid + 1;
            }
        }

        System.out.println(res);
    }

    public static int getCount(int size) {
        int sum = 0;
        int count = 1; // 블루레이 개수
        for (int i = 0; i < N; i++) {
            if (sum + arr[i] > size) { // 현재 블루레이에 더 못 담으면
                count++;
                sum = arr[i];
            } else {
                sum += arr[i];
            }
        }
        return count;
    }
}

//end의 초기값 설정 (모든 강의 길이의 합으로 설정):
//이유: 블루레이의 크기는 최대 모든 강의 길이의 합까지 가능합니다.
// 이렇게 설정하면 모든 강의를 하나의 블루레이에 담을 수 있는 최대 크기를 나타냅니다.
//예를 들어, 강의가 [1, 2, 3, 4, 5]인 경우, 모든 강의를 하나의 블루레이에 담으려면 블루레이의 크기는 최소한 15이어야 합니다.