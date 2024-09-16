package week10.olsohee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 암호_만들기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int l;
    static int c;
    static char[] arr;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        dfs(0, "");
    }

    private static void dfs(int start, String str) {
        if (str.length() == l) {
            if (canAnswer(str)) {
                System.out.println(str);
            }
            return;
        }

        for (int i = start; i < arr.length; i++) {
            dfs(i + 1, str + arr[i]);
        }
    }

    private static boolean canAnswer(String str) {
        int num1 = 0; // 모음 개수
        int num2 = 0; // 자음 개수
        for (char c : str.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                num1++;
            } else {
                num2++;
            }
        }

        return num1 >= 1 && num2 >= 2;
    }
}
