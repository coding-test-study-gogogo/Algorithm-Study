package week17.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 팔 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        String start = st.nextToken();
        String end = st.nextToken();

        int cnt = 0;
        if (start.length() == end.length()) {
            for (int i = 0; i < start.length(); i++) {
                if (start.charAt(i) == end.charAt(i)) {
                    if (start.charAt(i) == '8') cnt++;
                } else {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
