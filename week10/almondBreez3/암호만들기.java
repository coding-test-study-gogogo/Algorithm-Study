package week10.almondBreez3;

import java.util.*;
import java.io.*;

class 암호만들기 {
    public static int L, C;
    public static int[] arr = new int[26];
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[] str = br.readLine().split(" ");
        for (int i = 0; i < C; i++) {
            char c = str[i].charAt(0); // Correct way to get the character from the string array
            arr[c - 'a'] = 1;
        }

        visited = new boolean[26];
        dfs(0,0, new ArrayList<>(),0,0);
    }

    public static void dfs(int idx, int depth, List<Character> a, int flag,int flag2) {
        if (depth == L) {
            if (flag < 2) return;
            if (flag2 < 1) return;
            for (char c : a) {
                System.out.print(c);
            }

            System.out.println();
            return; // Stop further recursion once the password length is reached
        }

        for (int i = idx; i < 26; i++) { // Iterate over the 26 letters
            if (arr[i] == 1 && !visited[i]) {
                if (i != 0 && i != 4 && i!= 8 && i!= 14 && i!=20) {
                    flag += 1;
                    a.add((char) (i + 'a')); // Add the corresponding character
                    visited[i] = true;
                    dfs(i,depth + 1, a,flag,flag2); // Recur with increased depth
                    visited[i] = false;
                    a.remove(a.size() - 1); // Remove the last added character
                    flag -= 1;
                } else {
                    flag2 += 1;
                    a.add((char) (i + 'a')); // Add the corresponding character
                    visited[i] = true;
                    dfs(i,depth + 1, a,flag,flag2); // Recur with increased depth
                    visited[i] = false;
                    a.remove(a.size() - 1); // Remove the last added character
                    flag2 -= 1;
                }

            }
        }
    }
}

