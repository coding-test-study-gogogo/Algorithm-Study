import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> results = new ArrayList<>();

        for (long number : numbers) {
            String binaryString = Long.toBinaryString(number);
            int depth = (int) Math.ceil(Math.log(binaryString.length() + 1) / Math.log(2));
            int fullLength = (int) Math.pow(2, depth) - 1;

            // 포화 이진 트리로 만들기 위해 앞에 0을 채움
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fullLength - binaryString.length(); i++) {
                sb.append('0');
            }
            sb.append(binaryString);
            binaryString = sb.toString();

            // 트리를 배열로 변환
            int[] treeArray = binaryString.chars().map(c -> c - '0').toArray();

            // 조건 검사
            if (isValidTree(treeArray, 0, treeArray.length - 1)) {
                results.add(1);
            } else {
                results.add(0);
            }
        }

        return results.stream().mapToInt(i -> i).toArray();
    }

    private boolean isValidTree(int[] tree, int start, int end) {
        if (start > end) {
            return true;
        }

        int mid = (start + end) / 2;

        // 부모가 0인데 자식이 1인 경우를 체크
        if (tree[mid] == 0) {
            for (int i = start; i <= end; i++) {
                if (tree[i] == 1) {
                    return false;
                }
            }
        }

        return isValidTree(tree, start, mid - 1) && isValidTree(tree, mid + 1, end);
    }
}

//조금 다른 풀이
//
//import java.util.*;
//class Solution {
//    public String[] str;
//    public int count;
//    public int[] arr;
//    public int[] solution(long[] numbers) {
//        List<Integer> li = new ArrayList<>();
//        str = new String[numbers.length];
//        //이진수 변환
//        for (int i = 0; i < numbers.length; i++) {
//            String temp = Long.toBinaryString(numbers[i]);
//            str[i] = makeFullBinaryTree(temp);
//        }
//
//        // 이진수 변환한 String을 int[]에 넣어서 하나씩 체크하기
//        for (String s : str) {
//            arr = s.chars().map(Character::getNumericValue).toArray();
//            li.add(find(0, arr.length - 1) ? 1 : 0);
//        }
//
//        int[] answer = li.stream().mapToInt(Integer::intValue).toArray();
//        return answer;
//    }
//    //포화 이진트리 만들기 -> 놓친 부분
//    private String makeFullBinaryTree(String binaryString) {
//        int length = binaryString.length();
//        int depth = (int) Math.ceil(Math.log(length + 1) / Math.log(2));
//        int fullLength = (int) Math.pow(2, depth) - 1;
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < fullLength - length; i++) {
//            sb.append('0');
//        }
//        sb.append(binaryString);
//        return sb.toString();
//    }
//    public boolean find(int start,int end) {
//        // 모두 문제 없이 돌았다는 뜻
//        if (start > end) {
//            return true;
//        }
//        int mid = (start+end) / 2;
//
//        // 부모가 0인데 자식이 1이면 return 0;
//        if (arr[mid] == 0) {
//            for (int i = start; i <= end; i++) {
//                if (arr[i]==1) {
//                    return false;
//                }
//            }
//        }
//        return find(start, mid - 1) && find(mid + 1, end);
//
//    }
//}