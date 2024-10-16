package week15.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 접두사 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        List<String> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        Collections.sort(list, Collections.reverseOrder());
        Set<String> set = new HashSet<>();
        for (String str : list) {
            if (set.size() == 0) {
                set.add(str);
                continue;
            }

            boolean canSave = true;
            for (String savedStr : set) {
                if (savedStr.startsWith(str)) {
                    canSave = false;
                    break;
                }
            }

            if (canSave) set.add(str);
        }

        System.out.println(set.size());
    }
}
