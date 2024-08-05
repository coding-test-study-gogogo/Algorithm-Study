package week4.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 그룹단어체커 {
    public static boolean[] alpha = new boolean[27];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int res = 0;
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            alpha = new boolean[27];
            int count = 0;
            for (int j = 0; j < temp.length; j++) {
                if (alpha[temp[j]-'a']) {
                    if (j >= 1) {
                        if (temp[j-1] != temp[j]) {
                            count = 0;
                            break;
                        }
                    }
                } else {
                    alpha[temp[j]-'a'] = true;
                    count++;
                }

            }
            if (count > 0) {
                res++;
            }
        }

        System.out.println(res);
    }
}