package week4.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 돌게임 {
    public static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());



        if (N % 2 == 0) {
            System.out.println("CY");
        } else if ( N%2 == 1) {
            System.out.println("SK");
        }


    }
}
