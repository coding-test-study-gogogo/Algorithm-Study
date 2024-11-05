package week18.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ZOAC {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String input;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input = br.readLine();
        visited = new boolean[input.length()];

        func(0, input.length() - 1);
        System.out.println(sb);
    }

    private static void func(int start, int end) {
        if (start > end) {
            return;
        }

        // idx = start~end 사이의 문자중 가장 수가 적은 문자의 인덱스
        int idx = start;
        for (int i = start; i <= end; i++) {
            if (input.charAt(idx) > input.charAt(i)) {
                idx = i;
            }
        }

        // 인덱스 idx의 문자 선택!
        visited[idx] = true;

        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) {
                sb.append(input.charAt(i));
            }
        }
        sb.append('\n');

        func(idx + 1, end);
        func(start, idx - 1);
    }
}
