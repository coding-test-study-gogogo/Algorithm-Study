package week4.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 돌_게임 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        if (n % 2 == 0) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}
