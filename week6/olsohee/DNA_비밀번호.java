package week6.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DNA_비밀번호 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Map<Character, Integer> standardMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        char[] arr = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        standardMap.put('A', Integer.parseInt(st.nextToken()));
        standardMap.put('C', Integer.parseInt(st.nextToken()));
        standardMap.put('G', Integer.parseInt(st.nextToken()));
        standardMap.put('T', Integer.parseInt(st.nextToken()));

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 0);
        map.put('G', 0);
        map.put('T', 0);

        for (int i = 0; i < p; i++) {
            map.put(arr[i], map.get(arr[i]) + 1);
        }

        int start = 0;
        int end = p - 1;
        int answer = 0;
        while (end < s) {
            if (canAnswer(map)) answer++;

            map.put(arr[start], map.get(arr[start]) - 1);
            start++;
            end++;
            if (end == s) break;
            map.put(arr[end], map.get(arr[end]) + 1);
        }

        System.out.println(answer);
    }

    private static boolean canAnswer(Map<Character, Integer> map) {
        for (Character key : standardMap.keySet()){
            if (map.get(key) < standardMap.get(key)) return false;
        }
        return true;
    }

}
