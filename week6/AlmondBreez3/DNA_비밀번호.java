package week6.AlmondBreez3;

import java.io.*;
import java.util.StringTokenizer;

public class DNA_비밀번호 {
    public static int a, b;
    public static char[] str;
    public static int[] dna;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        str = new char[a];
        dna = new int[4];

        str = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            dna[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = b - 1;
        int count = 0;
        int[] temp = new int[4];

        // 초기 윈도우 세팅
        for (int i = start; i <= end; i++) {
            if (str[i] == 'A') {
                temp[0]++;
            } else if (str[i] == 'C') {
                temp[1]++;
            } else if (str[i] == 'G') {
                temp[2]++;
            } else if (str[i] == 'T') {
                temp[3]++;
            }
        }

        // 첫 번째 윈도우 검증
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            if (temp[i] < dna[i]) {
                flag = false;
                break;  // 하나라도 조건을 충족하지 않으면 더 이상 검사할 필요가 없음
            }
        }

        if (flag) {
            count++;
        }

        // 슬라이딩 윈도우
        while (end < a - 1) {  // end가 범위를 벗어나지 않도록 설정
            // 윈도우의 시작 문자 처리
            if (str[start] == 'A') {
                temp[0]--;
            } else if (str[start] == 'C') {
                temp[1]--;
            } else if (str[start] == 'G') {
                temp[2]--;
            } else if (str[start] == 'T') {
                temp[3]--;
            }

            start++;
            end++;

            // 윈도우의 끝에 새로 들어온 문자 처리
            if (str[end] == 'A') {
                temp[0]++;
            } else if (str[end] == 'C') {
                temp[1]++;
            } else if (str[end] == 'G') {
                temp[2]++;
            } else if (str[end] == 'T') {
                temp[3]++;
            }

            // 새 윈도우 검증
            flag = true;
            for (int i = 0; i < 4; i++) {
                if (temp[i] < dna[i]) {
                    flag = false;
                    break;  // 하나라도 조건을 충족하지 않으면 더 이상 검사할 필요가 없음
                }
            }

            if (flag) {
                count++;
            }
        }

        System.out.println(count);
    }
}
