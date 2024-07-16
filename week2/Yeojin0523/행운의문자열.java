// 2024-07-16
import java.util.Scanner;

public class 행운의문자열 {
    static int[] alphabet = new int[26]; // 각 알파벳의 개수를 저장
    static int count; // 행운의 문자열 개수
    static int length; // 문자열 길이

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next(); // 입력 문자열
        length = str.length(); // 문자열 길이
        
        // 각 문자의 개수를 셈
        for (int i = 0; i < str.length(); i++) {
            alphabet[str.charAt(i) - 'a']++;
        }

        // DFS 탐색 시작
        dfs(0, ' ');
        System.out.println(count); // 결과 출력
    }

    public static void dfs(int index, char pre) {
        // 문자열 길이만큼 탐색한 경우
        if (index == length) {
            count++;
            return;
        }

        // 모든 알파벳을 시도
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] == 0) continue; // 해당 문자가 없으면 건너뜀

            // 이전 문자와 다른 경우
            if (pre != (char) (i + 'a')) {
                alphabet[i]--; // 문자의 개수를 감소
                dfs(index + 1, (char) (i + 'a')); // 다음 문자로 재귀 호출
                alphabet[i]++; // 문자의 개수를 복구
            }
        }
    }
}
