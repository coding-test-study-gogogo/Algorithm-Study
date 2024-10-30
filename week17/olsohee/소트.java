package week17.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소트 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        /*
        맨 앞자리부터, 해당 자리에 s번의 움직임 내로 올 수 있는 제일 큰 수를 채우기
         */

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        int s = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            // i번 자리에 최댓값 채우기
            int max = list.get(i);
            int nextIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (j - i > s) break;
                if (max < list.get(j)) {
                    max = list.get(j);
                    nextIdx = j;
                }
            }

            // i번 자리에 list.get(nextIdx)을 채우기
            list.add(i, list.remove(nextIdx));
            s -= nextIdx - i;
        }


        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }
}
