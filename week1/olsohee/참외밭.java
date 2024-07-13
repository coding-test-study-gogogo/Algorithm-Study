package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 참외밭 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int k = Integer.parseInt(br.readLine());

        int[] dir = new int[6];
        int[] len = new int[6];
        int hMax = 0, wMax = 0; //각 가로 세로의 최대 길이 저장
        int hMaxIdx = -1, wMaxIdx = -1; //가로 세로의 최대 길이의 인덱스 저장

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            dir[i] = Integer.parseInt(st.nextToken());
            len[i] = Integer.parseInt(st.nextToken());
            // 가로이면
            if (dir[i] == 1 || dir[i] == 2) {
                if (wMax < len[i]) {
                    wMax = len[i];
                    wMaxIdx = i;
                }
            }
            // 세로이면
            else {
                if (hMax < len[i]) {
                    hMax = len[i];
                    hMaxIdx = i;
                }
            }
        }

        int maxArea = hMax * wMax;
        int minArea = len[(hMaxIdx + 3) % 6] * len[(wMaxIdx + 3) % 6];
        System.out.println((maxArea - minArea) * k);
    }
}
